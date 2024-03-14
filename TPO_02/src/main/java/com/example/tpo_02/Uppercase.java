package com.example.tpo_02;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("uppercase")
public class Uppercase implements Profiles {
    @Override
    public void displayWords(List<Entry> entries) {
        for (Entry entry : entries) {
            System.out.println(entry.getEnglish().toUpperCase() + " - " + entry.getGerman().toUpperCase()
                    + " - " + entry.getPolish().toUpperCase());
        }
        System.out.println();
    }
}
