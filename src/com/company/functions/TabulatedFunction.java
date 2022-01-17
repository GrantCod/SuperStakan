package com.company.functions;

public class TabulatedFunction {

    private double leftX;
    private double rightX;
    private FunctionPoint[] array;

    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        this.leftX = leftX;
        this.rightX = rightX;
        this.array = new FunctionPoint[pointsCount];
        double step = (rightX - leftX) / pointsCount;
        double currentX = leftX;
        for (int i = 0; i < pointsCount; i++) {
            this.array[i] = new FunctionPoint(currentX, 0);
            currentX = currentX + step;
        }
    }

    public TabulatedFunction(double leftX, double rightX, double[] values) {
        this.leftX = leftX;
        this.rightX = rightX;
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
        return this.leftX;
    }

    public double getRightDomainBorder() {
        return this.rightX;
    }

    public double getFunctionValue(double x) {
        if (x >= leftX && x <= rightX) {

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
