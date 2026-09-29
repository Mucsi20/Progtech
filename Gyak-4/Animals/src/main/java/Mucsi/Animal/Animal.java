package Mucsi.Animal;

import java.util.ArrayList;

public abstract class Animal {

    public Boolean scrawny;
    public int scrawnyLimit;

    private String name;
    private int weight;
    private ArrayList<Integer> meals;

    public Animal(String name, int weight, ArrayList<Integer> meals){
        this.name = name;
        this.weight = weight;
        this.meals = meals;
        setScrawny(scrawnyLimit);
    }

    public int sumOfMeals(){
        int sum = 0;
        for (Integer meal : meals){
            sum += meal;
        }
        return sum;
    }

    public int getWeight() {
        return weight;
    }

    public void setScrawny(int scrawnyLimit) {
        this.scrawny = this.getWeight() < scrawnyLimit;
    }

    @Override
    public String toString() {
        return getClass().getName() + "\n\tName: " + name + "\n\tWeight: " + weight + "\n\tMeals: "+ meals;
    }
}
