package Mucsi;

import Mucsi.Animal.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {

    private final ArrayList<Animal> animals;

    public Database() {
        animals = new ArrayList<>();
    }

    public void read(String filename) throws FileNotFoundException, InvalidInputException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));
        int numAnimals = sc.nextInt();
        while (sc.hasNext()) {
            Animal animal;
            String[] nextLine = sc.nextLine().split(" ");
            ArrayList<Integer> meals = collectMeals(nextLine);
            switch (nextLine[0]) {
                case "E":
                    animal = new Emu(nextLine[1], Integer.parseInt(nextLine[2]), meals);
                    if(Integer.parseInt(nextLine[3]) != meals.size()){
                        System.out.println("Mismatching meal amounts");
                    }
                    break;
                case "G":
                    animal = new Goat(nextLine[1], Integer.parseInt(nextLine[2]), meals);
                    if(Integer.parseInt(nextLine[3]) != meals.size()){
                        System.out.println("Mismatching meal amounts");
                    }
                    break;
                case "C":
                    animal = new Cow(nextLine[1], Integer.parseInt(nextLine[2]), meals);
                    if(Integer.parseInt(nextLine[3]) != meals.size()){
                        System.out.println("Mismatching meal amounts");
                    }
                    break;
                case "H":
                    animal = new Horse(nextLine[1], Integer.parseInt(nextLine[2]), meals);
                    if(Integer.parseInt(nextLine[3]) != meals.size()){
                        System.out.println("Mismatching meal amounts");
                    }
                    break;
                default:
                    throw new InvalidInputException();
            }
            animals.add(animal);
        }
    }
    
    public void report() {
        System.out.println("Animals in the database:");
        for (Animal animal : animals){
            System.out.println(animal);
        }
    }

    public ArrayList<Integer> collectMeals(String[] nextLine){
        ArrayList<Integer> meals = new ArrayList<>();
        for (int i = 4; i < nextLine.length; i++){
            meals.add(Integer.parseInt(nextLine[i]));
        }
        return meals;
    }
}
