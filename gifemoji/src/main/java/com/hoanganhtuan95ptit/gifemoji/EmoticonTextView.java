package com.hoanganhtuan95ptit.gifemoji;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;

import com.hoanganhtuan95ptit.gifemoji.utils.EmoticonProvider;
import com.hoanganhtuan95ptit.gifemoji.utils.EmoticonSpannableStringBuilder;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Hoang Anh Tuan on 12/14/2017.
 */

public class EmoticonTextView extends AppCompatTextView {
    private int mEmoticonSize;

    @Nullable
    private EmoticonProvider mEmoticonProvider;

    public EmoticonTextView(@NonNull Context context) {
        super(context);
        mEmoticonSize = (int) getTextSize();
        init(null);
    }

    public EmoticonTextView(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public EmoticonTextView(@NonNull Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(@Nullable final AttributeSet attrs) {
        int mSchedule;
        if (attrs == null) {
            mEmoticonSize = (int) getTextSize();
            mSchedule = 40;
        } else {
            @SuppressLint("CustomViewStyleable")
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.EmoticonTextView);
            mEmoticonSize = (int) a.getDimension(R.styleable.EmoticonTextView_emoticonSize, getTextSize());
            mSchedule = a.getInteger(R.styleable.EmoticonTextView_emoticonSchedule, 40);
            a.recycle();
        }

        Observable
                .interval(mSchedule, TimeUnit.MILLISECONDS)
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
                        invalidate();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public final void setTextEmoticon(String text) {
        if (mEmoticonProvider != null) {
            setText(text);
            Observable.just(text)
                    .map(new Function<String, SpannableStringBuilder>() {
                        @Override
                        public SpannableStringBuilder apply(String text) throws Exception {
                            return EmoticonSpannableStringBuilder.create(getContext(), mEmoticonProvider, mEmoticonSize, text);
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<SpannableStringBuilder>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(SpannableStringBuilder spannableStringBuilder) {
                            setText(spannableStringBuilder);
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        }
    }

    @CallSuper
    public final void setEmoticonSize(final int pixels) {
        mEmoticonSize = pixels;
    }

    @CallSuper
    public final void setEmoticonProvider(@Nullable final EmoticonProvider emoticonProvider) {
        mEmoticonProvider = emoticonProvider;
    }
}