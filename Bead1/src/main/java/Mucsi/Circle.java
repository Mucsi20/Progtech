package Mucsi;

public class Circle extends Shape{
    public Circle(int x, int y, int r){ super(x,y,r); }
    @Override
    public double GetArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double GetPerimeter() {
        return 2 * Math.PI * radius;
    }
}
