package Mucsi.Animal;

import java.util.ArrayList;

public class Horse extends Animal {
    public Horse(String name, int weight, ArrayList<Integer> meals){
        super(name, weight, meals);
        scrawnyLimit = 60;
    }
}
