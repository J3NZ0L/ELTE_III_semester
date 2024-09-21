public class Defender extends Orc{

    public Defender(String name, int HP, int attack) {
        super(name, HP, attack);
    }

    @Override
    protected void applyDamageFrom(Character character) {
        this.HP-=character.attack/2;
    }
}
