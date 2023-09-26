package com.example.newlab7.view;

import com.example.newlab7.pojo.Product;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;


@Route("mainPage.it")
public class ProductView extends VerticalLayout {
    private TextField txt;
    private ComboBox c1;
    private List<Product> out;
    private  Product out2;
    private int current;
    private NumberField n1,n2,n3;
    private Button b1,b2,b3,b4;
    private HorizontalLayout h1;
    public ProductView(){

        txt = new TextField("Product Name:");
        c1 = new ComboBox("Product List");
        n1 = new NumberField("Product Cost:");
        n1.setValue(0.0);
        n2 = new NumberField("Product Profit:");
        n2.setValue(0.0);
        n3 = new NumberField("Product Price");
        n3.setValue(0.0);
        b1 = new Button("Add Product");
        b2 = new Button("Update Product");
        b3 = new Button("Delete Product");
        b4 = new Button("Clear Product");
        h1 = new HorizontalLayout();
        n3.setEnabled(false);
        h1.add(b1,b2,b3,b4);
        add(c1,txt,n1,n2,n3,h1);

        b1.addClickListener(event ->{
            getPrice();
        Product product = new Product(null , txt.getValue(),n1.getValue(),n2.getValue(),n3.getValue());
            boolean out = WebClient.create()
                    .post()
                    .uri("http://127.0.0.1:8080/addProduct")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(product)
                    .retrieve()
                    .bodyToMono(boolean.class)
                    .block();
            load();
        });
        b2.addClickListener(event ->{
            if(out2 != null){
                getPrice();
                Product product = new Product(out2.get_Id() , txt.getValue(),n1.getValue(),n2.getValue(),n3.getValue());
                 out = WebClient.create()
                        .post()
                        .uri("http://127.0.0.1:8080/updateProduct")
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(product)
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<List<Product>>() {})
                        .block();
            }
            load();
        });
        b3.addClickListener(event ->{

                String id = out2.get_Id();
                boolean out = WebClient.create()
                        .post()
                        .uri("http://127.0.0.1:8080/deleteProduct/"+id)
                        .retrieve()
                        .bodyToMono(boolean.class)
                        .block();
            txt.setValue("");
            n1.setValue(0.0);
            n2.setValue(0.0);
            n3.setValue(0.0);
                load();
        });
        b4.addClickListener(event ->{
            clear();
        });
        c1.addValueChangeListener(event ->{
            String name = c1.getValue() != null ? c1.getValue().toString():null ;
            out2 = WebClient.create()
                    .post()
                    .uri("http://127.0.0.1:8080/productsbyname/"+name)
                    .retrieve()
                    .bodyToMono(Product.class)
                    .block();
            if(out2 != null){
               txt.setValue(out2.getProductName());
               n1.setValue(out2.getProductCost());
               n2.setValue(out2.getProductProfit());
               n3.setValue(out2.getProductPrice());
            }
        });
        n1.addKeyPressListener(Key.ENTER,e ->{
            getPrice();
        });

        n2.addKeyPressListener(Key.ENTER,e ->{
            getPrice();
        });
        load();
    }
    public void load(){

        out = WebClient.create()
                .get()
                .uri("http://localhost:8080/products")
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<List<Product>>() {})
                        .block();

        List<String> productNames = out.stream()
                .map(Product::getProductName)
                .collect(Collectors.toList());
        c1.setItems(productNames);
    }
    public void getPrice(){
        Double cost = n1.getValue();
        Double prof = n2.getValue();
        Double out = WebClient.create()
                .get()
                .uri("http://127.0.0.1:8080/getPrice/"+cost+"/"+prof)
                .retrieve()
                .bodyToMono(Double.class)
                .block();
        n3.setValue(out);
    }
    public void clear(){
            txt.setValue("");
            n1.setValue(0.0);
            n2.setValue(0.0);
            n3.setValue(0.0);
            load();
    }
}
