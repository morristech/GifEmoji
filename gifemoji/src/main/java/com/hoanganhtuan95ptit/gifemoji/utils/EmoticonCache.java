package com.hoanganhtuan95ptit.gifemoji.utils;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.util.LruCache;

/**
 * Created by HOANG ANH TUAN on 7/20/2017.
 */

class EmoticonCache {

    private static final int SIZE_MAX = 1024;

    private static EmoticonCache instance;

    private static EmoticonCache getInstance() {
        if (instance == null) instance = new EmoticonCache();
        return instance;
    }

    private LruCache<String, Drawable> drawableLruCache;
    private LruCache<String, EmoticonSpan> emoticonSpanLruCache;
    private LruCache<String, EmoticonDecoder> emoticonDecoderLruCache;
    private LruCache<String, EmoticonDrawable> emoticonDrawableLruCache;
    private LruCache<String, EmoticonSpannableStringBuilder> emoticonSpannableStringBuilderLruCache;

    private EmoticonCache() {
        drawableLruCache = new LruCache<>(SIZE_MAX);
        emoticonSpanLruCache = new LruCache<>(SIZE_MAX);
        emoticonDecoderLruCache = new LruCache<>(SIZE_MAX);
        emoticonDrawableLruCache = new LruCache<>(SIZE_MAX);
        emoticonSpannableStringBuilderLruCache = new LruCache<>(SIZE_MAX);
    }

    private LruCache<String, Drawable> getDrawableLruCache() {
        return drawableLruCache;
    }

    private LruCache<String, EmoticonSpan> getEmoticonSpanLruCache() {
        return emoticonSpanLruCache;
    }

    private LruCache<String, EmoticonDecoder> getEmoticonDecoderLruCache() {
        return emoticonDecoderLruCache;
    }

    private LruCache<String, EmoticonDrawable> getEmoticonDrawableLruCache() {
        return emoticonDrawableLruCache;
    }

    private LruCache<String, EmoticonSpannableStringBuilder> getEmoticonSpannableStringBuilderLruCache() {
        return emoticonSpannableStringBuilderLruCache;
    }

    static Drawable getDrawable(@DrawableRes int drawable, int position) {
        return getInstance().getDrawableLruCache().get(drawable + "" + position);
    }

    static void saveDrawable(@DrawableRes int drawable, int position, Drawable bitmap) {
        getInstance().getDrawableLruCache().put(drawable + "" + position, bitmap);
    }

    static EmoticonSpan getEmoticonSpan(@DrawableRes int drawableRes, int textSize) {
        return getInstance().getEmoticonSpanLruCache().get(drawableRes + "" + textSize);
    }

    static void saveEmoticonSpan(@DrawableRes int drawableRes, int textSize, EmoticonSpan emoticonSpan) {
        getInstance().getEmoticonSpanLruCache().put(drawableRes + "" + textSize, emoticonSpan);
    }

    static EmoticonDecoder getEmoticonDecoder(@DrawableRes int drawableRes, int textSize) {
        return getInstance().getEmoticonDecoderLruCache().get(drawableRes + "" + textSize);
    }

    static void saveEmoticonDecoder(@DrawableRes int drawableRes, int textSize, EmoticonDecoder emoticonSpan) {
        getInstance().getEmoticonDecoderLruCache().put(drawableRes + "" + textSize, emoticonSpan);
    }

    static EmoticonDrawable getEmoticonDrawable(@DrawableRes int drawableRes, int textSize) {
        return getInstance().getEmoticonDrawableLruCache().get(drawableRes + "" + textSize);
    }

    static void saveEmoticonDrawable(@DrawableRes int drawableRes, int textSize, EmoticonDrawable emoticonSpan) {
        getInstance().getEmoticonDrawableLruCache().put(drawableRes + "" + textSize, emoticonSpan);
    }

    static EmoticonSpannableStringBuilder getSpannableStringBuilder(String text) {
        return getInstance().getEmoticonSpannableStringBuilderLruCache().get(text);
    }

    static void saveSpannableStringBuilder(String text, EmoticonSpannableStringBuilder emoticonSpannableStringBuilder) {
        getInstance().getEmoticonSpannableStringBuilderLruCache().put(text, emoticonSpannableStringBuilder);
    }
}
