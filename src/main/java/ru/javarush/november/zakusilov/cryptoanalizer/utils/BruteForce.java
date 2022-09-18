package ru.javarush.november.zakusilov.cryptoanalizer.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BruteForce {

    public static void main(String[] args) {
        HashMap<Character, Integer> hashMap = new HashMap<>();

        char[] charactersSet = CaesarCipher.getCharactersSet();
        try {
            FileReader fileReader = new FileReader("src/main/resources/Война и мир.txt");
            BufferedReader buffer = new BufferedReader(fileReader);
            while (buffer.ready()) {
                char[] text = buffer.readLine().toCharArray();
                for (int i = 0; i < charactersSet.length; i++) {
                    int count = 0;
                    for (int j = 0; j < text.length; j++) {
                        if (charactersSet[i] == text[j]) {
                            count++;
                        }
                    }
                    hashMap.put(charactersSet[i], count);
                    System.out.println(charactersSet[i] + " встречается " + count + " раз.");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Map.Entry<Character, Integer> characterIntegerEntry : hashMap.entrySet()) {
            Character chars = characterIntegerEntry.getKey();
            Integer integers = characterIntegerEntry.getValue();
            System.out.println(chars + " - " + integers);
        }
    }
}
