package com.company.functions;

import com.company.functions.exceptions.FunctionPointIndexOutOfBoundsException;
import com.company.functions.exceptions.InappropriateFunctionPointException;
import com.company.functions.tools.MyLinkedList;

public class LinkedListTabulatedFunction {

    MyLinkedList array;
    public LinkedListTabulatedFunction(double leftX, double rightX, int pointsCount) {
        if (leftX >= rightX) {
            throw new IllegalArgumentException("Левая граница не может быть больше правой");
        } else if (pointsCount < 2) {
            throw new IllegalArgumentException("Недопустимое количество точек");
        }
        this.array = new MyLinkedList();
        double step = (rightX - leftX) / pointsCount;
        double currentX = leftX;
        for (int i = 0; i < pointsCount; i++) {
            this.array.addPointToTail(new FunctionPoint(currentX, 0));
            currentX = currentX + step;
        }
    }

    public LinkedListTabulatedFunction(double leftX, double rightX, double[] values) {
        if (leftX >= rightX) {
            throw new IllegalArgumentException("Левая граница не может быть больше правой");
        } else if (values.length < 2) {
            throw new IllegalArgumentException("Недопустимое количество точек");
        }
        int pointsCount = values.length;
        double currentX = leftX;
        double step = (rightX - leftX) / pointsCount;
        this.array = new MyLinkedList();
        for (int i = 0; i < pointsCount; i++) {
            this.array.addPointToTail(new FunctionPoint(currentX, values[i]));
            currentX = currentX + step;
        }
    }

    public double getLeftDomainBorder() {
        return this.array.getPointByIndex(0).getX();
    }

    public double getRightDomainBorder() {
        return this.array.getPointByIndex(this.array.getLength()-1) .getX();
    }

    public double getFunctionValue(double x) {
        if (x >= getLeftDomainBorder() && x <= getRightDomainBorder()) {

            for (int i = 0; i < this.array.getLength(); i++) {
                if (this.array.getPointByIndex(i).getX() == x) {
                    return this.array.getPointByIndex(i).getY();

                } else if (x <= this.array.getPointByIndex(i).getX()) {
                    return (this.array.getPointByIndex(i - 1).getY() + (this.array.getPointByIndex(i).getY() - this.array.getPointByIndex(i - 1).getY()) / (this.array.getPointByIndex(i).getX() - this.array.getPointByIndex(i - 1).getX()) * (x - this.array.getPointByIndex(i-1).getX()));
                }
            }
        }
        return Double.NaN;

    }

    public int getPointsCount() {
        return this.array.getLength();
    }

    public FunctionPoint getPoint(int index) {
        if (index >= this.getPointsCount() && index < 0) {
            throw new FunctionPointIndexOutOfBoundsException("Недопустимый индекс");
        }
        return this.array.getPointByIndex(index);
    }

    public void setPoint(int index, FunctionPoint point) throws InappropriateFunctionPointException {
        if (index >= this.getPointsCount() && index < 0) {
            throw new FunctionPointIndexOutOfBoundsException("Недопустимый индекс");
        }
        if ((index > 0 && this.array.getPointByIndex(index - 1).getX() < point.getX())
                && (index < (this.array.getLength() - 1) && this.array.getPointByIndex(index + 1).getX() > point.getX())) {
            this.array.setPointByIndex(index, point);
        }
        else {
            throw new InappropriateFunctionPointException("Координата Х лежит вне интервала соседних точек функции");
        }
    }

    public double getPointX(int index) {
        if (index >= this.getPointsCount() && index < 0) {
            throw new FunctionPointIndexOutOfBoundsException("Недопустимый индекс");
        }
        return this.array.getPointByIndex(index).getX();
    }

    public void setPointX(int index, double x) throws InappropriateFunctionPointException {
        if (index >= this.getPointsCount() && index < 0) {
            throw new FunctionPointIndexOutOfBoundsException("Недопустимый индекс");
        }
        if ((index > 0 && this.array.getPointByIndex(index - 1).getX() < x)
                && (index < (this.array.getLength() - 1) && this.array.getPointByIndex(index + 1).getX() > x)) {
            this.array.getPointByIndex(index).setX(x);
        }
        else {
            throw new InappropriateFunctionPointException("Координата Х лежит вне интервала соседних точек функции");
        }

    }

    public double getPointY(int index) {
        if (index >= this.getPointsCount() && index < 0) {
            throw new FunctionPointIndexOutOfBoundsException("Недопустимый индекс");
        }
        return this.array.getPointByIndex(index).getY();

    }

    public void setPointY(int index, double y) {
        if (index >= this.getPointsCount() && index < 0) {
            throw new FunctionPointIndexOutOfBoundsException("Недопустимый индекс");
        }
        this.array.getPointByIndex(index).setY(y);
    }

    public void deletePoint(int index) {

        if (getPointsCount() < 3) {
            throw new IllegalStateException("Количество точек меньше трех");
        }
        if (index >= this.getPointsCount() && index < 0) {
            throw new FunctionPointIndexOutOfBoundsException("Недопустимый индекс");
        }
       this.array.deletePointByIndex(index);
    }

    public void addPoint(FunctionPoint point) throws InappropriateFunctionPointException {
        FunctionPoint[] values = new FunctionPoint[array.getLength() + 1];
        int index = 0;
        for (int i = 0; i < array.getLength(); i++) {
            if (i == 0 && array.getPointByIndex(i).getX() > point.getX()) {
                index = 0;
                break;
            } else if (i == array.getLength() - 1 && point.getX() > array.getPointByIndex(i).getX()) {
                index = i + 1;
                break;
            } else if (array.getPointByIndex(i).getX() < point.getX() && array.getPointByIndex(i + 1).getX() > point.getX()) {
                index = i + 1;
                break;
            }
            if (array.getPointByIndex(i).getX() == point.getX()) {
                throw new InappropriateFunctionPointException("Такая точка уже есть");
            }
        }
        this.array.addPointByIndex(index, point);
    }
}
