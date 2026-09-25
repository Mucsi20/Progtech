package Mucsi;

public class Hexagon extends Shape{
    public Hexagon(int x, int y, int r){ super(x,y,r); }
    @Override
    public double GetArea() {
        return (3 * Math.sqrt(3) / 2) * radius * radius;
    }

    @Override
    public double GetPerimeter() {
        return 6 * radius;
    }
}
