package Adventure;

import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;
    private final Adventure adventure;

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
                System.out.println(
                        "Commands: go north, go south, go east, go west, look, help, exit"
                );
            } else if (command.equals("go north")) {
                move("north");
            } else if (command.equals("go south")) {
                move("south");
            } else if (command.equals("go east")) {
                move("east");
            } else if (command.equals("go west")) {
                move("west");
            } else {
                System.out.println("I don't understand that command.");
            }
        }
    }

    private void move(String direction) {
        boolean moved = adventure.go(direction);

        if (moved) {
            System.out.println("You are in " + adventure.getRoomName());
            System.out.println(adventure.getRoomDescription());
        } else {
            System.out.println("You cannot go that way");
        }
    }
}