public class Emu extends Animal{

    public Emu(String name, char species, int weight, int mealsToday, int foodWeightToday) {
        super(name, species, weight, mealsToday, foodWeightToday);
    }
    public boolean isEmaciated(){
        return this.getWeight()<20;
    }
}
