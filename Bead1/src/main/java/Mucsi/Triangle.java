package Mucsi;

public class Triangle extends Shape{
    public Triangle(int x, int y, int r){ super(x,y,r); }
    @Override
    public double getArea() {
        return (3 * Math.sqrt(3) / 4) * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 3 * radius * Math.sqrt(3);
    }
}
