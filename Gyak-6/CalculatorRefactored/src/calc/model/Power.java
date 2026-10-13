package calc.model;

public final class Power implements Operation {

    @Override
    public double apply(double left, double right) {
        return Math.pow(left, right);
    }

    @Override
    public String getSymbol() {
        return "^";
    }
}
