package ru.javarush.november.zakusilov.cryptoanalizer;

import java.util.Scanner;

public class Cypher {
    private static final char[] characterSet = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', 'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', '(', ')', '.', ',', '”', '”', ':', '-', '!', '?', ' '};
//    private static final char[] characterSet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '(', ')', '.', ',', '”', '”', ':', '-', '!', '?', ' '};
    private static int charactersNumber = characterSet.length;

    public static void fileCypher (Scanner scanner, char symbol) {
        int key = KeyInput.keyTest(scanner, charactersNumber);

        for (int i = 0; i < characterSet.length; i++) {
            if (symbol == characterSet[i]) {
                if (i + key < characterSet.length) {
                    symbol = characterSet[i + key];
                    break;
                } else if (i + key >= characterSet.length) {
                    symbol = characterSet[i + key - charactersNumber];
                    break;
                }
            }
        }
    }
    public static void toCypher(Scanner scanner) {
//        char[] characterSet = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', '(', ')', '.', ',', '”', '”', ':', '-', '!', '?', ' '};
//        int charactersNumber = characterSet.length;

        System.out.println("Введите слово или фразу, которую нужно зашифровать");
        String string = scanner.nextLine();

        int key = KeyInput.keyTest(scanner, charactersNumber);

        char[] stringArray = string.toCharArray();
        for (int i = 0; i < stringArray.length; i++) {
            for (int j = 0; j < characterSet.length; j++) {
                if (stringArray[i] == characterSet[j]) {
                    if (j + key < characterSet.length) {
                        stringArray[i] = characterSet[j + key];
                        break;
                    } else if (j + key >= characterSet.length) {
                        stringArray[i] = characterSet[j + key - charactersNumber];
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < stringArray.length; i++) {
            System.out.print(stringArray[i]);
        }
        System.out.println();
    }
}