package com.example.tpo_03;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("uppercase")
public class Uppercase implements Profiles {
    @Override
    public String implementProfile(String word) {
        return word.toUpperCase();
    }
}
