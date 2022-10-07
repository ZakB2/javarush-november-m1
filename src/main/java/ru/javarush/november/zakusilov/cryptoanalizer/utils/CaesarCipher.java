package ru.javarush.november.zakusilov.cryptoanalizer.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CaesarCipher {

    private CaesarCipher() {
    }

    static char[] charactersSet = getCharactersSet();
    static int charactersNumber = charactersSet.length;

    public static void toEncrypt(char[] textPart, int key) {

        for (int i = 0; i < textPart.length; i++) {
            for (int j = 0; j < charactersNumber; j++) {
                if (textPart[i] == charactersSet[j]) {
                    if (j + key < charactersNumber) {
                        textPart[i] = charactersSet[j + key];
                        break;
                    } else if (j + key >= charactersSet.length) {
                        textPart[i] = charactersSet[j + key - charactersNumber];
                        break;
                    }
                }
            }
        }
    }

    public static void toDecrypt(char[] textPart, int key) {

        for (int i = 0; i < textPart.length; i++) {
            for (int j = 0; j < charactersSet.length; j++) {
                if (textPart[i] == charactersSet[j]) {
                    if (j - key >= 0) {
                        textPart[i] = charactersSet[j - key];
                        break;
                    } else if (j - key < 0) {
                        textPart[i] = charactersSet[j - key + charactersNumber];
                        break;
                    }
                }
            }
        }
    }

    public static char[] getCharactersSet() {
        String stringFromCharactersSet = "";
        try (FileReader reader = new FileReader("src/main/resources/Russian alphabet character set.txt")) {
            BufferedReader buffer = new BufferedReader(reader);
            while (reader.ready()) {
                stringFromCharactersSet = buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringFromCharactersSet.toCharArray();
    }
}