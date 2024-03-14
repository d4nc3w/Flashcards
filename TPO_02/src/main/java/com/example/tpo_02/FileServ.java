package com.example.tpo_02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileServ implements FileService {
    private final EntryRepository entryRepository;
    private final String filename;

    @Autowired
    public FileServ(EntryRepository entryRepository, @Value("${pl.edu.pja.tpo02.filename}") String filename) {
        this.filename = filename;
        this.entryRepository = entryRepository;
    }
    @Override
    public List<Entry> readEntriesFromFile() {
        List<Entry> entries = new ArrayList<>();
        try (InputStream inputStream = new ClassPathResource(filename).getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_16))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    Entry entry = new Entry(parts[0], parts[1], parts[2]);
                    entries.add(entry);
                    entryRepository.addEntry(entry);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entries;
    }

}
