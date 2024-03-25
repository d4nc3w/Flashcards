package com.example.tpo_03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Tpo03Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Tpo03Application.class, args);

        EntryRepository entryRepository = context.getBean(EntryRepository.class);

      /*  Entry entry1 = new Entry("english", "german", "polish");
        Entry entry2 = new Entry("english", "german", "polish");

        entryRepository.addEntry(entry1);
        entryRepository.addEntry(entry2);

        entry1.setGerman("notgerman");*/

        FlashcardsController controller = context.getBean(FlashcardsController.class);
        controller.start();

        /*try {
            entryRepository.update(entry1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
        /*System.out.println(entryRepository.findById(1L));
        entryRepository.deleteById(1L);*/
    }
}
