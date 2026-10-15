package Mucsi;

public class Main {
    public static void main(String[] args) {
        ShapeManager sm = new ShapeManager("shaples.txt");
        sm.printShapes();
        sm.closestAreaToPerimeter();
    }
}