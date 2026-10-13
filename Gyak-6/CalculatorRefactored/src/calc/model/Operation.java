package calc.model;

public interface Operation {

    double apply(double left, double right);

    String getSymbol();
}
