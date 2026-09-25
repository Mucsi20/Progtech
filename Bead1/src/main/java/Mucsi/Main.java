package Mucsi;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Shape> shapeList = GenerateShapes();
        PrintShapes(shapeList);
    }
    public static ArrayList<Shape> GenerateShapes(){
        ArrayList<Shape> ShapeList = new ArrayList<>();
        ShapeList.add(new Circle(1,2,3));
        ShapeList.add(new Triangle(-2,-3,5));
        ShapeList.add(new Square(0,2,2));
        ShapeList.add(new Hexagon(4,-1,4));
        return ShapeList;
    }
    public static void PrintShapes(ArrayList<Shape> ShapeList){
        for (Shape shape : ShapeList){
            System.out.println(shape);
        }
    }

}