package com.hoanganhtuan95ptit.gifemoji.utils;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

/**
 * Created by Hoang Anh Tuan on 12/14/2017.
 */

final class Emoticon implements Parcelable {

    public static final Creator<Emoticon> CREATOR = new Creator<Emoticon>() {
        @Override
        public Emoticon createFromParcel(Parcel in) {
            return new Emoticon(in);
        }

        @Override
        public Emoticon[] newArray(int size) {
            return new Emoticon[size];
        }
    };

    @NonNull
    private final String unicode;

    @NonNull
    private @DrawableRes
    int icon;

    Emoticon(@NonNull String unicode, @DrawableRes int icon) {
        this.unicode = unicode;
        this.icon = icon;
    }

    private Emoticon(Parcel in) {
        this.icon = in.readInt();
        this.unicode = in.readString();

        if (unicode == null) throw new RuntimeException("Unicode cannot be null.");
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(icon);
        dest.writeString(unicode);
    }

    @DrawableRes
    int getIcon() {
        return icon;
    }

    @NonNull
    String getUnicode() {
        return unicode;
    }

    @Override
    public boolean equals(Object o) {
        return o == this || (o instanceof Emoticon && unicode.equals(((Emoticon) o).unicode));
    }

    @Override
    public int hashCode() {
        return unicode.hashCode();
    }
}
