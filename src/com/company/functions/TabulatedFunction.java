package com.company.functions;

import com.company.functions.exceptions.FunctionPointIndexOutOfBoundsException;
import com.company.functions.exceptions.InappropriateFunctionPointException;

public class TabulatedFunction {

    private FunctionPoint[] array;

    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        if (leftX >= rightX) {
            throw new IllegalArgumentException("Левая граница не может быть больше правой");
        } else if (pointsCount < 2) {
            throw new IllegalArgumentException("Недопустимое количество точек");
        }
        this.array = new FunctionPoint[pointsCount];
        double step = (rightX - leftX) / pointsCount;
        double currentX = leftX;
        for (int i = 0; i < pointsCount; i++) {
            this.array[i] = new FunctionPoint(currentX, 0);
            currentX = currentX + step;
        }
    }

    public TabulatedFunction(double leftX, double rightX, double[] values) {
        if (leftX >= rightX) {
            throw new IllegalArgumentException("Левая граница не может быть больше правой");
        } else if (values.length < 2) {
            throw new IllegalArgumentException("Недопустимое количество точек");
        }
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
        return this.array[this.array.length - 1].getX();
    }

    public double getFunctionValue(double x) {
        if (x >= getLeftDomainBorder() && x <= getRightDomainBorder()) {

            for (int i = 0; i < this.array.length; i++) {
                if (this.array[i].getX() == x) {
                    return this.array[i].getY();

                } else if (x <= this.array[i].getX()) {
                    return (this.array[i - 1].getY() + (this.array[i].getY() - this.array[i - 1].getY()) / (this.array[i].getX() - this.array[i - 1].getX()) * (x - this.array[i - 1].getX()));
                }
            }
        }
        return Double.NaN;

    }

    public int getPointsCount() {
        return this.array.length;
    }

    public FunctionPoint getPoint(int index) {
        if (index >= this.getPointsCount() && index < 0) {
            throw new FunctionPointIndexOutOfBoundsException("Недопустимый индекс");
        }
        return this.array[index];
    }

    public void setPoint(int index, FunctionPoint point) throws InappropriateFunctionPointException {
        if (index >= this.getPointsCount() && index < 0) {
            throw new FunctionPointIndexOutOfBoundsException("Недопустимый индекс");
        }
        if ((index > 0 && this.array[index - 1].getX() < point.getX())
                && (index < (this.array.length - 1) && this.array[index + 1].getX() > point.getX())) {
            this.array[index] = point;
        }
        else {
            throw new InappropriateFunctionPointException("Координата Х лежит вне интервала соседних точек функции");
        }
    }

    public double getPointX(int index) {
        if (index >= this.getPointsCount() && index < 0) {
            throw new FunctionPointIndexOutOfBoundsException("Недопустимый индекс");
        }
        return this.array[index].getX();
    }

    public void setPointX(int index, double x) throws InappropriateFunctionPointException {
        if (index >= this.getPointsCount() && index < 0) {
            throw new FunctionPointIndexOutOfBoundsException("Недопустимый индекс");
        }
        if ((index > 0 && this.array[index - 1].getX() < x)
                && (index < (this.array.length - 1) && this.array[index + 1].getX() > x)) {
            this.array[index].setX(x);
        }
        else {
            throw new InappropriateFunctionPointException("Координата Х лежит вне интервала соседних точек функции");
        }

    }

    public double getPointY(int index) {
        if (index >= this.getPointsCount() && index < 0) {
            throw new FunctionPointIndexOutOfBoundsException("Недопустимый индекс");
        }
        return this.array[index].getY();

    }

    public void setPointY(int index, double y) {
        if (index >= this.getPointsCount() && index < 0) {
            throw new FunctionPointIndexOutOfBoundsException("Недопустимый индекс");
        }
        this.array[index].setY(y);
    }

    public void deletePoint(int index) {
        if (getPointsCount() < 3) {
            throw new IllegalStateException("Количество точек меньше трех");
        }
        if (index >= this.getPointsCount() && index < 0) {
            throw new FunctionPointIndexOutOfBoundsException("Недопустимый индекс");
        }
        FunctionPoint[] values = new FunctionPoint[array.length - 1];
        System.arraycopy(array, 0, values, 0, index);
        System.arraycopy(array, index + 1, values, index, values.length - index);
        this.array = values;
    }

    public void addPoint(FunctionPoint point) throws InappropriateFunctionPointException {
        FunctionPoint[] values = new FunctionPoint[array.length + 1];
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (i == 0 && array[i].getX() > point.getX()) {
                index = 0;
                break;
            } else if (i == array.length - 1 && point.getX() > array[i].getX()) {
                index = i + 1;
                break;
            } else if (array[i].getX() < point.getX() && array[i + 1].getX() > point.getX()) {
                index = i + 1;
                break;
            }
            if (array[i].getX() == point.getX()) {
                throw new InappropriateFunctionPointException("Такая точка уже есть");
            }
        }
        System.arraycopy(array, 0, values, 0, index);
        values[index] = point;
        System.arraycopy(array, index, values, index + 1, array.length - index);
        this.array = values;

    }
}