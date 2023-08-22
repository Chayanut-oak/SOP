package com.example.newlab5;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;

@Route(value = "index2")
public class MyView2 extends HorizontalLayout {
    private TextField txt, txt2, txt3, txt4;
    private Button btn1, btn2, btn3, btn4;
    private ComboBox s1, s2;
    private VerticalLayout v1, v2;
    private Word word;
    private Notification notification;
    public MyView2() {
        notification = new Notification();
        word = new Word();
        txt = new TextField();
        txt.setLabel("Add Word");
        txt2 = new TextField();
        txt2.setLabel("Add Sentence");
        txt3 = new TextField();
        txt3.setLabel("Good Sentence");
        txt4 = new TextField();
        txt4.setLabel("Bad Sentence");
        btn1 = new Button("Add Good Word");
        btn2 = new Button("Add Bad Word");
        btn3 = new Button("Add Sentence");
        btn4 = new Button("Show Sentence");
        s1 = new ComboBox<>("Good Words");
        s2 = new ComboBox<>("Bad Words");
        v1 = new VerticalLayout();
        v2 = new VerticalLayout();
        txt.setWidthFull();
        txt2.setWidthFull();
        txt3.setWidthFull();
        txt4.setWidthFull();
        btn1.setWidthFull();
        btn2.setWidthFull();
        btn3.setWidthFull();
        btn4.setWidthFull();
        s1.setWidthFull();
        s2.setWidthFull();
        s1.setItems(word.getGoodWords());
        s2.setItems(word.getBadWords());
        v1.add(txt, btn1, btn2, s1, s2);
        v2.add(txt2, btn3, txt3, txt4, btn4);
        add(v1, v2);

        btn4.addClickListener(event -> {
            Sentence out = WebClient.create()
                    .get()
                    .uri("http://127.0.0.1:8081/getSentence")
                    .retrieve()
                    .bodyToMono(Sentence.class)
                    .block();
            txt3.setValue(out.goodSentences.toString());
            txt4.setValue(out.badSentences.toString());
        });

        btn3.addClickListener(event -> {
            String newword = txt2.getValue();

            ArrayList out = WebClient.create()
                    .post()
                    .uri("http://127.0.0.1:8081/proof/" + newword)
                    .retrieve()
                    .bodyToMono(ArrayList.class)
                    .block();
            boolean badword = false;
            boolean goodword = false;
            for (int i = 0; i < word.getGoodWords().size() ; i++) {
                goodword = newword.contains(word.getGoodWords().get(i));
                if(goodword){
                    break;
                }
            }
            for (int i = 0; i < word.getBadWords().size() ; i++) {
                badword = newword.contains(word.getBadWords().get(i));

                if(badword){
                    break;
                }
            }
            Notification notification1 = goodword ? notification.show("Found Good Word") : notification.show("Found Bad Word");

        });


        btn2.addClickListener(event -> {
            String newword = txt.getValue();

            ArrayList out = WebClient.create()
                    .post()
                    .uri("http://127.0.0.1:8081/addBad/" + newword)
                    .retrieve()
                    .bodyToMono(ArrayList.class)
                    .block();
            s2.setItems(out);
            notification.show("Insert "+newword+" to Bad Word List Complete.");
        });
        btn1.addClickListener(event -> {
            String newword = txt.getValue();

            ArrayList out = WebClient.create()
                    .post()
                    .uri("http://127.0.0.1:8081/addGood/" + newword)
                    .retrieve()
                    .bodyToMono(ArrayList.class)
                    .block();
            s1.setItems(out);
            notification.show("Insert "+newword+" to Good Word List Complete.");
        });
    }

}

