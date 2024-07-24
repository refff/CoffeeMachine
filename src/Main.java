import java.util.Scanner;

class Main {
    private static int waterAmount = 400;
    private static int milkAmount = 540;
    private static int beansAmount = 120;
    private static int disposableCups = 9;
    private static int cash = 550;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    static void menu() {
        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String option = scanner.nextLine();

            switch (option) {
                case "buy" -> coffeeMenu();
                case "fill" -> fillMachine();
                case "take" -> {
                    System.out.printf("\nI gave you $%d \n\n", cash);
                    cash = 0;
                }
                case "remaining" -> availableSupplies();
            }

            if (option.equals("exit")) break;
        }
    }

    static void availableSupplies() {
        System.out.printf("""

                        The coffee machine has:\s
                        %d ml of water
                        %d ml of milk
                        %d g of coffee beans
                        %d disposable cups
                        $%d of money

                        """,
                waterAmount,
                milkAmount,
                beansAmount,
                disposableCups,
                cash);
    }

    static void fillMachine() {
        System.out.println("\nWrite how many ml of water you want to add:");
        waterAmount += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        milkAmount += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        beansAmount += scanner.nextInt();
        System.out.println("Write how many disposable cups you want to add:");
        disposableCups += scanner.nextInt();
        System.out.println();
        scanner.nextLine();
    }

    static void coffeeMenu() {
        System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
        String chosenCoffee = scanner.nextLine();

        makingCoffee(chosenCoffee);
    }

    static void makingCoffee(String chosenCoffee) {

        switch (chosenCoffee) {
            case "1" -> {
                useOfIngredients(250, 0, 16, 4);
            }
            case "2" -> {
                useOfIngredients(350, 75, 20, 7);
            }
            case "3" -> {
                useOfIngredients(200, 100, 12, 6);
            }

        }
    }

    static void useOfIngredients(int water, int milk, int beans, int money) {
        boolean isDone = true;

        if (waterAmount >= water) waterAmount -= water;
        else {
            outOfIngredient("water");
            isDone = false;
        }

        if (beansAmount >= beans && isDone) beansAmount -= beans;
        else {
            outOfIngredient("beans");
            isDone = false;
        }

        if (milkAmount >= milk && isDone) milkAmount -= milk;
        else {
            outOfIngredient("milk");
            isDone = false;
        }

        if (disposableCups >= 1 && isDone) disposableCups -= 1;
        else {
            outOfIngredient("cups");
            isDone = false;
        }

        cash += money;

        if (isDone) {
            enoughResources();
        }
    }

    static void enoughResources() {
        System.out.println("I have enough resources, making you a coffee!\n");
    }

    static void outOfIngredient(String ingredient) {
        System.out.printf("Sorry, not enough %s!\n\n", ingredient);
    }

}