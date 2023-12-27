package com.yin.yzjcourse.bean;

import java.io.Serializable;

public class EnglishSentenceEntity implements Serializable {
    public String chinese;
    public String english;
    public String date;

    public EnglishSentenceEntity(String chinese, String english, String date) {
        this.chinese = chinese;
        this.english = english;
        this.date = date;
    }
}
