package com.example.newlab5;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Word implements Serializable {
    public ArrayList<String> badWords;
    public ArrayList<String> goodWords;

    public Word() {
        badWords = new ArrayList<String>();
        goodWords = new ArrayList<String>();
        badWords.add("fuck");
        badWords.add("olo");
        goodWords.add("happy");
        goodWords.add("enjoy");
        goodWords.add("life");
    }

    public ArrayList<String> getBadWords() {
        return badWords;
    }

    public void setBadWords(ArrayList<String> badWords) {
        this.badWords = badWords;
    }

    public ArrayList<String> getGoodWords() {
        return goodWords;
    }

    public void setGoodWords(ArrayList<String> goodWords) {
        this.goodWords = goodWords;
    }
}
