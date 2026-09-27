package Mucsi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ShapeManager {
    private final ArrayList<Shape> shapeList;

    public ShapeManager(String path){
        this.shapeList = GenerateShapes(path);
    }

    public static ArrayList<Shape> GenerateShapes(String path){
        ArrayList<Shape> ShapeList = new ArrayList<>();
        try {
            List<String> rows = Files.readAllLines(Paths.get(path));
            rows.removeFirst();

            for (String row : rows) {
                String[] rowData = row.split(",");
                Shape newShape = switch (rowData[0]) {
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
                ShapeList.add(newShape);
            }
        } catch (IOException e) {
            System.out.println("File read error: " + e.getMessage());
        }
        return ShapeList;
    }

    public void PrintShapes(){
        for (Shape shape : this.shapeList){
            System.out.println(shape);
        }
    }

    public void ClosestAreaToPerimeter(){
        Shape closestShape = shapeList.getFirst();
        double closestValue = shapeList.getFirst().GetAreaToPerimeterRatio();
        for (Shape shape : this.shapeList){
            double ratio = shape.GetAreaToPerimeterRatio();
            if (Math.abs(1 - ratio) < Math.abs(1 - closestValue)){
                closestShape = shape;
                closestValue = ratio;
            }
        }
        System.out.println("Shape with closest area to perimeter ratio:");
        System.out.println(closestShape);
    }
}
