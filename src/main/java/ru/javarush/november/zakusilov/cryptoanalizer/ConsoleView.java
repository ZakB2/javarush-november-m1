package ru.javarush.november.zakusilov.cryptoanalizer;

import java.util.Scanner;

public class ConsoleView {
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
            System.out.println("Выберите режим работы криптоанализатора, нажав соответствующую цифру от 1 до 4:");
            System.out.println(ENCRYPTION + "\n" + DECRYPTION + "\n" + BRUTE_FORCE + "\n" + EXIT);
            String mode = scanner.nextLine();
            switch (mode) {
                case MODE_1:
                    ReaderWriter.getText(scanner, MODE_1);
                    System.out.println("Шифрование выполнено." + "\n" + DELIMITER);
                    break;
                case MODE_2:
                    ReaderWriter.getText(scanner, MODE_2);
                    System.out.println("Расшифровка выполнена." + "\n" + DELIMITER);
                    break;
                case MODE_3:
                    System.out.println("Криптоанализ методом brute force выполнен." + "\n" + DELIMITER);
                    break;
                case MODE_4:
                    isWorking = false;
                    System.out.println("Работа криптоанализатора завершена.");
                    break;
                default:
                    System.err.println("Введено неверное значение. Повторите ввод.");
            }
        }
    }
}
