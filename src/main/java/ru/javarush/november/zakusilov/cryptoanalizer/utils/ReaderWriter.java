package ru.javarush.november.zakusilov.cryptoanalizer.utils;

import ru.javarush.november.zakusilov.cryptoanalizer.view.ConsoleView;

import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;

import static java.nio.file.Files.isRegularFile;

public class ReaderWriter {

    private ReaderWriter() {
    }

    public static void getText(Scanner scanner, String mode) {

        printLine("Введите путь доступа к исходному файлу:");
        Path inputFilePath = testPath(scanner);
        if (inputFilePath == null) return;
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
            if (mode.equals(ConsoleView.MODE_1)) {
                printLine("Шифрование выполнено.");
            } else if (mode.equals(ConsoleView.MODE_2)) {
                printLine("Расшифровка выполнена.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printLine(String string) {
        System.out.println(string);
    }

    public static void printErrorLine(String string) {
        System.err.println(string);
    }

    public static Path testPath(Scanner scanner) {
        Path inputFilePath = Path.of(scanner.nextLine());
        if (!isRegularFile(inputFilePath)) {
            printErrorLine("Ошибка, проверьте путь доступа к исходному файлу. Программа возвращается в главное меню");
            return null;
        }
        return inputFilePath;
    }

    public static Path getOutputFileNameAndPath(Path inputFilePath, String mode) {
        String inputFileName = inputFilePath.getFileName().toString();
        String outputFileName = "";
        int dotIndex = inputFileName.lastIndexOf(".");
        if (mode.equals(ConsoleView.MODE_1)) {
            outputFileName = inputFileName.substring(0, dotIndex) + "_encrypted" + inputFileName.substring(dotIndex);
        } else if (mode.equals(ConsoleView.MODE_2)) {
            outputFileName = inputFileName.substring(0, dotIndex) + "_decrypted" + inputFileName.substring(dotIndex);
        } else if (mode.equals(ConsoleView.MODE_3)) {
            outputFileName = inputFileName.substring(0, dotIndex) + "_hacked" + inputFileName.substring(dotIndex);
        }
        return inputFilePath.getParent().resolve(Path.of(outputFileName));
    }
}