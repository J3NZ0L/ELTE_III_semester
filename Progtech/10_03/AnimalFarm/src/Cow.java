import java.util.ArrayList;


public class Cow extends Animal {
    public Cow(String name, char species, int weight, int mealsToday, ArrayList<Integer> foodWeightToday) {
        super(name, species, weight, mealsToday, foodWeightToday);
    }
    public boolean isEmaciated(){
        return this.getWeight()<100;
    }
}
