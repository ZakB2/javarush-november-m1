package ru.javarush.november.zakusilov.cryptoanalizer;

import java.util.Scanner;

public class Decypher {
    public static void toDecypher(Scanner scanner) {
        char[] characterSet = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', '(', ')', '.', ',', '”', '”', ':', '-', '!', '?', ' '};
        int charactersNumber = characterSet.length;

        System.out.println("Введите слово или фразу, которую нужно расшифровать");
        String string = scanner.nextLine().toUpperCase();

        int key = KeyInput.keyTest(scanner, charactersNumber);

        char[] stringArray = string.toCharArray();
        for (int i = 0; i < stringArray.length; i++) {
            for (int j = 0; j < characterSet.length; j++) {
                if (stringArray[i] == characterSet[j]) {
                    if (j - key >= 0) {
                        stringArray[i] = characterSet[j - key];
                        break;
                    } else if (j - key < 0) {
                        stringArray[i] = characterSet[j - key + charactersNumber];
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