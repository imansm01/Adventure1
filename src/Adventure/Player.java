package Adventure;

import java.util.ArrayList;

public class Player {
    private Room currentRoom;
    private ArrayList<Item> inventory = new ArrayList<>();
    private int health = 100;

    public Player(Room startRoom) {
        currentRoom = startRoom;
    }

    public boolean move(String direction) {
        Room newRoom = null;

        if (direction.equals("north")) {
            newRoom = currentRoom.getNorth();
        } else if (direction.equals("south")) {
            newRoom = currentRoom.getSouth();
        } else if (direction.equals("east")) {
            newRoom = currentRoom.getEast();
        } else if (direction.equals("west")) {
            newRoom = currentRoom.getWest();
        }

        if (newRoom != null) {
            currentRoom = newRoom;
            return true;
        }

        return false;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public int getHealth() {
        return health;
    }

    public Item findItem(String shortName) {
        for (Item item : inventory) {
            if (item.getShortName().equals(shortName)) {
                return item;
            }
        }

        return null;
    }

    public boolean takeItem(String shortName) {
        Item item = currentRoom.findItem(shortName);

        if (item == null) {
            return false;
        }

        currentRoom.removeItem(item);
        inventory.add(item);
        return true;
    }

    public boolean dropItem(String shortName) {
        Item item = findItem(shortName);

        if (item == null) {
            return false;
        }

        inventory.remove(item);
        currentRoom.addItem(item);
        return true;
    }

    public EatResult eat(String shortName) {
        Item item = findItem(shortName);
        boolean itemIsInInventory = true;

        if (item == null) {
            item = currentRoom.findItem(shortName);
            itemIsInInventory = false;
        }

        if (item == null) {
            return EatResult.NOT_FOUND;
        }

        if (!(item instanceof Food)) {
            return EatResult.NOT_FOOD;
        }

        Food food = (Food) item;
        health = health + food.getHealthPoints();

        if (itemIsInInventory) {
            inventory.remove(item);
        } else {
            currentRoom.removeItem(item);
        }

        return EatResult.EATEN;
    }
}