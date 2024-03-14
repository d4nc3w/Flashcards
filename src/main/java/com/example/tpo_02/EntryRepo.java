package com.example.tpo_02;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EntryRepo implements EntryRepository {
    private List<Entry> entries = new ArrayList<>();

    @Override
    public List<Entry> getAllEntries() {
        return new ArrayList<>(entries);
    }

    @Override
    public void addEntry(Entry entry) {
        entries.add(entry);
    }
}
