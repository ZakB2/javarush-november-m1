package ru.javarush.november.zakusilov.cryptoanalizer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.util.Scanner;

public class IOFileChannels {
    public static void access(Scanner scanner, String mode) {
//        char[] characterSet = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', 'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', '(', ')', '.', ',', '”', '”', ':', '-', '!', '?', ' '};
//        char[] characterSet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '(', ')', '.', ',', '”', '”', ':', '-', '!', '?', ' '};
//        int charactersNumber = characterSet.length;

        System.out.println("Введите путь доступа к исходному файлу:");
        Path inputFilePath = Path.of(scanner.nextLine());
        String inputFileName = inputFilePath.getFileName().toString();
        int dotIndex = inputFileName.lastIndexOf(".");
        String outputFileName = null;
//        String outputFileName = inputFileName.substring(0, dotIndex) + "_cyphered" + inputFileName.substring(dotIndex);
        if (mode.equals("1")) {
            outputFileName = inputFileName.substring(0, dotIndex) + "_cyphered" + inputFileName.substring(dotIndex);
        } else if (mode.equals("2")) {
            outputFileName = inputFileName.substring(0, dotIndex) + "_decyphered" + inputFileName.substring(dotIndex);
        }
        Path outputFilePath = inputFilePath.getParent().resolve(Path.of(outputFileName));

//        int key = KeyInput.keyTest(scanner, charactersNumber);

        try (RandomAccessFile inputFile = new RandomAccessFile(inputFilePath.toString(), "rw");
             RandomAccessFile outputFile = new RandomAccessFile(outputFilePath.toString(), "rw");
             FileChannel inputFileChannel = inputFile.getChannel();
             FileChannel outputFileChannel = outputFile.getChannel()) {
            ByteBuffer inputByteBuffer = ByteBuffer.allocate(64);
            ByteBuffer outputByteBuffer = ByteBuffer.allocate(64);
//            StringBuilder stringBuilder = new StringBuilder();
            while (inputFileChannel.read(inputByteBuffer) != -1) {
                inputByteBuffer.flip();
                while (inputByteBuffer.hasRemaining()) {
                    char symbol = (char) inputByteBuffer.get();
                    Cypher.fileCypher(scanner, symbol);
                    outputByteBuffer.put((byte) symbol);
                    outputByteBuffer.flip();
                    outputFileChannel.write(outputByteBuffer);
                    outputByteBuffer.clear();
                }
                inputByteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
