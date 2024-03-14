package com.example.tpo_02;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("original")
public class Original implements Profiles {
    @Override
    public void displayWords(List<Entry> entries) {
        for (Entry entry : entries) {
            System.out.println(entry.getEnglish() + " - " + entry.getGerman() + " - " + entry.getPolish());
        }
        System.out.println();
    }
}
