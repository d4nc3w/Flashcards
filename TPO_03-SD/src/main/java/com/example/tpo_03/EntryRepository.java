package com.example.tpo_03;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EntryRepository {
    private List<Entry> entries = new ArrayList<>();

    public List<Entry> getAllEntries() {
        return new ArrayList<>(entries);
    }

    public void addEntry(Entry entry) {
        entries.add(entry);
    }

    public void deleteEntry(Entry entry) {
        entries.remove(entry);
    }
}
