package com.hoanganhtuan95ptit.gifemoji.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.DrawableRes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by HOANG ANH TUAN on 7/20/2017.
 */

class EmoticonDecoder {

    private int frameNum;
    private int delay;
    private long handle;

    public static EmoticonDecoder create(Context context, @DrawableRes int drawableRes, int textSize) {
        EmoticonDecoder emoticonDecoder = EmoticonCache.getEmoticonDecoder(drawableRes, textSize);
        if (emoticonDecoder == null)
            emoticonDecoder = new EmoticonDecoder(context, drawableRes, textSize);
        return emoticonDecoder;
    }

    private EmoticonDecoder(Context context, @DrawableRes int drawableRes, int textSize) {
        handle = nativeInit();
        load(context, drawableRes, textSize, copy(context, drawableRes));
        EmoticonCache.saveEmoticonDecoder(drawableRes, textSize, this);
    }

    int frameNum() {
        return frameNum;
    }

    int getDelay() {
        return delay;
    }

    private void load(Context context, @DrawableRes int drawableRes, int textSize, String url) {
        if (!nativeLoad(handle, url)) {
            nativeClose(handle);
            throw new RuntimeException("not gif");
        } else {
            frameNum = nativeGetFrameCount(handle);
            for (int i = 0; i < frameNum; i++) {
                BitmapDrawable drawable = new BitmapDrawable(context.getResources(), nativeGetFrame(handle, i));
                int width = (textSize * drawable.getIntrinsicWidth()) / drawable.getIntrinsicHeight();
                drawable.setBounds(0, 0, width, textSize);
                EmoticonCache.saveDrawable(drawableRes, i, drawable);
                delay = nativeGetDelay(handle, i);
            }
            nativeClose(handle);
        }
    }

    private String copy(Context context, @DrawableRes int drawable) {
        try {
            @SuppressLint("ResourceType") InputStream is = context.getResources().openRawResource(drawable);
            String destFile = context.getFilesDir().getAbsolutePath() + File.separator + drawable;

            File file = new File(destFile);
            destFile = context.getFilesDir().getAbsolutePath() + File.separator + file.getName();
            file = new File(destFile);
            if (file.exists()) return destFile;

            FileOutputStream os = new FileOutputStream(destFile);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = is.read(buffer)) != -1) {
                os.write(buffer, 0, read);
            }
            is.close();
            os.flush();
            os.close();
            return destFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    static {
        System.loadLibrary("native_lib_GifDecoder");
    }

    private native long nativeInit();

    private native void nativeClose(long var1);

    private native boolean nativeLoad(long var1, String var3);

    private native int nativeGetFrameCount(long var1);

    private native Bitmap nativeGetFrame(long var1, int var3);

    private native int nativeGetDelay(long var1, int var3);

}
