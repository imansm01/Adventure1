package Adventure;

public class Player {


        private Room currentRoom;

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
    }

