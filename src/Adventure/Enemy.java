package Adventure;

public class Enemy {
    private String shortName;
    private String longName;
    private String description;
    private int health;
    private Weapon weapon;
    private Room room;
    private Item itemToDrop;

    public Enemy(
            String shortName,
            String longName,
            String description,
            int health,
            Weapon weapon,
            Room room,
            Item itemToDrop) {
        this.shortName = shortName;
        this.longName = longName;
        this.description = description;
        this.health = health;
        this.weapon = weapon;
        this.room = room;
        this.itemToDrop = itemToDrop;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

    public String getDescription() {
        return description;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public boolean canAttack() {
        return weapon.canUse();
    }

    public int attack() {
        if (!weapon.canUse()) {
            return 0;
        }

        weapon.use();
        return weapon.getDamage();
    }

    public boolean possessesPlayer() {
        return false;
    }

    public boolean hit(int damage) {
        health = health - damage;

        if (health <= 0) {
            room.removeEnemy(this);
            room.addItem(weapon);

            if (itemToDrop != null) {
                room.addItem(itemToDrop);
            }

            return true;
        }

        return false;
    }
}