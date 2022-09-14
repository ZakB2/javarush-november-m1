package ru.javarush.november.zakusilov.cryptoanalizer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.util.Scanner;

public class IOChannels {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        String charactersString = "";
//        System.out.println();
//        try (FileReader reader = new FileReader("src/main/resources/!English alphabet character set.txt")) {
//            BufferedReader buffer = new BufferedReader(reader);
//            charactersString = buffer.readLine();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        char[] charactersSet = charactersString.toCharArray();
//        char[] charactersSet = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', 'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', '(', ')', '.', ',', '”', '”', ':', '-', '!', '?', ' '};
        char[] charactersSet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '(', ')', '.', ',', '”', '”', ':', '-', '!', '?', ' '};
        int charactersNumber = charactersSet.length;

        System.out.println("Введите путь доступа к исходному файлу:");
        Path inputFilePath = Path.of(scanner.nextLine());
        String inputFileName = inputFilePath.getFileName().toString();
        int dotIndex = inputFileName.lastIndexOf(".");
        String outputFileName = inputFileName.substring(0, dotIndex) + "_cyphered" + inputFileName.substring(dotIndex);
        Path outputFilePath = inputFilePath.getParent().resolve(Path.of(outputFileName));

//        Ниже метод для проверки ключа шифрования, он в отдельно классе, поэтому не стал его отправлять, для простоты проверки установил ключ равный 1
//        int key = KeyInput.keyTest(scanner, charactersNumber);
        int key = 1;

        try (RandomAccessFile inputFile = new RandomAccessFile(inputFilePath.toString(), "rw");
             RandomAccessFile outputFile = new RandomAccessFile(outputFilePath.toString(), "rw");
             FileChannel inputFileChannel = inputFile.getChannel();
             FileChannel outputFileChannel = outputFile.getChannel()) {
            ByteBuffer inputByteBuffer = ByteBuffer.allocate(64);
            ByteBuffer outputByteBuffer = ByteBuffer.allocate(64);
            while (inputFileChannel.read(inputByteBuffer) != -1) {
                inputByteBuffer.flip();
                while (inputByteBuffer.hasRemaining()) {
                    char symbol = ((char) inputByteBuffer.get());
                    for (int i = 0; i < charactersSet.length; i++) {
                        if (symbol == charactersSet[i]) {
                            if (i + key < charactersSet.length) {
                                symbol = charactersSet[i + key];
                                break;
                            } else if (i + key >= charactersSet.length) {
                                symbol = charactersSet[i + key - charactersNumber];
                                break;
                            }
                        }
                    }
                    outputByteBuffer.put((byte) symbol);
                }
                outputByteBuffer.flip();
                outputFileChannel.write(outputByteBuffer);
                outputByteBuffer.clear();

                inputByteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
