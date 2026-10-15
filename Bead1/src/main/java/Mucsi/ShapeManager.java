package Mucsi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShapeManager {
    private final ArrayList<Shape> shapeList;

    public ShapeManager(String path){
        this.shapeList = generateShapes(path);
    }

    private static ArrayList<Shape> generateShapes(String filePath){
        Path path = Paths.get(filePath);
        ArrayList<Shape> ShapeList = new ArrayList<>();
        try {
            List<String> rows = Files.readAllLines(path);
            rows.removeFirst();

            for (String row : rows) {
                Shape newShape = getNewShape(row);
                ShapeList.add(newShape);
            }
        }
        catch (NoSuchFileException e){
            System.out.println("File does not exist at: "+ path.toAbsolutePath());
        }
        catch (IOException e) {
            System.out.println("File read error: ");
        }
        return ShapeList;
    }

    private static Shape getNewShape(String row) {
        String[] rowData = row.split(",");
        if (rowData.length != 4){
            throw new IllegalArgumentException("Invalid amount of shape parameters: " + Arrays.toString(rowData));
        }
        try {
            return switch (rowData[0]) {
                case "C" ->
                        new Circle(Integer.parseInt(rowData[1]), Integer.parseInt(rowData[2]), Integer.parseInt(rowData[3]));
                case "S" ->
                        new Square(Integer.parseInt(rowData[1]), Integer.parseInt(rowData[2]), Integer.parseInt(rowData[3]));
                case "T" ->
                        new Triangle(Integer.parseInt(rowData[1]), Integer.parseInt(rowData[2]), Integer.parseInt(rowData[3]));
                case "H" ->
                        new Hexagon(Integer.parseInt(rowData[1]), Integer.parseInt(rowData[2]), Integer.parseInt(rowData[3]));
                default -> new Square(0, 0, 0);
            };
        }
        catch (NumberFormatException e){
            throw new IllegalArgumentException("Invalid number in file: " + e);
        }
    }

    public void printShapes(){
        for (Shape shape : this.shapeList){
            System.out.println(shape);
        }
    }

    public void closestAreaToPerimeter(){
        Shape closestShape = shapeList.getFirst();
        double closestValue = shapeList.getFirst().getAreaToPerimeterRatio();
        for (Shape shape : this.shapeList){
            double ratio = shape.getAreaToPerimeterRatio();
            if (Math.abs(1 - ratio) < Math.abs(1 - closestValue)){
                closestShape = shape;
                closestValue = ratio;
            }
        }
        System.out.println("Shape with closest area to perimeter ratio:");
        System.out.println(closestShape);
    }
}
