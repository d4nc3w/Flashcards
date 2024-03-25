package com.example.tpo_03;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("original")
public class Original implements Profiles {
    @Override
    public String implementProfile(String word) {
        return word;
    }
}
