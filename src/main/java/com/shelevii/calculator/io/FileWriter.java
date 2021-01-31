package com.shelevii.calculator.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileWriter {

    private Path path;

    public FileWriter(String fileName) {
        path = Paths.get(fileName);

        try {
            Files.write(path, new byte[0], StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String value) {
        try {
            Files.write(path, value.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
