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

        Item lamp = new Item("lamp", "a shiny brass lamp");
        Item key = new Item("key", "an old rusty key");
        Item ring = new Item("ring", "an old silver ring");

        Food bread = new Food("bread", "a loaf of stale bread", 10);

        MeleeWeapon sword =
                new MeleeWeapon("sword", "a rusty sword", 12);

        RangedWeapon revolver =
                new RangedWeapon("revolver", "an old revolver", 25, 6);

        room1.addItem(lamp);
        room1.addItem(bread);
        room1.addItem(sword);

        room2.addItem(key);
        room2.addItem(revolver);

        Ghost ghost = new Ghost(
                "ghost",
                "a restless ghost",
                "The ghost goes silently through the room.",
                25,
                new MeleeWeapon("ghost-claws", "ghostly claws", 5),
                room3,
                ring
        );

        room3.addEnemy(ghost);

        return room1;
    }
}