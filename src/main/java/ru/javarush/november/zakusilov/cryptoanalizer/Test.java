package ru.javarush.november.zakusilov.cryptoanalizer;

import java.io.*;

public class Test {
    public static void main(String[] args) throws IOException {
//        char[] characterSet = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', 'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', '(', ')', '.', ',', '”', '”', ':', '-', '!', '?', ' '};
//        char[] characterSet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '(', ')', '.', ',', '”', '”', ':', '-', '!', '?', ' '};
//        for (int i = 0; i < characterSet.length; i++) {
//            System.out.print(characterSet[i]);
//        }
        FileReader reader = new FileReader("src/main/resources/Война и мир.txt");
        FileWriter writer = new FileWriter("src/main/resources/EnglishText_cyphered.txt");
        BufferedReader buffer = new BufferedReader(reader);
        while (buffer.ready()) {
            String str = buffer.readLine();
            writer.write(str);
        }
    }
}
