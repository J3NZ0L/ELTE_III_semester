

public class MainCharacter extends Character{
    protected double defense;

    public MainCharacter(String name, int HP, int attack, double defense) {
        super(name, HP, attack);
        this.defense = defense;
    }

    protected void applyDamageFrom(Character character){
        this.HP -= character.getAttack()/this.defense;
    }

    public double getDefense() {
        return defense;
    }
}
