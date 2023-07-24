package com.example.memoserver2.Controller;

import com.example.memoserver2.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MemeController {
    @Autowired
    MemCardRepository memCardRepository;
    @Autowired
    SituationRepository situationRepository;
    @Autowired
    TableRepository tableRepository;

    @PostMapping("/getMemes")
    private List<MemCard> sendMemes(@RequestBody() User user){
        List<MemCard> memCards = memCardRepository.findAllByUsage(false);
        List<MemCard> forSending = new ArrayList<>();
        try {
            for(int i = 0; i < 7; i ++){
                int min = 0; // Minimum value of range
                int max = memCards.size() - 1; // Maximum value of range
                // Print the min and max
                System.out.println("Random value in int from "+ min + " to " + max + ":");
                // Generate random int value from min to max
                int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);
                forSending.add(memCards.get(random_int));
                MemCard memCard = memCards.get(random_int);
                memCard.setUsage(true);
                memCardRepository.save(memCard);
                memCards.remove(random_int);
            }
        } catch (Exception e){
            for(MemCard memCard : memCardRepository.findAll()){
                memCard.setUsage(false);
                memCardRepository.save(memCard);
            }

            memCards = memCardRepository.findAllByUsage(false);
            forSending = new ArrayList<>();

            for(int i = 0; i < 7; i ++){
                int min = 0; // Minimum value of range
                int max = memCards.size() - 1; // Maximum value of range
                // Print the min and max
                System.out.println("Random value in int from "+ min + " to " + max + ":");
                // Generate random int value from min to max
                int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);
                forSending.add(memCards.get(random_int));
                MemCard memCard = memCards.get(random_int);
                memCard.setUsage(true);
                memCardRepository.save(memCard);
                memCards.remove(random_int);
            }
        }
        return forSending;
    }

    @PostMapping("/getSituation")
    private String sendSituation(@RequestBody() User user){
        List<Situation> situations = situationRepository.findAll();
        int min = 0; // Minimum value of range
        int max = situations.size() - 1; // Maximum value of range
        // Print the min and max
        System.out.println("Random value in int from "+ min + " to " + max + ":");
        // Generate random int value from min to max
        int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);
        Situation situation =situations.get(random_int);
        Table table = tableRepository.findById(1).get();
        table.setSituation(situation.getText());
        tableRepository.save(table);
        return situation.getText();
    }

    @PostMapping("/setCard")
    private String setCard(@RequestBody() User user){
        Table table = tableRepository.findById(1).get();
        System.out.println(user);
        switch (user.getId()){
            case 1 -> table.setFirstCard(user.getName());
            case 2 -> table.setSecondCard(user.getName());
            case 3 -> table.setThirdCard(user.getName());
            case 4 -> table.setForthCard(user.getName());
        }
        tableRepository.save(table);
        return table.toString();
    }

    @PostMapping("/updateTable")
    private Table updateTable(@RequestBody() User user){
        Table table = tableRepository.findById(1).get();
        return table;
    }

//getMemes

    @PostMapping("/updateGame")
    private Table updateGame(@RequestBody() User user){
        Table table = tableRepository.findById(1).get();
        table.setForthCard(null);
        table.setThirdCard(null);
        table.setSecondCard(null);
        table.setFirstCard(null);
        table.setSituation(null);
        tableRepository.save(table);
        return table;
    }
}
