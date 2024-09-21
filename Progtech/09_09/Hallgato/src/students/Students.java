package students;

public class Students {
    private String name;
    private String nationality;
    private double gradeAvg;

    public Students(String name, String nationality, double gradeAvg) {
        name=name;
        nationality=nationality;
        gradeAvg=gradeAvg;
    }

    public String getName(){
        return this.name;
    }

    public String getNationality(){
        return this.nationality;
    }

    public double getGradeAvg(){
        return this.gradeAvg;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setGradeAvg(double gradeAvg) {
        this.gradeAvg = gradeAvg;
    }


}
