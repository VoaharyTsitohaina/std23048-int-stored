package com.examen.storedint.endpoint.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

@RestController
public class StoredIntController {

    private static final String FILE_PATH = "/tmp/stored-int.txt";

    @GetMapping("/stored-int")
    public String getStoredInt() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                String content = Files.readString(Path.of(FILE_PATH));
                return "{\"value\":" + content + "}";
            } else {
                int randomNumber = new Random().nextInt(1_000_000);
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(String.valueOf(randomNumber));
                }
                return "{\"value\":" + randomNumber + "}";
            }
        } catch (Exception e) {
            return "{\"error\":\"" + e.getMessage() + "\"}";
        }
    }
}