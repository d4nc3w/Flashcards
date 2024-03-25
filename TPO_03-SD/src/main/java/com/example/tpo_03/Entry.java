package com.example.tpo_03;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String polish;
    private String english;
    private String german;

    public Entry(String english, String german, String polish) {
        this.polish = polish;
        this.english = english;
        this.german = german;
    }

    public Entry() {

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
