package com.example.newlab4.View;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.Route;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;




@Route(value = "index1")
public class MathView extends VerticalLayout {
    private TextField txt,txt2,txt3;
    private Button btn1,btn2,btn3,btn4,btn5,btn6;
    private HorizontalLayout hori;
    private Label l1;

    public MathView() {
        txt = new TextField();
        txt.setLabel("Number1");
        txt.setPlaceholder("Placeholder");
        txt2 = new TextField();
        txt2.setLabel("Number2");
        txt2.setPlaceholder("Placeholder");
        txt3 = new TextField();
        txt3.setLabel("Number3");
        txt3.setPlaceholder("Placeholder");
        l1 = new Label("Operator");
        hori = new HorizontalLayout();
        btn1 = new Button("+");
        btn2 = new Button("-");
        btn3 = new Button("x");
        btn4 = new Button("/");
        btn5 = new Button("mod");
        btn6 = new Button("max");

        FormLayout formLayout = new FormLayout();
        hori.add(btn1,btn2,btn3,btn4,btn5,btn6);
        add(txt,txt2,l1,hori,txt3);
        btn1.addClickListener(event ->{
            double num1 = Double.parseDouble(txt.getValue());
            double num2 = Double.parseDouble(txt2.getValue());

            String out = WebClient.create()
                    .get()
                    .uri("http://127.0.0.1:8080/plus/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            txt3.setValue(out);});
        btn2.addClickListener(event ->{
            double num1 = Double.parseDouble(txt.getValue());
            double num2 = Double.parseDouble(txt2.getValue());

            String out = WebClient.create()
                    .get()
                    .uri("http://127.0.0.1:8080/minus/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            txt3.setValue(out);});
        btn3.addClickListener(event ->{
            double num1 = Double.parseDouble(txt.getValue());
            double num2 = Double.parseDouble(txt2.getValue());

            String out = WebClient.create()
                    .get()
                    .uri("http://127.0.0.1:8080/multi/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            txt3.setValue(out);});
        btn4.addClickListener(event ->{
            double num1 = Double.parseDouble(txt.getValue());
            double num2 = Double.parseDouble(txt2.getValue());

            String out = WebClient.create()
                    .get()
                    .uri("http://127.0.0.1:8080/divide/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            txt3.setValue(out);});
        btn5.addClickListener(event ->{
            double num1 = Double.parseDouble(txt.getValue());
            double num2 = Double.parseDouble(txt2.getValue());

            String out = WebClient.create()
                    .get()
                    .uri("http://127.0.0.1:8080/mod/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            txt3.setValue(out);});

        btn6.addClickListener(event ->{
//            double num1 = Double.parseDouble(txt.getValue());
//            double num2 = Double.parseDouble(txt2.getValue());
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("n1", txt.getValue()); // key คือ n1, value คือ n1.getValue())
            formData.add("n2", txt2.getValue());
            String out = WebClient.create()
                    .post()
                    .uri("http://127.0.0.1:8080/max")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))

                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            txt3.setValue(out);});
        }
}
