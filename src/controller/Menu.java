package controller;

import model.FighterData;

import java.util.Scanner;

public class Menu {

    /**
     * Scanner
     */
    private static Scanner input = new Scanner(System.in);

    public static void MainMenu() {
        System.out.println("----------------- MAIN MENU -----------------");

        System.out.print("1. Display fighters\n2. Sort DB\n3. WWE WarGame alpha\n4. Exit\nChoose: ");
        int choose = input.nextInt();

        switch(choose) {
            case 1:
                FighterController.displayFighters();
                break;
            case 2:
                FighterController.sortDB();
                break;
            case 3:
                FighterController.warGame();
                break;
            case 4:
                System.out.println("Program done[Battles has been save].");
                break;
        }
    }

}
