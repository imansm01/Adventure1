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
            return player.getCurrentRoom().getDescription();
        }
    }
