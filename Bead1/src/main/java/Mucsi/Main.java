package Mucsi;

public class Main {
    public static void main(String[] args) {
        ShapeManager sm = new ShapeManager("shapes.txt");
        sm.PrintShapes();
        sm.ClosestAreaToPerimeter();
    }
}