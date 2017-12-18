package com.hoanganhtuan95ptit.gifemoji.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Hoang Anh Tuan on 12/14/2017.
 */

final class EmoticonUtils {

    static void replaceWithImages(@NonNull final Context context,
                                  @NonNull final Spannable text,
                                  @NonNull final EmoticonProvider emoticonProvider,
                                  final int emoticonSize) {

        final EmoticonSpan[] existingSpans = text.getSpans(0, text.length(), EmoticonSpan.class);
        final ArrayList<Integer> existingSpanPositions = new ArrayList<>(existingSpans.length);
        for (EmoticonSpan existingSpan : existingSpans) {
            existingSpanPositions.add(text.getSpanStart(existingSpan));
        }

        final List<EmoticonRange> findAllEmojis = findAllEmoticons(text, emoticonProvider);

        for (int i = 0; i < findAllEmojis.size(); i++) {
            final EmoticonRange location = findAllEmojis.get(i);
            if (!existingSpanPositions.contains(location.mStartPos)) {
                text.setSpan(EmoticonSpan.create(context, location.mEmoticon.getIcon(), emoticonSize),
                        location.mStartPos,
                        location.mEndPos,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }

    @NonNull
    private static List<EmoticonRange> findAllEmoticons(@Nullable final CharSequence text,
                                                        @NonNull final EmoticonProvider emoticonProvider) {
        final List<EmoticonRange> result = new ArrayList<>();

        if (!TextUtils.isEmpty(text)) {
            try {
                for (Map.Entry<String, Integer> entry : emoticonProvider.getEmoticons().entrySet()) {
                    String unicode = entry.getKey();
                    Integer icon = entry.getValue();
                    final Matcher matcher = Pattern.compile(Pattern.quote(unicode)).matcher(text);
                    while (matcher.find()) {
                        if (emoticonProvider.getEmoticons().containsKey(unicode)) {
                            final Emoticon found = new Emoticon(unicode, icon);
                            result.add(new EmoticonRange(matcher.start(), matcher.end(), found));
                        }
                    }
                }
            } catch (Exception ignored) {
            }
//            final Matcher matcher = getRegex(emoticonProvider).matcher(text);
//            while (matcher.find()) {
//                String unicode = text.subSequence(matcher.start(), matcher.end()).toString();
//                if (emoticonProvider.hasEmoticonIcon(unicode)) {
//
//                    String icon = emoticonProvider.getIcon(unicode);
//                    final Emoticon found = new Emoticon(unicode, icon);
//
//                    result.add(new EmoticonRange(matcher.start(), matcher.end(), found));
//                }
//            }
        }

        return result;
    }

//    @NonNull
//    private static Pattern getRegex(EmoticonProvider emoticonProvider) {
//        if (sRegexPattern == null) {
//            sRegexPattern = Pattern.compile(emoticonProvider.getRegex());
//        }
//        return sRegexPattern;
//    }

    private static final class EmoticonRange {

        final int mStartPos;

        final int mEndPos;

        final Emoticon mEmoticon;

        private EmoticonRange(final int start,
                              final int end,
                              @NonNull final Emoticon emoticon) {
            this.mStartPos = start;
            this.mEndPos = end;
            this.mEmoticon = emoticon;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            final EmoticonRange that = (EmoticonRange) o;
            return mStartPos == that.mStartPos
                    && mEndPos == that.mEndPos
                    && mEmoticon.equals(that.mEmoticon);
        }

        @Override
        public int hashCode() {
            int result = mStartPos;
            result = 31 * result + mEndPos;
            result = 31 * result + mEmoticon.hashCode();
            return result;
        }
    }
}
