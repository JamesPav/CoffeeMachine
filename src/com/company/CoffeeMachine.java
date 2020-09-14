package com.company;

import java.util.Scanner;

public class CoffeeMachine {

    enum CoffeeMachineState {
        READY,
        EXECUTING_BUY_ACTION,
        EXECUTING_FILL_ACTION,
        EXECUTING_TAKE_ACTION,
        EXECUTING_REMAINING_ACTION,
        EXECUTING_EXIT_ACTION,
    }
    static int fillingStage = 0;
    static CoffeeMachineState coffeeMachineState = CoffeeMachineState.READY;
    static int amountOfMoney = 550;
    static int amountOfWater = 400;
    static int amountOfMilk = 540;
    static int amountOfCoffeeBeans = 120;
    static int amountOfDisposableCups = 9;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            switch (coffeeMachineState) {
                case READY:
                    System.out.println("Write action (buy, fill, take, remaining, exit):");
                    break;
                case EXECUTING_BUY_ACTION:
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
                    break;
                case EXECUTING_FILL_ACTION:
                    switch (fillingStage) {
                        case 0:
                            System.out.println("Write how many ml of water do you want to add:");
                            break;
                        case 1:
                            System.out.println("Write how many ml of milk do you want to add:");
                            break;
                        case 2:
                            System.out.println("Write how many grams of coffee beans do you want to add:");
                            break;
                        case 3:
                            System.out.println("Write how many disposable cups of coffee do you want to add:");
                            break;
                    }

            }
            singleInputInterface(scanner.nextLine());
        }

    }

    private static void singleInputInterface(String input) {
        switch (coffeeMachineState) {
            case READY:
                processInput(input);
                break;
            case EXECUTING_BUY_ACTION:
                executeBuyAction(input);
                break;
            case EXECUTING_FILL_ACTION:
                executeFillAction(input);
                break;
        }
    }

    private static void processInput(String input) {
        switch (input) {
            case "buy":
                coffeeMachineState = CoffeeMachineState.EXECUTING_BUY_ACTION;
                break;
            case "fill":
                coffeeMachineState = CoffeeMachineState.EXECUTING_FILL_ACTION;
                break;
            case "take":
                coffeeMachineState = CoffeeMachineState.EXECUTING_TAKE_ACTION;
                executeTakeAction();
                break;
            case "remaining":
                coffeeMachineState = CoffeeMachineState.EXECUTING_REMAINING_ACTION;
                executeRemainingAction();
                break;
            case "exit":
                coffeeMachineState = CoffeeMachineState.EXECUTING_EXIT_ACTION;
                executeExitAction();
                break;
        }
    }

    private static void executeExitAction() {
        System.exit(0);
    }

    private static void executeRemainingAction() {
        printState();
        coffeeMachineState = CoffeeMachineState.READY;
    }

    private static void executeBuyAction(String input) {
        int itemToBuy;
        if (input.equals("back")) {
            coffeeMachineState = CoffeeMachineState.READY;
            return;
        } else {
            itemToBuy = Integer.parseInt(input);
        }
        switch (itemToBuy) {
            case 1:
                if (hasEnoughResources(250, 0, 4)) {
                    System.out.println("I have enough resources, making you a coffee!");
                    amountOfWater -= 250;
                    amountOfCoffeeBeans -= 16;
                    amountOfMoney += 4;
                    amountOfDisposableCups--;
                } else {
                    System.out.println("Sorry not enough resources!");
                }
                break;
            case 2:
                if (hasEnoughResources(350, 75, 20)) {
                    System.out.println("I have enough resources, making you a coffee!");
                    amountOfWater -= 350;
                    amountOfMilk -= 75;
                    amountOfCoffeeBeans -= 20;
                    amountOfMoney += 7;
                    amountOfDisposableCups--;
                } else {
                    System.out.println("Sorry not enough resources!");
                }
                break;
            case 3:
                if (hasEnoughResources(200, 100, 12)) {
                    System.out.println("I have enough resources, making you a coffee!");
                    amountOfWater -= 200;
                    amountOfMilk -= 100;
                    amountOfCoffeeBeans -= 12;
                    amountOfMoney += 6;
                    amountOfDisposableCups--;
                } else {
                    System.out.println("Sorry not enough resources!");
                }
                break;
        }
        coffeeMachineState = CoffeeMachineState.READY;
    }

    private static boolean hasEnoughResources(int water, int milk, int beans) {
        return ((amountOfWater - water) >= 0
                && (amountOfMilk - milk) >= 0
                && (amountOfCoffeeBeans - beans) >= 0
                && (amountOfDisposableCups - 1) >= 0);
    }

    private static void executeFillAction(String input) {
        switch (fillingStage) {
            case 0:
                amountOfWater += Integer.parseInt(input);
                break;
            case 1:
                amountOfMilk += Integer.parseInt(input);
                break;
            case 2:
                amountOfCoffeeBeans += Integer.parseInt(input);
                break;
            case 3:
                amountOfDisposableCups += Integer.parseInt(input);
                fillingStage = 0;
                coffeeMachineState = CoffeeMachineState.READY;
                return;
        }
        fillingStage++;
    }

    private static void executeTakeAction() {
        System.out.printf("I gave you $%d\n", amountOfMoney);
        amountOfMoney = 0;
        coffeeMachineState = CoffeeMachineState.READY;
    }

    private static void printState() {
        System.out.printf("The coffee machine has:\n" +
                        "%d of water\n" +
                        "%d of milk\n" +
                        "%d of coffee beans\n" +
                        "%d of disposable cups\n" +
                        "$%d of money\n",
                amountOfWater,
                amountOfMilk,
                amountOfCoffeeBeans,
                amountOfDisposableCups,
                amountOfMoney);
    }
}
