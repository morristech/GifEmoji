package com.hoanganhtuan95ptit.example.utils;


import com.hoanganhtuan95ptit.example.R;
import com.hoanganhtuan95ptit.gifemoji.utils.EmoticonProvider;

import java.util.HashMap;

/**
 * Created by Hoang Anh Tuan on 12/15/2017.
 */

public class GifEmoticonProvider implements EmoticonProvider {

    static final HashMap<String, Integer> EMOTICONS = new HashMap<>();

    static {
        EMOTICONS.put("(giggle)", R.drawable.giggle);
        EMOTICONS.put("(monkey)", R.drawable.monkey);
        EMOTICONS.put("(rock)", R.drawable.rock);
        EMOTICONS.put(":)", R.drawable.emoji_1);
        EMOTICONS.put("y:))", R.drawable.emoji_2);
        EMOTICONS.put("(:8", R.drawable.rock);
        EMOTICONS.put(":'(", R.drawable.emoji_4);
        EMOTICONS.put(":D", R.drawable.emoji_5);
        EMOTICONS.put(">:|", R.drawable.emoji_6);
        EMOTICONS.put("|:|", R.drawable.emoji_7);
        EMOTICONS.put(":B", R.drawable.emoji_8);
        EMOTICONS.put("):.", R.drawable.emoji_9);
        EMOTICONS.put("b:|", R.drawable.emoji_10);
        EMOTICONS.put("**", R.drawable.emoji_11);
        EMOTICONS.put("**|", R.drawable.emoji_12);
        EMOTICONS.put("O:|", R.drawable.emoji_13);
        EMOTICONS.put("@@", R.drawable.emoji_14);
        EMOTICONS.put("8'", R.drawable.emoji_15);
        EMOTICONS.put(":-o", R.drawable.emoji_16);
        EMOTICONS.put("*:", R.drawable.emoji_17);
        EMOTICONS.put(">8p", R.drawable.emoji_18);
        EMOTICONS.put("cold", R.drawable.emoji_19);
        EMOTICONS.put("):(", R.drawable.emoji_20);
        EMOTICONS.put(",:|", R.drawable.emoji_21);
        EMOTICONS.put(">:(", R.drawable.emoji_22);
        EMOTICONS.put(":(=", R.drawable.emoji_23);
        EMOTICONS.put("|;(", R.drawable.emoji_24);
        EMOTICONS.put("B)", R.drawable.emoji_25);
        EMOTICONS.put(":8b", R.drawable.emoji_26);
        EMOTICONS.put(":=", R.drawable.emoji_27);
        EMOTICONS.put(";)", R.drawable.emoji_28);
        EMOTICONS.put("..:.", R.drawable.emoji_29);
        EMOTICONS.put(":P", R.drawable.emoji_30);
        EMOTICONS.put(">:;", R.drawable.emoji_31);
        EMOTICONS.put("|:;", R.drawable.emoji_32);
        EMOTICONS.put("|;)", R.drawable.emoji_33);
        EMOTICONS.put("b-", R.drawable.emoji_34);
        EMOTICONS.put(":-o", R.drawable.emoji_35);
        EMOTICONS.put(":'o", R.drawable.emoji_36);
        EMOTICONS.put("_:O", R.drawable.emoji_37);
        EMOTICONS.put("8d", R.drawable.emoji_38);
        EMOTICONS.put("6~", R.drawable.emoji_39);
        EMOTICONS.put("(yoyo)", R.drawable.emoji_40);
        EMOTICONS.put("<8o", R.drawable.emoji_41);
        EMOTICONS.put(";.)", R.drawable.emoji_42);
        EMOTICONS.put("':o", R.drawable.emoji_43);
        EMOTICONS.put("(spiderman)", R.drawable.emoji_45);
        EMOTICONS.put(":).", R.drawable.emoji_46);
        EMOTICONS.put(":((", R.drawable.emoji_47);
        EMOTICONS.put("=;-", R.drawable.emoji_48);
        EMOTICONS.put(":oo", R.drawable.emoji_49);
        EMOTICONS.put(":s2", R.drawable.emoji_50);
        EMOTICONS.put(");o", R.drawable.emoji_51);
        EMOTICONS.put(":=~", R.drawable.emoji_52);
        EMOTICONS.put("B-0", R.drawable.emoji_53);
        EMOTICONS.put(">:", R.drawable.emoji_54);
    }

    private static GifEmoticonProvider exampleEmoticonProvider;

    public static GifEmoticonProvider create() {
        if (exampleEmoticonProvider == null) {
            exampleEmoticonProvider = new GifEmoticonProvider();
        }
        return exampleEmoticonProvider;
    }

    private GifEmoticonProvider() {
    }

    @Override
    public HashMap<String, Integer> getEmoticons() {
        return EMOTICONS;
    }

}
