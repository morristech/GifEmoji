package com.hoanganhtuan95ptit.gifemoji.utils;

import android.content.Context;
import android.text.SpannableStringBuilder;

/**
 * Created by Hoang Anh Tuan on 12/18/2017.
 */

public class EmoticonSpannableStringBuilder extends SpannableStringBuilder {

    public static SpannableStringBuilder create(Context context, EmoticonProvider emoticonProvider, int textSize, String text) {
        EmoticonSpannableStringBuilder spannableStringBuilder =EmoticonCache.getSpannableStringBuilder(text);
        if (spannableStringBuilder == null)
            spannableStringBuilder = new EmoticonSpannableStringBuilder(context, emoticonProvider, text, textSize);
        return spannableStringBuilder;
    }

    private EmoticonSpannableStringBuilder(Context context, EmoticonProvider emoticonProvider, String text, int textSize) {
        super(text);
        EmoticonUtils.replaceWithImages(context, this, emoticonProvider, textSize);
        EmoticonCache.saveSpannableStringBuilder(text, this);
    }
}
