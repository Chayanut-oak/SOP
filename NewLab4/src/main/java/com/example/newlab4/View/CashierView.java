package com.example.newlab4.View;
import com.example.newlab4.Controller.Change;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.Route;
import org.springframework.web.reactive.function.client.WebClient;

@Route(value = "index2")
public class CashierView extends VerticalLayout {
    private Change change;
    private TextField txt,txt2,txt3,txt4,txt5,txt6,txt7,txt8;
    private Button btn1;
    public CashierView(){

        txt = new TextField();
        txt.setLabel("เงินทอน");
        txt.setPlaceholder("$");
        btn1 = new Button("คำนวณเงิน");
        txt2 = new TextField();
        txt2.setPrefixComponent(new Span("$1000:"));
        txt3 = new TextField();
        txt3.setPrefixComponent(new Span("$500:"));
        txt4 = new TextField();
        txt4.setPrefixComponent(new Span("$100:"));
        txt5 = new TextField();
        txt5.setPrefixComponent(new Span("$20:"));
        txt6 = new TextField();
        txt6.setPrefixComponent(new Span("$10:"));
        txt7 = new TextField();
        txt7.setPrefixComponent(new Span("$5:"));
        txt8 = new TextField();
        txt8.setPrefixComponent(new Span("$1:"));
        add(txt,btn1,txt2,txt3,txt4,txt5,txt6,txt7,txt8);
        btn1.addClickListener(event ->{
            Integer num1 = Integer.parseInt(txt.getValue());
            Change out = WebClient.create()
                    .get()
                    .uri("http://127.0.0.1:8080/getChange/"+num1)
                    .retrieve()
                    .bodyToMono(Change.class)
                    .block();
            txt2.setValue(String.valueOf(out.getB1000()));
            txt3.setValue(String.valueOf(out.getB500()));
            txt4.setValue(String.valueOf(out.getB100()));
            txt5.setValue(String.valueOf(out.getB20()));
            txt6.setValue(String.valueOf(out.getB10()));
            txt7.setValue(String.valueOf(out.getB5()));
            txt8.setValue(String.valueOf(out.getB1()));
        });
    }
}
