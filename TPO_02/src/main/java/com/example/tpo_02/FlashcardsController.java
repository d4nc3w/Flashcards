package com.example.tpo_02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Controller
public class FlashcardsController {
    private EntryRepository entryRepository;
    private Profiles profileInterface;
    private FileServ fileServ;

    @Autowired
    public FlashcardsController(EntryRepository entryRepository, Profiles profileInterface, FileServ fileServ) {
        this.entryRepository = entryRepository;
        this.profileInterface = profileInterface;
        this.fileServ = fileServ;
    }

    public void displayMenu() {
        System.out.println("1. Add a new word");
        System.out.println("2. Display all words");
        System.out.println("3. Take a test");
        System.out.println("4. Exit");
    }

    public void start() {
        fileServ.readEntriesFromFile();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while (choice != 4) {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addNewWord();
                    break;
                case 2:
                    displayAllWords();
                    break;
                case 3:
                    takeTest();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        scanner.close();
    }

    public void addNewWord() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter English translation: ");
        String english = scanner.nextLine();
        System.out.print("Enter German translation: ");
        String german = scanner.nextLine();
        System.out.print("Enter Polish translation: ");
        String polish = scanner.nextLine();

        Entry newEntry = new Entry(english, german, polish);
        entryRepository.addEntry(newEntry);
        System.out.println("Word added successfully!");
    }

    public void displayAllWords() {
        List<Entry> entries = entryRepository.getAllEntries();
        if (entries.isEmpty()) {
            System.out.println("No words in the dictionary.");
        } else {
            System.out.println("All words in the dictionary:");
            profileInterface.displayWords(entries);
        }
    }

    public void takeTest() {
        List<Entry> entries = entryRepository.getAllEntries();
        if (entries.isEmpty()) {
            System.out.println("No words in the dictionary to take a test.");
        } else {
            Random random = new Random();
            Entry randomEntry = entries.get(random.nextInt(entries.size()));
            System.out.println("Translate the word: " + randomEntry.getPolish());
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the English translation: ");
            String englishTranslation = scanner.nextLine();
            System.out.print("Enter the German translation: ");
            String germanTranslation = scanner.nextLine();

            if (englishTranslation.equalsIgnoreCase(randomEntry.getEnglish()) &&
                    germanTranslation.equalsIgnoreCase(randomEntry.getGerman())) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect. The correct translations are: English - " + randomEntry.getEnglish() +
                        ", German - " + randomEntry.getGerman());
            }
        }
    }
}
