public abstract class Character {
    protected String name;
    protected int HP;
    protected int attack;

    public Character(String name, int HP, int attack) {
        this.name = name;
        this.HP = HP;
        this.attack = attack;
    }

    public void attack(Character character) {
        if (character.isAlive()) {
            character.attacked(this);
        }
    }

    public void attacked(Character character) {
        if (this.isAlive()) {
            this.applyDamageFrom(character);
        }
    }

    public boolean isAlive(){
        return this.HP>=0;
    }

    protected void applyDamageFrom(Character character){
            this.HP-=character.getAttack();
    }

    public String getName() {
        return name;
    }

    public int getHP() {
        return HP;
    }

    public int getAttack() {
        return attack;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }
}
