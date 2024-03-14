package com.example.tpo_02;

public class Entry {
    private String polish;
    private String english;
    private String german;

    public Entry(String english, String german, String polish) {
        this.polish = polish;
        this.english = english;
        this.german = german;
    }

    public String getPolish() {
        return polish;
    }

    public String getEnglish() {
        return english;
    }

    public String getGerman() {
        return german;
    }
}
