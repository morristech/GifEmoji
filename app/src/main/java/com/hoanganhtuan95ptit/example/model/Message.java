package com.hoanganhtuan95ptit.example.model;

import java.io.Serializable;

/**
 * Created by HOANG ANH TUAN on 7/24/2017.
 */

public class Message implements Serializable {

    private String info;
    private MessageSlide slide;

    public Message(String info, MessageSlide slide) {
        this.info = info;
        this.slide = slide;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public MessageSlide getSlide() {
        return slide;
    }

    public void setSlide(MessageSlide slide) {
        this.slide = slide;
    }

    public enum MessageSlide {
        LEFT, RIGHT
    }

}
