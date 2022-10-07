package ru.javarush.november.zakusilov.cryptoanalizer.utils;

import ru.javarush.november.zakusilov.cryptoanalizer.view.ConsoleView;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

import static ru.javarush.november.zakusilov.cryptoanalizer.utils.ReaderWriter.getOutputFileNameAndPath;

public class BruteForce {

    private BruteForce() {
    }

    public static void hackKey(Scanner scanner, String mode) {

        ReaderWriter.printLine("Введите путь доступа к исходному файлу:");
        Path inputFilePath = ReaderWriter.testPath(scanner);
        if (inputFilePath == null) return;
        Path outputFilePath = getOutputFileNameAndPath(inputFilePath, mode);

        Integer initialCharactersNumber = 0;
        Map<Character, Integer> charactersNumberMap = new HashMap<>();
        char[] charactersSet = CaesarCipher.getCharactersSet();
        for (int i = 0; i < charactersSet.length; i++) {
            charactersNumberMap.put(charactersSet[i], initialCharactersNumber);
        }

        try (FileReader fileReader = new FileReader(inputFilePath.toString());
            BufferedReader buffer = new BufferedReader(fileReader)) {
            while (buffer.ready()) {
                char[] textPart = buffer.readLine().toCharArray();
                for (int i = 0; i < textPart.length; i++) {
                    for (Character character : charactersNumberMap.keySet()) {
                        if (textPart[i] == character) {
                            Integer characterNumber = charactersNumberMap.get(character) + 1;
                            charactersNumberMap.put(character, characterNumber);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(charactersNumberMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> a, Map.Entry<Character, Integer> b) {
                return b.getValue() - a.getValue();
            }
        });
        char firstCharacter = list.get(0).toString().charAt(0);
        char secondCharacter = list.get(1).toString().charAt(0);
        char characterSpace = ' ';
        char characterO = 'о';

        int firstCharacterIndex = initialCharactersNumber;
        int secondCharacterIndex = initialCharactersNumber;
        int characterSpaceIndex = initialCharactersNumber;
        int characterOIndex = initialCharactersNumber;

        for (int i = 0; i < charactersSet.length; i++) {
            if (charactersSet[i] == firstCharacter) {
                firstCharacterIndex = i;
            } else if (charactersSet[i] == secondCharacter) {
                secondCharacterIndex = i;
            } else if (charactersSet[i] == characterSpace) {
                characterSpaceIndex = i;
            } else if (charactersSet[i] == characterO) {
                characterOIndex = i;
            }
        }
        int key = 0;
        if ((firstCharacterIndex - characterSpaceIndex) == (secondCharacterIndex - characterOIndex)) {
            if ((firstCharacterIndex - characterSpaceIndex) > 0) {
                key = firstCharacterIndex - characterSpaceIndex;
            } else if ((firstCharacterIndex - characterSpaceIndex) < 0) {
                key = firstCharacterIndex + charactersSet.length - characterSpaceIndex;
            }
        }

        try (FileReader fileReader = new FileReader(inputFilePath.toString());
             FileWriter fileWriter = new FileWriter(outputFilePath.toString())) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            while (bufferedReader.ready()) {
                char[] textPart = bufferedReader.readLine().toCharArray();
                if (mode.equals(ConsoleView.MODE_3)) {
                    CaesarCipher.toDecrypt(textPart, key);
                }
                bufferedWriter.write(textPart);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ReaderWriter.printLine("Взлом выполнен.");
    }
}