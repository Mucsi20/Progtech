package Mucsi;

public class Triangle extends Shape{
    public Triangle(int x, int y, int r){ super(x,y,r); }
    @Override
    public double GetArea() {
        return (Math.sqrt(3) / 4) * radius * radius;
    }

    @Override
    public double GetPerimeter() {
        return 3 * radius;
    }
}
