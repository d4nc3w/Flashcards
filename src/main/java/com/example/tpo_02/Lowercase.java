package com.example.tpo_02;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("lowercase")
public class Lowercase implements Profiles {
    @Override
    public void displayWords(List<Entry> entries) {
        for (Entry entry : entries) {
            System.out.println(entry.getEnglish().toLowerCase() + " - " + entry.getGerman().toLowerCase()
                    + " - " + entry.getPolish().toLowerCase());
        }
        System.out.println();
    }
}
