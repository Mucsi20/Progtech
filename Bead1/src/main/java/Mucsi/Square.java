package Mucsi;

public class Square extends Shape{
    public Square(int x, int y, int r){ super(x,y,r); }
    @Override
    public double getArea() {
        return radius * radius * 2;
    }

    @Override
    public double getPerimeter() {
        return 4 * radius * Math.sqrt(2);
    }
}
