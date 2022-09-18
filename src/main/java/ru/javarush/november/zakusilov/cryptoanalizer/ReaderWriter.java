package ru.javarush.november.zakusilov.cryptoanalizer;

import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;

public class ReaderWriter {
    public static void getText(Scanner scanner, String mode) {

        System.out.println("Введите путь доступа к исходному файлу:");
        Path inputFilePath = Path.of(scanner.nextLine());
        Path outputFilePath = getOutputFileNameAndPath(inputFilePath, mode);

        int charactersNumber = CaesarCipher.getCharactersSet().length;
        int key = KeyInput.keyTest(scanner, charactersNumber);

        try (FileReader fileReader = new FileReader(inputFilePath.toString());
             FileWriter fileWriter = new FileWriter(outputFilePath.toString())) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            while (bufferedReader.ready()) {
                char[] textPart = bufferedReader.readLine().toCharArray();
                if (mode.equals(ConsoleView.MODE_1)) {
                    CaesarCipher.toEncrypt(textPart, key);
                } else if (mode.equals(ConsoleView.MODE_2)) {
                    CaesarCipher.toDecrypt(textPart, key);
                }
                bufferedWriter.write(textPart);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Path getOutputFileNameAndPath(Path inputFilePath, String mode) {
        String inputFileName = inputFilePath.getFileName().toString();
        String outputFileName = "";
        int dotIndex = inputFileName.lastIndexOf(".");
        if (mode.equals(ConsoleView.MODE_1)) {
            outputFileName = inputFileName.substring(0, dotIndex) + "_encrypted" + inputFileName.substring(dotIndex);
        } else if (mode.equals(ConsoleView.MODE_2)) {
            outputFileName = inputFileName.substring(0, dotIndex) + "_decrypted" + inputFileName.substring(dotIndex);
        }
        return inputFilePath.getParent().resolve(Path.of(outputFileName));
    }
}