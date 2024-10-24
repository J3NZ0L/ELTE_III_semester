import java.util.ArrayList;


public class Horse extends Animal {

    public Horse(String name, char species, int weight, int mealsToday, ArrayList<Integer> foodWeightToday) {
        super(name,species, weight, mealsToday, foodWeightToday);
    }
    public boolean isEmaciated(){
        return this.getWeight()<60;
    }
}
