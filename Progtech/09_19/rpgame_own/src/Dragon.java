public abstract class Dragon extends Character{
    protected int ATTACK_THRESHOLD;

    public Dragon(String name, int HP, int attack, int ATTACK_THRESHOLD) {
        super(name, HP, attack);
        this.ATTACK_THRESHOLD = ATTACK_THRESHOLD;
    }

    protected void applyDamageFrom(Character character){
        if (character.attack>ATTACK_THRESHOLD){
            this.HP -= character.attack;
        }
    }

    public int getATTACK_THRESHOLD() {
        return ATTACK_THRESHOLD;
    }
}
