package BeerBottleChallenge;

import java.util.Scanner;
import java.util.Random;

public class BeerBottleChallenge {


    public static String currPlayer = "none";
    public static String nextPlayer = "none";
    static Scanner input = new Scanner(System.in);

    public static String scanLine() {
        String userString = input.nextLine();
        return userString;
    }





    public static void gameOver() {
        System.out.print("\n");
        System.out.println("===========================================================================");
        System.out.println("============================= Game Over ===================================");
        System.out.println("===========================================================================");
    }





    //Computer's logic to always be able to win the game. Sometime the computer would actually win the game.
    public static int compsPlay(int bottles) {
        Random random = new Random();
        int compPickBottles = 0;
        if (currPlayer.equals("user")) {
            switch (bottles) {
                case 17, 12, 7, 2:  compPickBottles = 1; break;
                case 18, 13, 8, 3: compPickBottles = 2; break;
                case 19, 14, 9, 4: compPickBottles = 3; break;
                case 20, 15, 10, 5: compPickBottles = 4; break;
                default: compPickBottles = 1 + random.nextInt(3); break;
            }
        }
        else {
            switch (bottles) {
                case 12, 7, 2: compPickBottles = 1; break;
                case 13, 8, 3: compPickBottles = 2; break;
                case 14, 9, 4: compPickBottles = 3; break;
                case 10, 5: compPickBottles = 4;    break;
                default: compPickBottles = 1 + random.nextInt(3); break;
            }
        }

        return compPickBottles;
    }
    //-------------------------------------------------------------


    // ================= Game Play ======================
    public static void startgame(String player) {

        int totalBottles = 21;
        Boolean endGame = false;

        // Your turn first
        if(player.equals("user")) {
            while(!endGame) {
                try {
                    System.out.print("\nEnter a number: ");
                    String prompt = scanLine();
                    int pickBottles = Integer.parseInt(prompt);
                    if(pickBottles <= 4 && pickBottles >= 1) {
                        totalBottles -= pickBottles;
                        nextPlayer = "comp";
                    }
                    else {
                        System.out.println("\nInvalid input... Please choose numbers between 1 and 4.");
                        continue;
                    }
                    int getBottles = 0;
                    if(totalBottles >= 1) {
                        getBottles = compsPlay(totalBottles);
                        totalBottles -= getBottles;
                        nextPlayer = "user";
                    }

                    //=========================================
                    System.out.print("\nYou picked: "+pickBottles);
                    System.out.print("\nComputer picked: "+getBottles);
                    System.out.print("\nTotal bottles remaining: "+totalBottles);
                    System.out.print("\n");
                    //=========================================
                    if(totalBottles <= 1) {
                        endGame = true;
                    }
                    else {
                        continue;
                    }
                }
                catch(NumberFormatException nfe) {
                    System.out.println("Invalid input... Enter a number between 1 and 4");
                    continue;
                }

            }
            if(nextPlayer.equals("user")) {
                System.out.println("\nYou have to pick the last bottle.. You loose!!!");
            }
            else {
                System.out.println("\nComputer has to pick the last bottle.. You win!!!");
            }
        }
        // ======================================

        //Computers turn first
        else {
            nextPlayer = "comp";
            int getBottles = 0;
            while(!endGame) {
                try {
                    if(nextPlayer.equals("comp")) {
                        if(totalBottles >= 1) {
                            getBottles = compsPlay(totalBottles);
                            totalBottles -= getBottles;
                            System.out.print("\nComputer picked: "+getBottles);
                            nextPlayer = "user";
                        }
                    }

                    if(totalBottles <= 1) {
                        break;
                    }
                    System.out.print("\nEnter a number: ");
                    String prompt = scanLine();
                    int pickBottles = Integer.parseInt(prompt);
                    if(pickBottles <= 4 && pickBottles >= 1) {
                        totalBottles -= pickBottles;
                        nextPlayer = "comp";
                    }
                    else {
                        System.out.println("\nInvalid input... Please choose numbers between 1 and 4.");
                        continue;
                    }

                    System.out.print("\nComputer picked: "+getBottles);
                    System.out.print("\nYou picked: "+pickBottles);
                    System.out.print("\nTotal bottles remaining: "+totalBottles);
                    System.out.print("\n");

                    if(totalBottles <= 1) {
                        endGame = true;
                    }
                    else {
                        continue;
                    }
                }
                catch(NumberFormatException nfe) {
                    System.out.println("Invalid input... Enter a number between 1 and 4");
                    continue;
                }
            }
            if(nextPlayer.equals("user")) {
                System.out.println("\nYou have to pick the last bottle.. You loose!!!");
            }
            else {
                System.out.println("\nComputer has to pick the last bottle.. You win!!!");
            }
        }
        // ======================================


        System.out.print("\nDo you want to play again: ");
        String res = scanLine().toLowerCase();
        if(res.equals("yes")) {
            System.out.print("\n");
            main(new String[0]);
        }
        else if(res.equals("no")) {
            System.out.println("\nThanks for playing :)");
            gameOver();
        }
        else {
            System.out.println("\nInvalid Entry.... Exiting the game");
            gameOver();
        }

    }

    // ==========================================================================


    public static void main(String[] args) {

        System.out.println("There are 21 beer bottles...");
        System.out.println("There are 2 players - Computer and you");
        System.out.println("At a time, each one can pick up any no. of bottles between 1 and 4 (Inclusive)");
        System.out.print("Will you like to play first: ");

        String ans = scanLine().toLowerCase();
        if(ans.equals("yes")) {
            currPlayer = "user";
            startgame(currPlayer);
        }
        else if(ans.equals("no")) {
            currPlayer = "com";
            startgame(currPlayer);
        }
        else {
            System.out.println("\nInvalid Entry.... Exiting the game");
            gameOver();
        }

    }





}

