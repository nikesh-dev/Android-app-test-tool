

#include<string.h>//
#include <jni.h>//
#include <stdlib.h>//
#include <unistd.h>//
#include <dirent.h>//
#include <fcntl.h>//
#include <stdio.h>//
#include <errno.h>//
#include <stdint.h>

#include <sys/ioctl.h>//
#include <sys/mman.h>//

#include <time.h>//
#include <linux/types.h>
#include <sys/types.h>//
#include <sys/inotify.h>//
#include <sys/limits.h>//
#include <linux/poll.h>//


#include <linux/fb.h>
#include <linux/kd.h>
#include <linux/stddef.h>
#include <linux/input.h>
#include "uinput.h"
#include "include/pixelflinger.h"

#include <android/log.h>
#define TAG "JNI"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG  , TAG, __VA_ARGS__)
#define LOGV(...) __android_log_print(ANDROID_LOG_VERBOSE, TAG, __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, TAG, __VA_ARGS__)
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN, TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, TAG, __VA_ARGS__)



/* Debug tools
 */
 int g_debug = 0;
 typedef struct {
      long filesize;
      char reserved[2];
      long headersize;
      long infoSize;
      long width;
      long depth;
      short biPlanes;
      short bits;
      long biCompression;
      long biSizeImage;
      long biXPelsPerMeter;
      long biYPelsPerMeter;
      long biClrUsed;
      long biClrImportant;
 } BMPHEAD;


jint Java_com_example_event_Nativenput_intEnableDebug( JNIEnv* env,jobject thiz, jint enable ) {

	g_debug = enable;
	return g_debug;
}


void JNI_OnUnload(JavaVM *vm, void *reserved)
{
	//debug("eventinterceptor native lib unloaded.");
}

static struct typedev {
	struct pollfd ufds;
	char *device_path;
	char *device_name;
} *pDevs = NULL;
struct pollfd *ufds;
static int nDevsCount;

const char *device_path = "/dev/input";

int g_Polling = 0;
struct input_event event;
int c;
int i;
int pollres;
int get_time = 0;
char *newline = "\n";
uint16_t get_switch = 0;
struct input_event event;
int version;

int dont_block = -1;
int event_count = 0;
int sync_rate = 0;
int64_t last_sync_time = 0;
const char *device = NULL;


static int open_device(int index)
{
	if (index >= nDevsCount || pDevs == NULL) return -1;
	//debug("open_device prep to open");
	char *device = pDevs[index].device_path;

//	debug("open_device call %s", device);
    int version;
    int fd;

    char name[80];
    char location[80];
    char idstr[80];
    struct input_id id;

    fd = open(device, O_RDWR);
    if(fd < 0) {
		pDevs[index].ufds.fd = -1;

		pDevs[index].device_name = NULL;
		//debug("could not open %s, %s", device, strerror(errno));
        return -1;
    }

	pDevs[index].ufds.fd = fd;
	ufds[index].fd = fd;

    name[sizeof(name) - 1] = '\0';
    if(ioctl(fd, EVIOCGNAME(sizeof(name) - 1), &name) < 1) {
       // debug("could not get device name for %s, %s", device, strerror(errno));
        name[0] = '\0';
    }
	//debug("Device %d: %s: %s", nDevsCount, device, name);

	pDevs[index].device_name = strdup(name);


    return 0;
}

int remove_device(int index)
{
	if (index >= nDevsCount || pDevs == NULL ) return -1;

	int count = nDevsCount - index - 1;
	//debug("remove device %d", index);
	free(pDevs[index].device_path);
	free(pDevs[index].device_name);

	memmove(&pDevs[index], &pDevs[index+1], sizeof(pDevs[0]) * count);
	nDevsCount--;
	return 0;
}



static int scan_dir(const char *dirname)
{
	nDevsCount = 0;
    char devname[PATH_MAX];
    char *filename;
    DIR *dir;
    struct dirent *de;
    dir = opendir(dirname);
    if(dir == NULL)
        return -1;
    strcpy(devname, dirname);
    filename = devname + strlen(devname);
    *filename++ = '/';
    while((de = readdir(dir))) {
        if(de->d_name[0] == '.' &&
           (de->d_name[1] == '\0' ||
            (de->d_name[1] == '.' && de->d_name[2] == '\0')))
            continue;
        strcpy(filename, de->d_name);
		//debug("scan_dir:prepare to open:%s", devname);
		// add new filename to our structure: devname
		struct typedev *new_pDevs = realloc(pDevs, sizeof(pDevs[0]) * (nDevsCount + 1));
		if(new_pDevs == NULL) {
			//debug("out of memory");
			return -1;
		}
		pDevs = new_pDevs;

		struct pollfd *new_ufds = realloc(ufds, sizeof(ufds[0]) * (nDevsCount + 1));
		if(new_ufds == NULL) {
			//debug("out of memory");
			return -1;
		}
		ufds = new_ufds;
		ufds[nDevsCount].events = POLLIN;

		pDevs[nDevsCount].ufds.events = POLLIN;
		pDevs[nDevsCount].device_path = strdup(devname);

        nDevsCount++;
    }
    closedir(dir);
    return 0;
}
/*
jstring Java_com_example_event_Nativenput_nike()
{

 return "its working";
}
*/
jint Java_com_example_event_Nativenput_ScanFiles( JNIEnv* env,jobject thiz ) {
	int res = scan_dir(device_path);
		if(res < 0) {
		//	debug("scan dir failed for %s:", device_path);
			return -1;
		}

		return nDevsCount;
}

jint Java_com_example_event_Nativenput_intSendEvent(JNIEnv* env,jobject thiz, jint index, uint16_t type, uint16_t code, int32_t value) {
	if (index >= nDevsCount || pDevs[index].ufds.fd == -1) return -1;
	int fd = pDevs[index].ufds.fd;
	//debug("SendEvent call (%d,%d,%d,%d)", fd, type, code, value);
	struct uinput_event event;
	int len;

	if (fd <= fileno(stderr)) return 0;

	memset(&event, 0, sizeof(event));
	event.type = type;
	event.code = code;
	event.value = value;

	len = write(fd, &event, sizeof(event));
	//debug("SendEvent done:%d",len);
}


jstring Java_com_example_event_Nativenput_getDevPath( JNIEnv* env,jobject thiz, jint index) {
	return (*env)->NewStringUTF(env, pDevs[index].device_path);
}
jstring Java_com_example_event_Nativenput_getDevName( JNIEnv* env,jobject thiz, jint index) {
	if (pDevs[index].device_name == NULL) return NULL;
	else return (*env)->NewStringUTF(env, pDevs[index].device_name);
}

jint Java_com_example_event_Nativenput_OpenDev( JNIEnv* env,jobject thiz, jint index ) {
	return open_device(index);
}

jint Java_com_example_event_Nativenput_RemoveDev( JNIEnv* env,jobject thiz, jint index ) {
	return remove_device(index);
}
/*
JNIEXPORT jstring JNICALL Java_com_example_event_Nativenput_nike1( JNIEnv* env,jobject thiz, jint index) {
	return (*env)->NewStringUTF(env, "Hello from native code, thank you!");
}*/
/*
JNIEXPORT jstring JNICALL Java_com_mytest_JNIActivity_getMessage1
          (JNIEnv *env, jobject thisObj) {
   return (*env)->NewStringUTF(env, "Hello from native code, thank you!");
}*/


jint Java_com_example_event_Nativenput_PollDev( JNIEnv* env,jobject thiz, jint index ) {
	if (index >= nDevsCount || pDevs[index].ufds.fd == -1) return -1;
	int pollres = poll(ufds, nDevsCount, -1);
	if(ufds[index].revents) {
		if(ufds[index].revents & POLLIN) {
			int res = read(ufds[index].fd, &event, sizeof(event));
			if(res < (int)sizeof(event)) {
				return 1;
			}
			else return 0;
		}
	}
	return -1;
}

jint Java_com_example_event_Nativenput_getType( JNIEnv* env,jobject thiz ) {
	return event.type;
}

jint Java_com_example_event_Nativenput_getCode( JNIEnv* env,jobject thiz ) {
	return event.code;
}

jint Java_com_example_event_Nativenput_getValue( JNIEnv* env,jobject thiz ) {
	return event.value;
}
jstring Java_com_example_event_Nativenput_nike1( JNIEnv* env,jobject thiz) {
	return (*env)->NewStringUTF(env, "working ");
}








static GGLSurface gr_framebuffer[2];
//handler
static int gr_fb_fd = -1;
//v screen info
static struct fb_var_screeninfo vi;
//f screen info
struct fb_fix_screeninfo fi;

static void dumpinfo(struct fb_fix_screeninfo *fi,
                     struct fb_var_screeninfo *vi);

static int get_framebuffer(GGLSurface *fb)
{
    int fd;
    void *bits;

    fd = open("/dev/graphics/fb0", O_RDWR);
    if(fd < 0) {
        perror("cannot open fb0");
        return -5;
    }

    if(ioctl(fd, FBIOGET_FSCREENINFO, &fi) < 0) {
        perror("failed to get fb0 info");
        return -2;
    }

    if(ioctl(fd, FBIOGET_VSCREENINFO, &vi) < 0) {
        perror("failed to get fb0 info");
        return -3;
    }

    //dumpinfo(&fi, &vi);

    bits = mmap(0, fi.smem_len, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
    if(bits == MAP_FAILED) {
        perror("failed to mmap framebuffer");
        return -4;
    }

    fb->version = sizeof(*fb);
    fb->width = vi.xres;
    fb->height = vi.yres;
    fb->stride = fi.line_length / (vi.bits_per_pixel >> 3);
    fb->data = bits;
    fb->format = GGL_PIXEL_FORMAT_RGB_565;

    fb++;

    fb->version = sizeof(*fb);
    fb->width = vi.xres;
    fb->height = vi.yres;
    fb->stride = fi.line_length / (vi.bits_per_pixel >> 3);
    fb->data = (void*) (((unsigned) bits) + vi.yres * vi.xres * 2);
    fb->format = GGL_PIXEL_FORMAT_RGB_565;

    return fd;
}


static void dumpinfo(struct fb_fix_screeninfo *fi, struct fb_var_screeninfo *vi)
{
    fprintf(stderr,"vi.xres = %d\n", vi->xres);
    fprintf(stderr,"vi.yres = %d\n", vi->yres);
    fprintf(stderr,"vi.xresv = %d\n", vi->xres_virtual);
    fprintf(stderr,"vi.yresv = %d\n", vi->yres_virtual);
    fprintf(stderr,"vi.xoff = %d\n", vi->xoffset);
    fprintf(stderr,"vi.yoff = %d\n", vi->yoffset);
    fprintf(stderr, "vi.bits_per_pixel = %d\n", vi->bits_per_pixel);

    fprintf(stderr, "fi.line_length = %d\n", fi->line_length);

}



//int main(int argc, char **argv)
JNIEXPORT void JNICALL Java_com_example_event_Nativenput_getMessage
          (JNIEnv *env, jobject thisObj, jstring prompt) {

  //get screen capture

	 char buf[128];
	    const char *str = (*env)->GetStringUTFChars(env, prompt, 0);
  gr_fb_fd = get_framebuffer(gr_framebuffer);
 // return gr_fb_fd;
//  /*
  if (gr_fb_fd <= 0) exit(1);

  int w = vi.xres, h = vi.yres, depth = vi.bits_per_pixel;
  //return depth;

  //convert pixel data
  uint8_t *rgb24;
  if (depth == 16)
  {
	rgb24 = (uint8_t *)malloc(w * h * 3);
	int i = 0;
	for (;i<w*h;i++)
	{
		uint16_t pixel16 = ((uint16_t *)gr_framebuffer[0].data)[i];
		// RRRRRGGGGGGBBBBBB -> RRRRRRRRGGGGGGGGBBBBBBBB
		// in rgb24 color max is 2^8 per channel (*255/32 *255/64 *255/32)
		rgb24[3*i+2]   = (255*(pixel16 & 0x001F))/ 32; 		//Blue
		rgb24[3*i+1]   = (255*((pixel16 & 0x07E0) >> 5))/64;	//Green
		rgb24[3*i]     = (255*((pixel16 & 0xF800) >> 11))/32; 	//Red
	}
  }
  else
  if (depth == 24) //exactly what we need
  {
  	rgb24 = (uint8_t *) gr_framebuffer[0].data;
  }
  else
  if (depth == 32) //skip transparency channel
  {
	rgb24 = (uint8_t *) malloc(w * h * 3);
	int i=0;
	for (;i<w*h;i++)
	{
		uint32_t pixel32 = ((uint32_t *)gr_framebuffer[0].data)[i];
		// in rgb24 color max is 2^8 per channel
		rgb24[3*i+0]   =  pixel32 & 0x000000FF; 		//Blue
		rgb24[3*i+1]   = (pixel32 & 0x0000FF00) >> 8;	//Green
		rgb24[3*i+2]   = (pixel32 & 0x00FF0000) >> 16; 	//Red
	}
  }
  else
  {
  	//free
        close(gr_fb_fd);
	exit(2);
  };
  //return rgb24;

  //save RGB 24 Bitmap
  int bytes_per_pixel = 3;
  BMPHEAD bh;
  memset ((char *)&bh,0,sizeof(BMPHEAD)); // sets everything to 0
  //bh.filesize  =   calculated size of your file (see below)
  //bh.reserved  = two zero bytes
  bh.headersize  = 54L;			// for 24 bit images
  bh.infoSize  =  0x28L;		// for 24 bit images
  bh.width     = w;			// width of image in pixels
  bh.depth     = h;			// height of image in pixels
  bh.biPlanes  =  1;			// for 24 bit images
  bh.bits      = 8 * bytes_per_pixel;	// for 24 bit images
  bh.biCompression = 0L;		// no compression
  int bytesPerLine;
  bytesPerLine = w * bytes_per_pixel;  	// for 24 bit images
  //round up to a dword boundary
  if (bytesPerLine & 0x0003)
  {
    	bytesPerLine |= 0x0003;
    	++bytesPerLine;
  }
  bh.filesize = bh.headersize + (long)bytesPerLine * bh.depth;
  FILE * bmpfile;
  //printf("Bytes per line : %d\n", bytesPerLine);
  bmpfile = fopen(str, "wb");
  if (bmpfile == NULL)
  {
	close(gr_fb_fd);
	exit(3);
  }
  fwrite("BM",1,2,bmpfile);
  fwrite((char *)&bh, 1, sizeof (bh), bmpfile);
  //fwrite(rgb24,1,w*h*3,bmpfile);
  char *linebuf;
  linebuf = (char *) calloc(1, bytesPerLine);
  if (linebuf == NULL)
  {
     	fclose(bmpfile);
	close(gr_fb_fd);
	exit(4);
  }
  int line,x;
  for (line = h-1; line >= 0; line --)
  {
    	// fill line linebuf with the image data for that line
	for( x =0 ; x < w; x++ )
  	{
   		*(linebuf+x*bytes_per_pixel) = *(rgb24 + (x+line*w)*bytes_per_pixel+2);
   		*(linebuf+x*bytes_per_pixel+1) = *(rgb24 + (x+line*w)*bytes_per_pixel+1);
   		*(linebuf+x*bytes_per_pixel+2) = *(rgb24 + (x+line*w)*bytes_per_pixel+0);
  	}
	// remember that the order is BGR and if width is not a multiple
       	// of 4 then the last few bytes may be unused
	fwrite(linebuf, 1, bytesPerLine, bmpfile);
  }
  fclose(bmpfile);
  close(gr_fb_fd);
 // return 109;

}

