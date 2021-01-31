package com.shelevii.calculator;


import com.shelevii.calculator.math.Calculator;

public class App {
    private static String logFileName = "log.txt";

    public static void main(String[] args) {
        new Calculator(logFileName).process();
    }
}
