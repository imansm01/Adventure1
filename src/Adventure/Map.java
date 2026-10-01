package Adventure;

public class Map {
    public Room buildMap() {
        Room room1 = new Room("Room 1", "A dark room with two doors.");
        Room room2 = new Room("Room 2", "Water drips from the ceiling.");
        Room room3 = new Room("Room 3", "There are old books everywhere.");
        Room room4 = new Room("Room 4", "You hear a strange sound.");
        Room room5 = new Room("Room 5", "A mysterious room.");
        Room room6 = new Room("Room 6", "There is a strange smell.");
        Room room7 = new Room("Room 7", "The room is very cold.");
        Room room8 = new Room("Room 8", "You see something moving.");
        Room room9 = new Room("Room 9", "A bright light is coming from above.");

        room1.setEast(room2);
        room1.setSouth(room4);

        room2.setWest(room1);
        room2.setEast(room3);

        room3.setWest(room2);
        room3.setSouth(room6);

        room4.setNorth(room1);
        room4.setEast(room5);
        room4.setSouth(room7);

        room5.setWest(room4);

        room6.setNorth(room3);
        room6.setSouth(room9);

        room7.setNorth(room4);
        room7.setEast(room8);

        room8.setWest(room7);
        room8.setEast(room9);

        room9.setWest(room8);
        room9.setNorth(room6);

        Item lamp = new Item("lamp", "a shiny lamp");
        Item key = new Item("key", "an old rusty key");
        Item sword = new Item("sword", "a sharp sword");
        Food bread = new Food("bread", "a loaf of stale bread", 10);
        Food mushroom = new Food("mushroom", "a glowing mushroom", -50);

        room1.addItem(lamp);
        room2.addItem(key);
        room1.addItem(bread);
        room4.addItem(sword);
        room5.addItem(mushroom);

        return room1;

    }
}