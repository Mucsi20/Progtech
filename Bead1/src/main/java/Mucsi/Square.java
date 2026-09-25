package Mucsi;

public class Square extends Shape{
    public Square(int x, int y, int r){ super(x,y,r); }
    @Override
    public double GetArea() {
        return radius * radius;
    }

    @Override
    public double GetPerimeter() {
        return 4 * radius;
    }
}
