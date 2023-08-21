package com.example.newlab5;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SentenceConsumer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public Sentence sentences;

    public SentenceConsumer() {
        sentences = new Sentence();
    }
    @RabbitListener(queues = "GetQueue")
    public Sentence getSentencs(){
        return sentences;
    }
    @RabbitListener(queues = "BadWordQueue")

    public void addBadSentence(String s) {
        sentences.getBadSentences().add(s);
        System.out.println("In addBadSentence Method : "+sentences.getBadSentences());
    }

    @RabbitListener(queues = "GoodWordQueue")
    public void addGoodSentence(String s) {
        sentences.getGoodSentences().add(s);
        System.out.println("In addGoodSentence Method : "+sentences.getGoodSentences());
    }
}
