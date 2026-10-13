package Mucsi;

public class Main {
    public static void main(String[] args) {
        ShapeManager sm = new ShapeManager("shapesTest.txt");
        sm.printShapes();
        sm.closestAreaToPerimeter();
    }
}