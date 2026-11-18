package ufos;

import java.awt.*;

public class Beacon {
    private double x;
    private double y;
    private final Color color;
    public Boolean drawing;
    public Beacon(){
        drawing = false;
        x = 0;
        y = 0;
        color = new Color((float)245/255, (float)1, (float)145/255, (float)0.5);
    }
    public void draw(Graphics2D g2) {
        if (!drawing){return;}
        g2.setColor(color);
        g2.fillOval((int)x-50, (int) y-50, 100, 100);
        g2.setColor(color.brighter());
        g2.fillOval((int)x-45, (int)y-45, 90, 90);
    }
    public void movePosition(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
