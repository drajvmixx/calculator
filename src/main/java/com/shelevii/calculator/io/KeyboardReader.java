package com.shelevii.calculator.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KeyboardReader {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public String readLine() {
        String name = "";

        try {
            name = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return name;
    }

    public void cleanUp() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
