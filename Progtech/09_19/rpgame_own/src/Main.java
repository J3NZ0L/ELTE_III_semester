import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args){
        MainCharacter maincharacter = new MainCharacter("John Cena", 300, 61, 5);
        Berserker berserker = new Berserker("Berser", 30, 30);
        Defender defender = new Defender("Defender", 40, 10);
        BLackDragon blackDragon = new BLackDragon("Black Dragon", 200, 61);
        RedDragon redDragon = new RedDragon("Red Dragon", 300, 70);
        Fighter fighter = new Fighter("Jackie", 50, 50);
        Fighter fighter2 = new Fighter("Chan", 40, 20);

        ArrayList<Character> characters = new ArrayList<>();
        characters.add(maincharacter);
        characters.add(berserker);
        characters.add(defender);
        characters.add(blackDragon);
        characters.add(redDragon);
        characters.add(fighter);
        characters.add(fighter2);

        int attacker;
        int attacked;
        Random random = new Random();
        while (characters.size()>1){
            attacker = random.nextInt(characters.size());
            attacked = random.nextInt(characters.size()-1);
            if (attacked>=attacker){
                attacked+=1;
            }
            System.out.println(characters.get(attacker).getName() + ", " +  characters.get(attacker).getHP() + " attacks " + characters.get(attacked).getName() + ", " +  characters.get(attacked).getHP());
            characters.get(attacker).attack(characters.get(attacked));
            System.out.println(characters.get(attacker).getName() + ", " +  characters.get(attacker).getHP() + " attacked " + characters.get(attacked).getName() + ", " +  characters.get(attacked).getHP());
            if (!characters.get(attacked).isAlive()){
                System.out.println(characters.get(attacked).getName() + " has died. ");
                characters.remove(attacked);
            }
        }
        System.out.println("---------------------------------");
        System.out.println("And the winner is: " + characters.get(0).getName() + ", " +  characters.get(0).getHP());
    }
}
