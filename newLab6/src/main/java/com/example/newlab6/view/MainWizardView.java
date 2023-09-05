package com.example.newlab6.view;

import com.example.newlab6.pojo.Wizard;
import com.example.newlab6.pojo.Wizards;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Route("mainPage.it")
public class MainWizardView extends VerticalLayout {
    private TextField txt, txt2;
    private List<Wizard> out;
    private int current;
    private RadioButtonGroup ra;
    private ComboBox c1, c2, c3;
    private Button btn, btn2, btn3, btn4, btn5;
    private HorizontalLayout h1;
    private Wizards wizards;
    private Notification notification;
    public MainWizardView() {
        txt = new TextField();
        txt.setPlaceholder("Fullname");
        txt2 = new TextField();
        txt2.setLabel("Dollars");
        txt2.setPlaceholder("$");
        ra = new RadioButtonGroup("Gender:", "Male", "Female");
        c1 = new ComboBox();
        c1.setPlaceholder("Position");
        c2 = new ComboBox<>();
        c2.setPlaceholder("School");
        c3 = new ComboBox<>();
        c3.setPlaceholder("House");
        c1.setItems("Student","Teacher");
        c2.setItems("Hogwarts","Beauxbatons","Durmstrang");
        c3.setItems("Gryffindor","Ravenclaw","Hufflepuff","Slyther");
        h1 = new HorizontalLayout();
        btn = new Button("<<");
        btn2 = new Button("Create");
        btn3 = new Button("Update");
        btn4 = new Button("Delete");
        btn5 = new Button(">>");
        h1.add(btn,
                btn2,
                btn3,
                btn4,
                btn5);
        add(txt,ra,c1,txt2,c2,c3,h1);
        btn.addClickListener(event ->{
           current = (((--current % out.size()) + out.size()) % out.size());
            setnew();

           });
        btn5.addClickListener(event ->{
            current = (((++current % out.size()) + out.size()) % out.size());
            setnew();
        });

        btn2.addClickListener(event ->{

            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("sex", ra.getValue().toString().equals("Male")?"m":"f"  ); // key คือ n1, value คือ n1.getValue())
            formData.add("name", txt.getValue());
            formData.add("school", c2.getValue().toString());
            formData.add("house", c3.getValue().toString());
            formData.add("money", txt2.getValue());
            formData.add("position", c1.getValue().toString());
            String out = WebClient.create()
                    .post()
                    .uri("http://127.0.0.1:8080/addWizard")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))

                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            Notification notification1 = notification.show("Wizard has been Created");
            load();
            });
        btn3.addClickListener(event ->{

            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("_id", out.get(current).get_id());
            formData.add("sex", ra.getValue().toString().equals("Male")?"m":"f"  ); // key คือ n1, value คือ n1.getValue())
            formData.add("name", txt.getValue());
            formData.add("school", c2.getValue().toString());
            formData.add("house", c3.getValue().toString());
            formData.add("money", txt2.getValue());
            formData.add("position", c1.getValue().toString());
            String out = WebClient.create()
                    .post()
                    .uri("http://127.0.0.1:8080/updateWizard")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))

                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            Notification notification1 = notification.show("Wizard has been Updated");
            load();
        });
        btn4.addClickListener(event ->{

            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("_id", out.get(current).get_id());

            String newval = WebClient.create()
                    .post()
                    .uri("http://127.0.0.1:8080/deleteWizard")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))

                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            current = (((--current % out.size()) + out.size()) % out.size());
            txt.setValue(out.get(current).getName());
            if(out.get(current).getSex().equals("m")){
                ra.setValue("Male");
            }else if(out.get(current).getSex().equals("f")){
                ra.setValue("Female");
            }else{
                ra.setValue("");
            }

            c1.setValue(out.get(current).getPosition());
            txt2.setValue(out.get(current).getMoney()+"");
            c2.setValue(out.get(current).getSchool());
            c3.setValue(out.get(current).getHouse());

            Notification notification1 = notification.show("Wizard has been remove");
            load();
        });

        load();
    }
    public void load(){
       out = WebClient.create()
                .get()
                .uri("http://127.0.0.1:8080/wizards")
                .retrieve()
                .bodyToFlux(Wizard.class)
                 .collectList()
                .block();
    }
    public void setnew(){
        txt.setValue(out.get(current).getName());
        if(out.get(current).getSex().equals("m")){
            ra.setValue("Male");
        }else if(out.get(current).getSex().equals("f")){
            ra.setValue("Female");
        }else{
            ra.setValue("");
        }

        c1.setValue(out.get(current).getPosition());
        txt2.setValue(out.get(current).getMoney()+"");
        c2.setValue(out.get(current).getSchool());
        c3.setValue(out.get(current).getHouse());
    }
    }


