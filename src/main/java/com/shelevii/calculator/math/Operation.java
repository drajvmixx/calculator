package com.shelevii.calculator.math;

public enum Operation {
    PLUS() {
        public double calculate(double x, double y) {
            return x + y;
        }

        public String toString() {
            return "+";
        }
    },

    MINUS() {
        public double calculate(double x, double y) {
            return x - y;
        }

        public String toString() {
            return "-";
        }
    },

    MULTIPLY() {
        public double calculate(double x, double y) {
            return x * y;
        }

        public String toString() {
            return "*";
        }
    },

    DIVIDE() {
        public double calculate(double x, double y) {
            return x / y;
        }

        public String toString() {
            return "/";
        }
    };

    public abstract double calculate(double x, double y);
}
