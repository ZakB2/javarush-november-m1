package ru.javarush.november.zakusilov.cryptoanalizer;

import java.util.Scanner;

public class ConsoleView {
    private static boolean isWorking = true;
    private static final String DELIMITER = "*******************************************************";
    public static void menuOutput(Scanner scanner) {
        while (isWorking) {
            System.out.println("Выберите режим работы криптоанализатора, нажав соответствующую цифру от 1 до 4:");
            System.out.println("1 Шифрование" + "\n" + "2 Расшифровка" + "\n" + "3 Криптоанализ методом brute force" + "\n" + "4 Завершение работы криптоанализатора");
            String mode = scanner.nextLine();
            switch (mode) {
                case "1":
//                    IOFileChannels.access(scanner, mode);
                    Cypher.toCypher(scanner);
                    System.out.println("Шифрование выполнено." + "\n" + DELIMITER);
                    break;
                case "2":
//                    IOFileChannels.access(scanner, mode);
                    Decypher.toDecypher(scanner);
                    System.out.println("Расшифровка выполнена."+ "\n" + DELIMITER);
                    break;
                case "3":
                    System.out.println("Криптоанализ методом brute force выполнен."+ "\n" + DELIMITER);
                    break;
                case "4":
                    isWorking = false;
                    System.out.println("Завершение работы криптоанализатора.");
                    break;
                default:
                    System.err.println("Введено неверное значение. Повторите ввод.");
            }
        }
    }
}
