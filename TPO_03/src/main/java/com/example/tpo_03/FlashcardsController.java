package com.example.tpo_03;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.*;

import static java.lang.System.exit;

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

    public void start() {
        PopulateDatabase();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while (choice != 8) {
            System.out.println("---------Flashcards Menu---------");
            System.out.println("Num: \t Operation:");
            System.out.println("(1) \t |Add a new word");
            System.out.println("(2) \t |Display all words ");
            System.out.println("(3) \t |Take a test ");
            System.out.println("(4) \t |Search a word ");
            System.out.println("(5) \t |Delete word");
            System.out.println("(6) \t |Modify word");
            System.out.println("(7) \t |Close the application");
            System.out.println("--------------------------------");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addNewWord();
                    break;
                case 2:
                    displayAllWords(sortWords());
                    break;
                case 3:
                    takeTest();
                    break;
                case 4:
                    searchWord();
                    break;
                case 5:
                    deleteWord();
                    break;
                case 6:
                    modifyWords();
                    break;
                case 7:
                    System.out.println("Goodbye!");
                    exit(0);
                default:
                    System.out.println("Please try again.");
                    break;
            }
        }
        scanner.close();
    }

    public void addNewWord() {
        System.out.println("-------Adding procedure-------");
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
        System.out.println("---------------------------------");
    }

    public void displayAllWords(List<Entry> sortedEntries) {
        int i = 1;
            if (sortedEntries.isEmpty()) {
                System.out.println("No words in the dictionary.");
            } else {
                System.out.println("----------Dictionary----------");
                for (Entry entry : sortedEntries) {
                    String profiledWord = i++ + ". " + profileInterface.implementProfile(entry.getEnglish()) +
                            " - " + profileInterface.implementProfile(entry.getPolish()) +
                            " - " + profileInterface.implementProfile(entry.getGerman());
                    System.out.println(profiledWord);
                }
                System.out.println("---------------------------------");
            }
    }

    public void takeTest() {
        List<Entry> entries = entryRepository.getAllEntries();
        if (entries.isEmpty()) {
            System.out.println("Warning! No words in the dictionary.");
        } else {
            System.out.println("-------------QUIZ-------------");
            Random random = new Random();
            Entry randomEntry = entries.get(random.nextInt(entries.size()));
            System.out.println("Translate the word: " + randomEntry.getPolish());
            Scanner scanner = new Scanner(System.in);
            System.out.print("Type english translation: ");
            String englishTranslation = scanner.nextLine();
            System.out.print("Type german translation: ");
            String germanTranslation = scanner.nextLine();

            if (englishTranslation.equalsIgnoreCase(randomEntry.getEnglish()) &&
                    germanTranslation.equalsIgnoreCase(randomEntry.getGerman())) {
                System.out.println("\tCorrect!");
                System.out.println("---------------------------------");
            } else {
                System.out.println("Incorrect. The answer is: English - " + randomEntry.getEnglish() +
                        ", German - " + randomEntry.getGerman());
                System.out.println("---------------------------------");
            }
        }
    }

    public String searchWord() {
        boolean isFound = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------Search Word Procedure--------");
        System.out.println("What word you want to search? Insert your word: ");
        String searchWord = scanner.next();
        System.out.println("In what language you inserted your word? (eng, pl, ger) - choose one: ");
        String langWord = scanner.next();

        List<Entry> entries = entryRepository.getAllEntries();
        for (Entry entry : entries) {
            if (langWord.equals("eng")) {
                if (entry.getEnglish().equals(searchWord)) {
                    System.out.println(entry.getEnglish() + " has been found!, pol: "
                            + entry.getPolish() + " ger: " + entry.getGerman());
                    System.out.println("---------------------------------");
                    isFound = true;
                    break;
                }
            } else if (langWord.equals("pl")) {
                if (entry.getPolish().equals(searchWord)) {
                    System.out.println(entry.getPolish() + " has been found!, eng: "
                            + entry.getEnglish() + " ger: " + entry.getGerman());
                    System.out.println("---------------------------------");
                    isFound = true;
                    break;
                }
            } else if (langWord.equals("ger")) {
                if (entry.getGerman().equals(searchWord)) {
                    System.out.println(entry.getGerman() + " has been found!, eng: "
                            + entry.getEnglish() + " pol: " + entry.getPolish());
                    System.out.println("---------------------------------");
                    isFound = true;
                    break;
                }
            } else {
                System.out.println("There is no such language as: " + langWord);
                System.out.println("---------------------------------");
            }
        }
        if (isFound == false) {
            System.out.println("Word " + searchWord + " is not present in dictionary");
            System.out.println("---------------------------------");
        }

        return searchWord;
    }

    public List<Entry> sortWords() {
        List<Entry> entries = entryRepository.getAllEntries();

        Scanner scanner = new Scanner(System.in);
        System.out.println("----------Sorting procedure----------");
        System.out.println("Select language to sort by (eng, pl, ger): ");
        String language = scanner.next();
        System.out.println("Select sorting order (asc, desc): ");
        String order = scanner.next();
        System.out.println("---------------------------------");

        Comparator<Entry> comparator = null;

        if (language.equals("eng")) {
           comparator = Comparator.comparing(Entry::getEnglish);
        }
        else if (language.equals("pl")) {
           comparator = Comparator.comparing(Entry::getPolish);
        }
        else if (language.equals("ger")) {
           comparator = Comparator.comparing(Entry::getGerman);
        }
        else {
            System.out.println("There is no " + language + "language in dictionary");
            System.out.println("---------------------------------");
        }
        if(comparator != null) {
            if (order.equals("asc")) {
                Collections.sort(entries, comparator);
            }
            else if(order.equals("desc")) {
                Collections.sort(entries, comparator.reversed());
            } else {
                System.out.println("You can't sort by " + order + " order");
                System.out.println("---------------------------------");
            }
        }
        return entries;
    }

    public void deleteWord() {
        List<Entry> entries = entryRepository.getAllEntries();
        boolean isDeleted = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------Deleting procedure------------");
        System.out.println("What word are you trying to delete? Type your word in: ");
        String deleteWord = scanner.next();
        System.out.println("In what language you inserted your word? (eng, pl, ger): ");
        String langWord = scanner.next();

        for (Entry entry : entries) {
            if (langWord.equals("eng")) {
                if (entry.getEnglish().equals(deleteWord)) {
                    System.out.println(entry.getEnglish() + " has been deleted as well as its translations, pol: "
                            + entry.getPolish() + " ger: " + entry.getGerman());
                    entryRepository.deleteEntry(entry);
                    System.out.println("---------------------------------");
                    isDeleted = true;
                    break;
                }
            } else if (langWord.equals("pl")) {
                if (entry.getPolish().equals(deleteWord)) {
                    System.out.println(entry.getPolish() + " has been deleted as well as its translations, eng: "
                            + entry.getEnglish() + " ger: " + entry.getGerman());
                    System.out.println("---------------------------------");
                    entryRepository.deleteEntry(entry);
                    isDeleted = true;
                    break;
                }
            } else if (langWord.equals("ger")) {
                if (entry.getGerman().equals(deleteWord)) {
                    System.out.println(entry.getGerman() + " has been deleted as well as its translations, eng: "
                            + entry.getEnglish() + " pol: " + entry.getPolish());
                    System.out.println("---------------------------------");
                    entryRepository.deleteEntry(entry);
                    isDeleted = true;
                    break;
                }
            } else {
                System.out.println("There is no such language as: " + langWord);
                System.out.println("---------------------------------");
            }
        }
        if (isDeleted == false) {
            System.out.println("Word " + deleteWord + " is not present in dictionary");
            System.out.println("---------------------------------");
        }
    }


    public void modifyWords() {
        boolean isModified = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------Modifying procedure------------");
        System.out.println("What word you want to modify? Insert your word: ");
        String modifyWord = scanner.next();
        System.out.println("In what language you inserted your word? (eng, pl, ger) - choose one ");
        String langWord = scanner.next();

        List<Entry> entries = entryRepository.getAllEntries();
        for(Entry entry : entries) {
            if (langWord.equals("eng")) {
                if (entry.getEnglish().equals(modifyWord)) {
                    System.out.println("Enter its english translation: ");
                    String eng = scanner.next();
                    System.out.println("Enter its german translation: ");
                    String ger = scanner.next();
                    System.out.println("Enter its polish translation: ");
                    String pol = scanner.next();
                    System.out.println("Word " + modifyWord + " has been modified to: " + eng + " - " + ger + " - " + pol);
                    System.out.println("---------------------------------");
                    entryRepository.deleteEntry(entry);
                    entryRepository.addEntry(new Entry(eng, ger, pol));
                    isModified = true;
                    break;
                }
            } else if (langWord.equals("pl")) {
                if (entry.getPolish().equals(modifyWord)) {
                    System.out.println("Enter its polish translation: ");
                    String pol = scanner.next();
                    System.out.println("Enter its german translation: ");
                    String ger = scanner.next();
                    System.out.println("Enter its english translation: ");
                    String eng = scanner.next();
                    System.out.println("Word " + modifyWord + " has been modified to: " + eng + " - " + ger + " - " + pol);
                    System.out.println("---------------------------------");
                    entryRepository.deleteEntry(entry);
                    entryRepository.addEntry(new Entry(eng, ger, pol));
                    isModified = true;
                    break;
                }
            } else if (langWord.equals("ger")) {
                if (entry.getGerman().equals(modifyWord)) {
                    System.out.println("Enter its german translation: ");
                    String ger = scanner.next();
                    System.out.println("Enter its english translation: ");
                    String eng = scanner.next();
                    System.out.println("Enter its polish translation: ");
                    String pol = scanner.next();
                    System.out.println("Word " + modifyWord + " has been modified to: " + eng + " - " + ger + " - " + pol);
                    System.out.println("---------------------------------");
                    entryRepository.deleteEntry(entry);
                    entryRepository.addEntry(new Entry(eng, ger, pol));
                    isModified = true;
                    break;
                }
            } else {
                System.out.println("There is no such language in dictionary: " + langWord);
                System.out.println("---------------------------------");
            }
        } if (isModified == false) {
            System.out.println("Given: " + modifyWord + " is not present in the dictionary");
            System.out.println("---------------------------------");
        }
    }

    public void PopulateDatabase() {
        EntityManager entityManager = entryRepository.getEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM Entry e");
        Long count = (long) query.getResultList().size();

        if(count == 0) {
            System.out.println("Database is empty, populating database and List...");
            fileServ.readEntriesFromFile();
        }
        else {
            System.out.println("Database is not empty, populating List only...");
            fileServ.readEntriesFromFileAddToListOnly();
        }
    }
}
