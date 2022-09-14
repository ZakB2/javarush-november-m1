package ru.javarush.november.zakusilov.cryptoanalizer;

import java.util.Scanner;

public class KeyInput {
    private static int key;
    private static String errorText = "Ошибка, введено неверное значаение ключа.";

    public static int keyTest(Scanner scanner, int numberOfCharacters) {
        key = 0;
        while (key < 1 || key >= numberOfCharacters) {
            System.out.println("Введите ключ шифрования. Ключ должен быть целым положительным числом.");
            try {
                key = Integer.parseInt(scanner.nextLine());
                if (key < 1) {
                    System.err.println(errorText + "\b, ключ не может быть меньше 1.");
                }
                if (key >= numberOfCharacters) {
                    if (key % numberOfCharacters != 0) {
                        key = key % numberOfCharacters;
                    } else {
                        System.err.println(errorText + "\b, данное значение не применимо.");
                    }
                }
            } catch (NumberFormatException e) {
                System.err.println(errorText + "\b, ключ не может быть вещественным числом или текстом.");
            }
        }
        return key;
    }
}

