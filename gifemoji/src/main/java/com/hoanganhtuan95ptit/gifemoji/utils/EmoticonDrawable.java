package com.hoanganhtuan95ptit.gifemoji.utils;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by HOANG ANH TUAN on 7/9/2017.
 */

class EmoticonDrawable extends AnimationDrawable {

    private Context context;
    private int drawableRes;
    private int textSize;

    private int position;
    private int frameNum;
    private int delay;

    public static EmoticonDrawable create(Context context, @DrawableRes int drawableRes, int textSize) {
        EmoticonDrawable emoticonDrawable = EmoticonCache.getEmoticonDrawable(drawableRes, textSize);
        if (emoticonDrawable == null)
            emoticonDrawable = new EmoticonDrawable(context, drawableRes, textSize);
        return emoticonDrawable;
    }

    private EmoticonDrawable(Context context, @DrawableRes int drawableRes, int textSize) {
        this.context = context;
        this.drawableRes = drawableRes;
        this.textSize = textSize;
        init();
        EmoticonCache.saveEmoticonDrawable(drawableRes, textSize, this);
    }

    private void init() {
        EmoticonDecoder emoticonDecoder = EmoticonDecoder.create(context, drawableRes, textSize);
        frameNum = emoticonDecoder.frameNum();
        delay = emoticonDecoder.getDelay();
        for (int i = 0; i < frameNum; i++) {
            Drawable drawable = EmoticonCache.getDrawable(drawableRes, i);
            int width = (textSize * drawable.getIntrinsicWidth()) / drawable.getIntrinsicHeight();
            addFrame(drawable, delay);
            if (i == 0) {
                setBounds(0, 0, width, textSize);
            }
        }
    }

    void animation() {
        if (delay > 0 && frameNum > 0)
            Observable
                    .interval(delay, TimeUnit.MILLISECONDS)
                    .flatMap(new Function<Long, ObservableSource<Long>>() {
                        @Override
                        public ObservableSource<Long> apply(Long aLong) throws Exception {
                            return Observable.just(aLong);
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Long>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(Long drawable) {
                            position++;
                            if (position >= frameNum) position = 0;
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
    }

    Drawable getDrawable() {
        return getFrame(position);
    }

}
