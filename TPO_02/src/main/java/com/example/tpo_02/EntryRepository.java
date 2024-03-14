package com.example.tpo_02;

import java.util.List;

public interface EntryRepository {
    List<Entry> getAllEntries();
    void addEntry(Entry entry);
}
