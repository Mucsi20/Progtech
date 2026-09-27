package Mucsi;

public class Square extends Shape{
    public Square(int x, int y, int r){ super(x,y,r); }
    @Override
    public double getArea() {
        return radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 4 * radius;
    }
}
