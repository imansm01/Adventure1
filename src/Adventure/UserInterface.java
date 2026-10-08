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

        while (running && adventure.playerIsAlive()) {
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
                                + "eat [item], equip [weapon], attack [enemy], "
                                + "pray, health, exit"
                );
            } else if (command.equals("health")) {
                System.out.println("Your health is "
                        + adventure.getPlayer().getHealth());
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
                    System.out.println("Your health is "
                            + adventure.getPlayer().getHealth());
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
                    System.out.println(
                            "You do not have that weapon in your inventory"
                    );
                }
            } else if (command.startsWith("attack ")) {
                String enemyName = command.substring(7);
                attackEnemy(enemyName);
            } else if (command.equals("attack")) {
                System.out.println(
                        "Write attack followed by an enemy name."
                );
            } else if (command.equals("pray")) {
                if (adventure.getPlayer().isPossessed()) {
                    adventure.getPlayer().pray();
                    System.out.println(
                            "You pray, and the ghost leaves your body."
                    );
                } else {
                    System.out.println("You are not possessed.");
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

        if (!adventure.playerIsAlive()) {
            System.out.println("You have died. Game over.");
        }
    }

    private void attackEnemy(String enemyName) {
        Player player = adventure.getPlayer();
        Weapon weapon = player.getEquippedWeapon();

        if (weapon == null) {
            System.out.println("You have no weapon equipped.");
            return;
        }

        if (!weapon.canUse()) {
            System.out.println("Your weapon is out of ammunition.");
            return;
        }

        if (adventure.getEnemyCount() == 0) {
            System.out.println("There are no enemies in this room.");
            return;
        }

        Enemy enemy = adventure.findEnemy(enemyName);

        if (enemy == null) {
            System.out.println("There is no " + enemyName + " in this room.");
            return;
        }

        int damage = adventure.playerAttack();

        System.out.println("You attack the " + enemy.getShortName()
                + " with " + weapon.getLongName()
                + " and deal " + damage + " damage.");

        if (!weapon.canUse()) {
            System.out.println("Your weapon is out of ammunition.");
        }

        boolean enemyDied = enemy.hit(damage);

        if (enemyDied) {
            System.out.println("The " + enemy.getShortName()
                    + " died and dropped its weapon.");
            return;
        }

        if (!enemy.canAttack()) {
            System.out.println("The enemy is out of ammunition.");
            return;
        }

        int enemyDamage = enemy.attack();

        System.out.println("The " + enemy.getShortName()
                + " attacks you with "
                + enemy.getWeapon().getLongName()
                + " for " + enemyDamage + " damage.");

        boolean playerDied = adventure.playerHit(enemyDamage);

        System.out.println("Your health is " + player.getHealth());

        if (playerDied) {
            System.out.println("You have died.");
        } else if (enemy.possessesPlayer()) {
            player.becomePossessed();
            System.out.println("The ghost possesses you!");
        }
    }

    private void showRoom() {
        System.out.println("You are in " + adventure.getRoomName());
        System.out.println(adventure.getRoomDescription());
    }

    private void move(String direction) {
        if (adventure.go(direction)) {
            showRoom();
        } else if (adventure.getPlayer().isPossessed()) {
            System.out.println(
                    "You are possessed. Type pray to become free."
            );
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