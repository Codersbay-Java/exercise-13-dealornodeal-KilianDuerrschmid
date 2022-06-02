package application;

import java.util.Scanner;

import static application.Game.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("$$$$$$ Welcome to DEAL OR NO DEAL $$$$$$");
        System.out.println("Please press the D key for a debugging game or any other key for a normal game: ");
        if (!scanner.nextLine().equalsIgnoreCase("d")) {
            chooseNormal();
        } else {
            chooseDebug();
        }


    }
}
