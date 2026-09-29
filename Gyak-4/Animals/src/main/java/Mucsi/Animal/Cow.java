package Mucsi.Animal;

import java.util.ArrayList;

public class Cow extends Animal {
    public Cow(String name, int weight, ArrayList<Integer> meals){
        super(name, weight, meals);
        scrawnyLimit = 100;
    }
}
