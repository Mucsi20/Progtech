package calc.model;

public final class Subtraction implements Operation {

    @Override
    public double apply(double left, double right) {
        return left - right;
    }

    @Override
    public String getSymbol() {
        return "-";
    }
}
