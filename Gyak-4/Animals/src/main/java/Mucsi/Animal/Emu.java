package Mucsi.Animal;

import java.util.ArrayList;

public class Emu extends Animal {
    public Emu(String name, int weight, ArrayList<Integer> meals){
        super(name, weight, meals);
        scrawnyLimit = 20;
    }

}
