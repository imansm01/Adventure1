package Adventure;

public class Ghost extends Enemy {
    public Ghost(
            String shortName,
            String longName,
            String description,
            int health,
            Weapon weapon,
            Room room,
            Item itemToDrop) {
        super(shortName, longName, description, health, weapon, room, itemToDrop);
    }

    @Override
    public boolean possessesPlayer() {
        return true;
    }
}