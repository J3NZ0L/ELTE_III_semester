import java.util.ArrayList;

public abstract class Animal {
    private String name;
    private char species;
    private int weight;
    private int mealsToday;
    private ArrayList<Integer> foodWeightsToday;

    public Animal(String name, char species, int weight, int mealsToday, ArrayList<Integer> foodWeightsToday) {
        this.name = name;
        this.species = species;
        this.weight = weight;
        this.mealsToday = mealsToday;
        this.foodWeightsToday = foodWeightsToday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getMealsToday() {
        return mealsToday;
    }

    public void setMealsToday(int mealsToday) {
        this.mealsToday = mealsToday;
    }

    public ArrayList<Integer> getFoodWeightToday() {
        return foodWeightsToday;
    }

    public void setFoodWeightToday(ArrayList<Integer> foodWeightToday) {
        this.foodWeightsToday = foodWeightToday;
    }

    public char getSpecies() {
        return species;
    }

    public void setSpecies(char species) {
        this.species = species;
    }

    public abstract boolean isEmaciated();

    public boolean consumedMoreThan1kgOfFood(){
        return this.getFoodWeightToday().stream().reduce(0, Integer::sum)>100;
    }
}
