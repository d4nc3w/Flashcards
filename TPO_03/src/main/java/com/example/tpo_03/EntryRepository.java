package com.example.tpo_03;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EntryRepository  {
    private final EntityManager entityManager;
    private List<Entry> entries = new ArrayList<>();

    public EntryRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void addEntry(Entry entry) {
        entityManager.persist(entry);
        entries.add(entry);
    }

    public Optional findById(Long id) {
        return Optional.ofNullable(entityManager.find(Entry.class, id));
    }

    public void deleteById(Long id) {
        findById(id).ifPresent(entityManager::remove);
    }

    public List<Entry> getAllEntries() {
        return new ArrayList<>(entries);
    }

    @Transactional
    public void deleteEntry(Entry entry) {
        Entry managedEntry = entityManager.merge(entry);
        entries.remove(entry);
        entityManager.remove(managedEntry);
    }

    /*@Transactional
    public Entry update(Entry entry) throws EntryNotFoundException {
        try {
            Entry dbentry = (Entry) findById(entry.getIdOfEntry())
                    .orElseThrow(EntryNotFoundException::new);
            dbentry.setPolish(entry.getPolish());
            dbentry.setEnglish(entry.getEnglish());
            dbentry.setGerman(entry.getGerman());
        } catch (Throwable e) {
            throw new EntryNotFoundException();
        }
        return entry;
    }*/
    public void addEntryToList(Entry entry) {
        entries.add(entry);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
