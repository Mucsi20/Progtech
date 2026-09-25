package Mucsi;

public abstract class Shape {

    public Point origin;
    public int radius;

    public Shape(int x, int y, int r){
        this.origin = new Point(x, y);
        this.radius = r;
    }

    public double GetAreaToPerimeterRatio(){
        return GetArea() / GetPerimeter();
    }

    public abstract double GetArea();
    public abstract double GetPerimeter();

    @Override
    public String toString() {
        return this.getClass().getName() + "\n\tOrigin: " + origin + "\n\tRadius: " + radius + "\n\tArea: " + GetArea() + "\n\tPerimeter: " + GetPerimeter();
    }
}
