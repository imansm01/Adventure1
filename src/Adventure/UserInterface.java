package Adventure;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private Adventure adventure;

    public UserInterface() {
        scanner = new Scanner(System.in);
        adventure = new Adventure();
    }

    public void startProgram() {
        System.out.println("You are in " + adventure.getRoomName());
        System.out.println(adventure.getRoomDescription());

        boolean running = true;

        while (running) {
            System.out.print("> ");
            String command = scanner.nextLine();

            if (command.equals("exit")) {
                System.out.println("Goodbye!");
                running = false;
            } else if (command.equals("look")) {
                System.out.println("You are in " + adventure.getRoomName());
                System.out.println(adventure.getRoomDescription());
            } else if (command.equals("help")) {
                System.out.println("Commands: go north, go south, go east, go west, look, inventory, take lamp, drop lamp, help, exit");
            } else if (command.equals("inventory")) {
                showInventory();
            } else if (command.startsWith("take ")) {
                String itemName = command.substring(5);

                if (adventure.takeItem(itemName)) {
                    System.out.println("You picked up " + itemName);
                } else {
                    System.out.println("There is nothing like " + itemName + " to take around here");
                }
            } else if (command.startsWith("drop ")) {
                String itemName = command.substring(5);

                if (adventure.dropItem(itemName)) {
                    System.out.println("You dropped " + itemName);
                } else {
                    System.out.println("You don't have anything like " + itemName + " in your inventory");
                }
            } else if (command.equals("go north")) {
                move("north");
            } else if (command.equals("go south")) {
                move("south");
            } else if (command.equals("go east")) {
                move("east");
            } else if (command.equals("go west")) {
                move("west");
            } else {
                System.out.println("I don't understand that command");
            }
        }
    }

    private void move(String direction) {
        if (adventure.go(direction)) {
            System.out.println("You are in " + adventure.getRoomName());
            System.out.println(adventure.getRoomDescription());
        } else {
            System.out.println("You cannot go that way");
        }
    }

    private void showInventory() {
        if (adventure.getPlayer().getInventory().size() == 0) {
            System.out.println("Your inventory is empty");
        } else {
            System.out.println("You are carrying:");

            for (Item item : adventure.getPlayer().getInventory()) {
                System.out.println("- " + item.getLongName());
            }
        }
    }
}