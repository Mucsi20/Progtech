package Mucsi.Animal;

import java.util.ArrayList;

public class Goat extends Animal {
    public Goat(String name, int weight, ArrayList<Integer> meals){
        super(name, weight, meals);
        scrawnyLimit = 12;
    }
}
