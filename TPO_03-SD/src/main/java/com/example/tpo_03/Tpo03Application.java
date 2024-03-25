package com.example.tpo_03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Tpo03Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Tpo03Application.class, args);
        SpringDataEntryRepository entryRepository = context.getBean(SpringDataEntryRepository.class);

        /*Entry entry = new Entry("english", "german", "polish");
        Entry entry2 = new Entry("english2", "german2", "polish2");

        entryRepository.save(entry);
        entryRepository.save(entry2);

        System.out.println(entryRepository.findById(1L));
        entryRepository.deleteById(1L);

        for (Entry e : entryRepository.findAll()) {
            System.out.println(e.getEnglish());
        }*/

        FlashcardsController controller = context.getBean(FlashcardsController.class);
        controller.start();
    }
}
