package ru.javarush.november.zakusilov.cryptoanalizer.utils;

import java.util.Scanner;

public class KeyInput {

    private KeyInput() {
    }

    private static final String ERROR_TEXT = "Ошибка, введено неверное значаение ключа.";

    public static int keyTest(Scanner scanner, int charactersNumber) {
        int key = 0;
        while (key < 1 || key >= charactersNumber) {
            System.out.println("Введите ключ шифрования. Ключ должен быть целым положительным числом.");
            try {
                key = Integer.parseInt(scanner.nextLine());
                if (key < 1) {
                    System.err.println(ERROR_TEXT + "\b, ключ не может быть меньше 1.");
                }
                if (key >= charactersNumber) {
                    if (key % charactersNumber != 0) {
                        key = key % charactersNumber;
                    } else {
                        System.err.println(ERROR_TEXT + "\b, данное значение не применимо.");
                    }
                }
            } catch (NumberFormatException e) {
                System.err.println(ERROR_TEXT + "\b, ключ не может быть вещественным числом или текстом.");
            }
        }
        return key;
    }
}

