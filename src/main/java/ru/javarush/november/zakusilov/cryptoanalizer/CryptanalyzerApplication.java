package ru.javarush.november.zakusilov.cryptoanalizer;

import ru.javarush.november.zakusilov.cryptoanalizer.view.ConsoleView;

import java.util.Scanner;

public class CryptanalyzerApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsoleView.menuOutput(scanner);
    }
}
