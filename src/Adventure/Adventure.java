package Adventure;

public class Adventure {
    private Player player;
    private Map map;

    public Adventure() {
        map = new Map();
        Room startRoom = map.buildMap();
        player = new Player(startRoom);
    }

    public boolean go(String direction) {
        return player.move(direction);
    }

    public String getRoomName() {
        return player.getCurrentRoom().getName();
    }

    public String getRoomDescription() {
        Room room = player.getCurrentRoom();
        String description = room.getDescription();

        if (room.getItems().size() > 0) {
            description = description + "\nItems:";

            for (Item item : room.getItems()) {
                description = description + "\n- " + item.getLongName();
            }
        }

        return description;
    }

    public boolean takeItem(String shortName) {
        return player.takeItem(shortName);
    }

    public boolean dropItem(String shortName) {
        return player.dropItem(shortName);
    }

    public Player getPlayer() {
        return player;
    }

    public EatResult eat(String shortName) {
        return player.eat(shortName);
    }

    public int getHealth() {
        return player.getHealth();
    }
}
