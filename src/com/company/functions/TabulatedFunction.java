package com.company.functions;

public class TabulatedFunction {

    private FunctionPoint[] array;

    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        this.array = new FunctionPoint[pointsCount];
        double step = (rightX - leftX) / pointsCount;
        double currentX = leftX;
        for (int i = 0; i < pointsCount; i++) {
            this.array[i] = new FunctionPoint(currentX, 0);
            currentX = currentX + step;
        }
    }

    public TabulatedFunction(double leftX, double rightX, double[] values) {
        int pointsCount = values.length;
        double currentX = leftX;
        double step = (rightX - leftX) / pointsCount;
        this.array = new FunctionPoint[pointsCount];
        for (int i = 0; i < pointsCount; i++) {
            this.array[i] = new FunctionPoint(currentX, values[i]);
            currentX = currentX + step;
        }
    }

    public double getLeftDomainBorder() {
        return this.array[0].getX();
    }

    public double getRightDomainBorder() {
        return this.array[this.array.length-1].getX();
    }

    public double getFunctionValue(double x) {
        if (x >= getLeftDomainBorder() && x <= getRightDomainBorder()) {

            for (int i = 0; i < this.array.length; i++) {
                if (this.array[i].getX() == x) {
                    return this.array[i].getY();

                } else if (x <= this.array[i].getX()) {
                    return this.array[i - 1].getY();
                }
            }
        }
        return Double.NaN;
    }
}
