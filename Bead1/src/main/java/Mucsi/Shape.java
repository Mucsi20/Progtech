package Mucsi;

public abstract class Shape {

    public Point origin;
    public int radius;

    public Shape(int x, int y, int r){
        this.origin = new Point(x, y);
        this.radius = r;
    }

    public double getAreaToPerimeterRatio(){
        return getArea() / getPerimeter();
    }

    public abstract double getArea();
    public abstract double getPerimeter();

    @Override
    public String toString() {
        return this.getClass().getName() + "\n\tOrigin: " + origin + "\n\tRadius: " + radius + "\n\tArea: " + getArea() + "\n\tPerimeter: " + getPerimeter() + "\n\tArea to perimeter ratio: "+ getAreaToPerimeterRatio();
    }
}
