package com.shelevii.calculator.math;

import com.shelevii.calculator.io.FileWriter;
import com.shelevii.calculator.io.KeyboardReader;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.shelevii.calculator.math.Calculator.State.*;

public class Calculator {

    protected enum State {READ_FIRST_VALUE, READ_SECOND_VALUE, READ_OPERATION, SHOW_RESULT, EXIT_OR_CONTINUE_QUESTION, EXIT}

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private KeyboardReader keyboardReader;
    private FileWriter fileWriter;

    private State state = State.READ_FIRST_VALUE;

    private double first;
    private double second;
    private Operation operation;

    public Calculator(String logFileName) {
        keyboardReader = new KeyboardReader();
        fileWriter = new FileWriter(logFileName);
    }

    public void process() {
        while (!EXIT.equals(state)) {
            doAction();
        }

        keyboardReader.cleanUp();
    }

    private void doAction() {
        switch (state) {
            case READ_FIRST_VALUE:
                readNumber("Please enter first number:");
                break;

            case READ_OPERATION:
                readOperation();
                break;

            case READ_SECOND_VALUE:
                readNumber("Please enter second number:");
                break;

            case SHOW_RESULT:
                showResult();
                break;

            case EXIT_OR_CONTINUE_QUESTION:
                exitOrContinueQuestion();
                break;

            default:
                break;
        }
    }

    private void readOperation() {
        System.out.println("Please enter an arithmetic operation. Supported operations are: + - * /");

        String operationString = keyboardReader.readLine().trim();
        boolean knownOperation = false;

        for (Operation value : Operation.values()) {
            if (value.toString().equalsIgnoreCase(operationString)) {
                operation = value;
                knownOperation = true;
                state = READ_SECOND_VALUE;
            }
        }

        if (!knownOperation) {
            System.out.println("Operation is not supported.");
        }
    }

    private void exitOrContinueQuestion() {
        System.out.println("Please enter the q letter to exit or the c letter to continue:");

        String value = keyboardReader.readLine().trim();
        if ("q".equalsIgnoreCase(value)) {
            state = EXIT;
        } else if ("c".equalsIgnoreCase(value)) {
            state = READ_FIRST_VALUE;
        }
    }

    private void readNumber(String message) {
        System.out.println(message);
        try {
            Double value = Double.valueOf(keyboardReader.readLine());

            if (state.equals(READ_FIRST_VALUE)) {
                state = READ_OPERATION;
                first = value;
            } else if (state.equals(READ_SECOND_VALUE)) {
                state = SHOW_RESULT;
                second = value;
            }
        } catch (NumberFormatException e) {
            System.out.println("Number format is incorrect.");
        }
    }

    private void showResult() {
        try {
            String result = String.valueOf(operation.calculate(first, second));
            System.out.println("The result is: " + result);
            writeLog(result);
        } catch (ArithmeticException e) {
            System.out.println("An error occurred...");
        }

        state = EXIT_OR_CONTINUE_QUESTION;
    }

    private void writeLog(String result) {
        StringBuilder logData = new StringBuilder(DATE_FORMAT.format(new Date()))
                .append("  ")
                .append(first)
                .append(operation)
                .append(second)
                .append("=")
                .append(result)
                .append("\n");

        fileWriter.write(logData.toString());
    }
}
