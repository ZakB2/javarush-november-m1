package ru.javarush.november.zakusilov.cryptoanalizer;

import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;

public class FileReaderWriter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String charactersString = "";
        System.out.println();
        try (FileReader reader = new FileReader("src/main/resources/!Russian alphabet character set.txt")) {
            BufferedReader buffer = new BufferedReader(reader);
            charactersString = buffer.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        char[] charactersSet = charactersString.toCharArray();

        int charactersNumber = charactersSet.length;

        System.out.println("Введите путь доступа к исходному файлу:");
        Path inputFilePath = Path.of(scanner.nextLine());
        String inputFileName = inputFilePath.getFileName().toString();
        int dotIndex = inputFileName.lastIndexOf(".");
        String outputFileName = inputFileName.substring(0, dotIndex) + "_cyphered" + inputFileName.substring(dotIndex);
        Path outputFilePath = inputFilePath.getParent().resolve(Path.of(outputFileName));


        int key = KeyInput.keyTest(scanner, charactersNumber);

        try (FileReader inputFile = new FileReader(inputFilePath.toString());
             FileWriter outputFile = new FileWriter(outputFilePath.toString());
             BufferedReader reader = new BufferedReader(inputFile);
             BufferedWriter writer = new BufferedWriter(outputFile)) {
            while (reader.ready()) {
                char[] stringArray = reader.readLine().toCharArray();
                for (int i = 0; i < stringArray.length; i++) {
                    for (int j = 0; j < charactersSet.length; j++) {
                        if (stringArray[i] == charactersSet[j]) {
                            if (j + key < charactersSet.length) {
                                stringArray[i] = charactersSet[j + key];
                                break;
                            } else if (j + key >= charactersSet.length) {
                                stringArray[i] = charactersSet[j + key - charactersNumber];
                                break;
                            }
                        }
                    }
                    writer.write(stringArray);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}