package com.example.newlab5;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
@Service
@RestController
public class WordPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    protected Word word;

    public WordPublisher() {
        word = new Word();
    }

    @PostMapping(value = "/addBad/{s}")

    public ArrayList<String> addBadWord(@PathVariable String s) {
        word.getBadWords().add(s);
        return word.getBadWords();
    }

    @DeleteMapping(value = "/delBad/{s}")
    public ArrayList<String> deleteBadWord(@PathVariable String s) {
        word.getBadWords().remove(s);
        return word.getBadWords();
    }

    @PostMapping(value = "/addGood/{s}")
    public ArrayList<String> addGoodWord(@PathVariable String s) {
        word.getGoodWords().add(s);
        System.out.println(word.getGoodWords());
        return word.getGoodWords();
    }

    @DeleteMapping(value = "/delGood/{s}")
    public ArrayList<String> deleteGoodWord(@PathVariable String s) {
        word.getGoodWords().remove(s);
        return word.getGoodWords();
    }

    @PostMapping(value = "/proof/{s}")
    public void proofSentence(@PathVariable String s) {
        boolean badword = false;
        boolean goodword = false;
        for (int i = 0; i < word.getGoodWords().size() ; i++) {
            goodword = s.contains(word.getGoodWords().get(i));
            if(goodword){
                break;
            }
        }
        for (int i = 0; i < word.getBadWords().size() ; i++) {
            badword = s.contains(word.getBadWords().get(i));

            if(badword){
                break;
            }
        }
        if (goodword && badword) {
            rabbitTemplate.convertAndSend("Fanout", "", s);

        } else if (goodword) {
            rabbitTemplate.convertAndSend("Direct", "good", s);
        } else if (badword) {
            rabbitTemplate.convertAndSend("Direct", "bad", s);
        }
        System.out.println(s);
    }
    @GetMapping(value = "/getSentence")
    public Sentence getSentence(){
       return (Sentence) rabbitTemplate.convertSendAndReceive("Direct", "word", "");

    }
}
