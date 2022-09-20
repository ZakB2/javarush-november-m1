package ru.javarush.november.zakusilov.cryptoanalizer.view;

import ru.javarush.november.zakusilov.cryptoanalizer.utils.BruteForce;
import ru.javarush.november.zakusilov.cryptoanalizer.utils.ReaderWriter;

import java.util.Scanner;

public class ConsoleView {

    private ConsoleView() {
    }

    private static final String DELIMITER = "*******************************************************";
    private static final String ENCRYPTION = "1 Шифрование";
    private static final String DECRYPTION = "2 Расшифровка";
    private static final String BRUTE_FORCE = "3 Криптоанализ методом brute force";
    private static final String EXIT = "4 Завершение работы криптоанализатора";
    public static final String MODE_1 = "1";
    public static final String MODE_2 = "2";
    public static final String MODE_3 = "3";
    public static final String MODE_4 = "4";

    public static void menuOutput(Scanner scanner) {
        boolean isWorking = true;
        while (isWorking) {
            ReaderWriter.printLine("Выберите режим работы криптоанализатора, нажав соответствующую цифру от 1 до 4:");
            ReaderWriter.printLine(ENCRYPTION + "\n" + DECRYPTION + "\n" + BRUTE_FORCE + "\n" + EXIT);
            String mode = scanner.nextLine();
            switch (mode) {
                case MODE_1:
                    ReaderWriter.getText(scanner, MODE_1);
                    ReaderWriter.printLine(DELIMITER);
                    break;
                case MODE_2:
                    ReaderWriter.getText(scanner, MODE_2);
                    ReaderWriter.printLine(DELIMITER);
                    break;
                case MODE_3:
                    BruteForce.hackKey(scanner, MODE_3);
                    ReaderWriter.printLine(DELIMITER);
                    break;
                case MODE_4:
                    isWorking = false;
                    ReaderWriter.printLine("Работа криптоанализатора завершена.");
                    break;
                default:
                    ReaderWriter.printErrorLine("Введено неверное значение. Повторите ввод.");
            }
        }
    }
}