package com.company;

import com.company.functions.FunctionPoint;
import com.company.functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {
        TabulatedFunction tabulatedFunction = new TabulatedFunction(3.4, 8.9, 10);
        for (int i = 0; i < tabulatedFunction.getPointsCount(); i++) {
            System.out.println(tabulatedFunction.getPointX(i) + " " + tabulatedFunction.getPointY(i));
        }
        tabulatedFunction.addPoint(new FunctionPoint(3, 2));
        tabulatedFunction.addPoint(new FunctionPoint(10, 0));
        tabulatedFunction.addPoint(new FunctionPoint(6, 0));

        tabulatedFunction.deletePoint(2);

        System.out.println("");

        for (int i = 0; i < tabulatedFunction.getPointsCount(); i++) {
            System.out.println(tabulatedFunction.getPointX(i) + " " + tabulatedFunction.getPointY(i));
        }
        System.out.println(tabulatedFunction.getFunctionValue(3.03)) ;

    }
}