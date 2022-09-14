package ru.javarush.november.zakusilov.cryptoanalizer;

import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;

public class Force {

    public static void main(String[] args) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        char[] charactersSet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '(', ')', '.', ',', '”', '”', ':', '-', '!', '?', ' '};
        char[] text = "There are four types of schools in the English and Welsh education system - nursery. primary, secondary and private schools.".toCharArray();
        for (int i = 0; i < charactersSet.length; i++) {
            int count = 0;
            for (int j = 0; j < text.length; j++) {
                if (charactersSet[i] == text[j]){
                    count++;
                }
            }
            hashMap.put(charactersSet[i], count);
            System.out.println(charactersSet[i] + " встречается " + count + " раз.");
        }
        for (Map.Entry<Character, Integer> characterIntegerEntry : hashMap.entrySet()) {
            Character chars = characterIntegerEntry.getKey();
            Integer integers = characterIntegerEntry.getValue();
            System.out.println(chars + " - " + integers);
        }
    }
}
