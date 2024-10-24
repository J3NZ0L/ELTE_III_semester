import java.util.ArrayList;


public class Goat extends Animal {
    public Goat(String name, char species, int weight, int mealsToday, ArrayList<Integer> foodWeightToday) {
        super(name, species, weight, mealsToday, foodWeightToday);
    }
    public boolean isEmaciated(){
        return this.getWeight()<12;
    }
}
