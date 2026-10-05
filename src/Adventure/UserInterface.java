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
        showRoom();
        boolean running = true;

        while (running) {
            System.out.print("> ");
            String command = scanner.nextLine();

            if (command.equals("exit")) {
                System.out.println("Goodbye!");
                running = false;
            } else if (command.equals("look")) {
                showRoom();
            } else if (command.equals("help")) {
                System.out.println(
                        "Commands: go north, go south, go east, go west, "
                                + "look, inventory, take [item], drop [item], "
                                + "eat [item], health, equip [weapon], attack, exit"
                );
            } else if (command.equals("health")) {
                System.out.println("Your health is " + adventure.getHealth());
            } else if (command.equals("inventory")) {
                showInventory();
            } else if (command.startsWith("take ")) {
                String itemName = command.substring(5);

                if (adventure.takeItem(itemName)) {
                    System.out.println("You picked up " + itemName);
                } else {
                    System.out.println("There is nothing like "
                            + itemName + " to take around here");
                }
            } else if (command.startsWith("drop ")) {
                String itemName = command.substring(5);

                if (adventure.dropItem(itemName)) {
                    System.out.println("You dropped " + itemName);
                } else {
                    System.out.println("You don't have anything like "
                            + itemName + " in your inventory");
                }
            } else if (command.startsWith("eat ")) {
                String itemName = command.substring(4);
                EatResult result = adventure.eat(itemName);

                if (result == EatResult.EATEN) {
                    System.out.println("You ate " + itemName);
                    System.out.println("Your health is " + adventure.getHealth());
                } else if (result == EatResult.NOT_FOOD) {
                    System.out.println("You cannot eat " + itemName);
                } else {
                    System.out.println("You cannot find " + itemName);
                }
            } else if (command.startsWith("equip ")) {
                String weaponName = command.substring(6);

                if (adventure.equip(weaponName)) {
                    System.out.println("You equipped " + weaponName);
                } else {
                    System.out.println("You do not have that weapon in your inventory");
                }
            } else if (command.equals("attack")) {
                System.out.println(adventure.attack());
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

    private void showRoom() {
        System.out.println("You are in " + adventure.getRoomName());
        System.out.println(adventure.getRoomDescription());
    }

    private void move(String direction) {
        if (adventure.go(direction)) {
            showRoom();
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