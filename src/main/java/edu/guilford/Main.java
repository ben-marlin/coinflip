package edu.guilford;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        // announce purpose, prompt for response
        System.out.println("Hello friend! I'm going to flip a coin. Call it!\n");

        // get response
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();

        // validate response
        while ((!response.equals("heads")) && (!response.equals("tails"))) {
            System.out.println("\nOnly heads or tails - call it again!\n");
            response = scanner.nextLine();
        }

        // generate result
        String[] possibilities = {"heads", "tails"};
        Random random = new Random();
        String result = possibilities[random.nextInt(2)];

        // handle outcome
        if (response.equals(result)) {
            System.out.println("\nIt was " + result + ", you win!\n");
        } else {
            System.out.println("\nIt was " + result + ", you lose!\n");

        }

        // close resource leak
        scanner.close();
    }
}