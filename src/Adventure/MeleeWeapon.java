package Adventure;

public class MeleeWeapon extends Weapon {
    public MeleeWeapon(String shortName, String longName, int damage) {
        super(shortName, longName, damage);
    }

    @Override
    public boolean canUse() {
        return true;
    }

    @Override
    public void use() {
        // Sværdet bruger ikke ammunition.
    }

    @Override
    public String getAttackVerb() {
        return "slash";
    }

    @Override
    public String getUsesLeftText() {
        return "You can use this weapon again.";
    }
}