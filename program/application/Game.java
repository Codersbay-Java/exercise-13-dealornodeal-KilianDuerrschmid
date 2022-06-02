package application;


import java.util.*;

public class Game {
    public static Scanner scanner = new Scanner(System.in);
    public static Briefcase bc = new Briefcase();
    public static int rounds = 1;
    public static int firstCaseValue;
    public static int firstCaseNumber;
    public static Map<Integer, Integer> briefcases;


    public static void chooseDebug() {
        briefcases = bc.briefcases;
        gameFlow();
    }

    public static void chooseNormal() {
        briefcases = bc.shuffleBriefcases();
        gameFlow();
    }


    public static int getOffer() {
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : briefcases.entrySet()) {
            Integer value = entry.getValue();
            sum += value;
        }
        sum += firstCaseValue;
        int expected = sum / briefcases.size();
        int expected2 = expected * rounds;
        int offer = expected2 / 10;
        return offer;
    }

    public static void gameFlow() {
        if (rounds == 1) {
            firstRound();
        }
        if (rounds == 10) {
            lastRound();
        } else {
            bc.printNumbers();
            System.out.println();
            System.out.println("Current round: " + rounds);
            System.out.println();

            switch (rounds) {
                case 1:
                    for (int i = 0; i < 6; i++) {
                        eliminateCase();
                    }
                    break;
                case 2:
                    for (int i = 0; i < 5; i++) {
                        eliminateCase();
                    }
                    break;
                case 3:
                    for (int i = 0; i < 4; i++) {
                        eliminateCase();
                    }
                    break;
                case 4:
                    for (int i = 0; i < 3; i++) {
                        eliminateCase();
                    }
                    break;
                case 5:
                    for (int i = 0; i < 2; i++) {
                        eliminateCase();
                    }
                    break;
                default:
                    eliminateCase();
                    break;
            }

            System.out.println("The bank offers you $" + getOffer());
            takeDeal();
        }


    }

    private static void takeDeal() {
        System.out.println("""
                Do you accept the offer?
                Please press Y for YES (stop playing) or N for NO (continue playing):""");

        String continuePlaying = scanner.next();

        if (continuePlaying.equalsIgnoreCase("y")) {
            System.out.println("Congratulations, you won $" + getOffer());
            System.out.println("Game Over, thanks for playing!");

        } else if (continuePlaying.equalsIgnoreCase("n")) {
            rounds++;
            gameFlow();
        } else {
            System.out.println("Please only press Y or N.");
            takeDeal();
        }
    }

    private static void lastRound() {
        int switchCase = 0;
        for (Integer key : briefcases.keySet()) {
            switchCase = key;
        }
        System.out.println();
        System.out.println("The suitcase you chose in the beginning was number [" + firstCaseNumber + "]");
        System.out.println("Suitcase number [" + switchCase + "] is the last one remaining.");
        System.out.println("""
                Now you have the chance to switch the cases.
                Do you want to switch the cases?
                Please press Y for yes and N for no: """);

        makeFinalChoice(switchCase);

    }

    private static void makeFinalChoice(int switchCase) {
        String finalChoice = scanner.next();
        if (finalChoice.equalsIgnoreCase("y")) {

            System.out.println("Opening suitcase number " + switchCase);
            System.out.println("Congratulations, you won $" + briefcases.get(switchCase));
            System.out.println("Game over, thanks for playing!");
        } else if (finalChoice.equalsIgnoreCase("n")) {
            System.out.println("Opening suitcase num ber [" + firstCaseNumber + "]");
            System.out.println("Congratulations, you won $" + firstCaseValue);
            System.out.println("Game over, thanks for playing!");
        } else {
            System.out.println("Please only press Y or N.");
            makeFinalChoice(switchCase);
        }
    }

    public static void eliminateCase() {
        System.out.println("Pick one of the remaining suitcases to eliminate: ");
        try {
            int eliminate = scanner.nextInt();
            if (eliminate > 0 && eliminate < 27 && briefcases.containsKey(eliminate)) {
                System.out.println("Case " + eliminate + " was eliminated.");
                System.out.println("It contained $" + briefcases.get(eliminate));
                briefcases.remove(eliminate);
                removeDisplay(eliminate);
                System.out.println();
            } else {
                System.out.println("Not a valid choice, pick again.");
                eliminateCase();
            }
        } catch(InputMismatchException e){
            System.out.println("Not a valid choice, pick again.");
            System.out.println();
            scanner.nextLine();
            eliminateCase();
        }

    }

    public static void firstRound() {
        bc.printNumbers();
        System.out.println();
        System.out.println("Please choose a suitcase (1 - 26):");
        try {
        int firstChoice = scanner.nextInt();
        if (firstChoice > 0 && firstChoice < 27 && briefcases.containsKey(firstChoice)) {
            System.out.println("You chose number " + firstChoice);
            System.out.println();
            firstCaseNumber = firstChoice;
            firstCaseValue = briefcases.get(firstChoice);
            briefcases.remove(firstChoice);
            removeDisplay(firstChoice);
        } else {
            System.out.println("Not a valid choice, pick again.");
            firstRound();
        }
        } catch(InputMismatchException e){
            System.out.println("Not a valid choice, pick again.");
            System.out.println();
            scanner.nextLine();
            firstRound();
        }

    }

    public static void removeDisplay(int index) {
        bc.briefcases.remove(index);
    }

}
