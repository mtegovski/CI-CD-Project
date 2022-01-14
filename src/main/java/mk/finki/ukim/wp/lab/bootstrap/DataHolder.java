package mk.finki.ukim.wp.lab.bootstrap;

import mk.finki.ukim.wp.lab.model.Balloon;
import mk.finki.ukim.wp.lab.model.Manufacturer;
import mk.finki.ukim.wp.lab.model.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Balloon> baloons = new ArrayList<>();
    public static List<Manufacturer> manufacturers = new ArrayList<>();
    public static List<Order> orders = new ArrayList<>();

//    @PostConstruct
//    public void init(){
//        manufacturers.add(new Manufacturer("Nike","Serbia","Belgrade"));
//        manufacturers.add(new Manufacturer("Adidas","Croatia","Zagreb"));
//
//        baloons.add(new Balloon("Black","The color black",manufacturers.get(0)));
//        baloons.add(new Balloon("White","The color white",manufacturers.get(0)));
//        baloons.add(new Balloon("Red","The color red",manufacturers.get(0)));
//        baloons.add(new Balloon("Blue","The color blue",manufacturers.get(0)));
//        baloons.add(new Balloon("Green","The color green",manufacturers.get(0)));
//        baloons.add(new Balloon("Yellow","The color yellow",manufacturers.get(1)));
//        baloons.add(new Balloon("Pink","The color pink",manufacturers.get(1)));
//        baloons.add(new Balloon("Purple","The color purple",manufacturers.get(1)));
//        baloons.add(new Balloon("Orange","The color orange",manufacturers.get(1)));
//        baloons.add(new Balloon("Cyan","The color cyan",manufacturers.get(1)));
//
//    }
}
