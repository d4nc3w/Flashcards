package com.example.tpo_03;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Entry {
    @Id
    private Long idOfEntry;
    private String polish;
    private String english;
    private String german;

    public Entry() {}

    public Entry(String english, String german, String polish) {
        this.idOfEntry = IdGenerator.generateId();
        this.polish = polish;
        this.english = english;
        this.german = german;
    }

    public Long getIdOfEntry() {
        return idOfEntry;
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

    /*
    public void setEnglish(String english) {
        this.english = english;
    }
    public void setGerman(String german) {
        this.german = german;
    }
    public void setPolish(String polish) {
        this.polish = polish;
    }
    */

    @Override
    public String toString() {
        return "Entry{" +
                "idOfEntry=" + idOfEntry +
                ", polish='" + polish + '\'' +
                ", english='" + english + '\'' +
                ", german='" + german +
                '}';
    }

    /*public Entry(Long idOfEntry, String english, String german, String polish) {
        this.idfBook = idOfEntry;
        this.polish = polish;
        this.english = english;
        this.german = german;
    }*/
}
