import students.Students;

import java.util.ArrayList;

import console.Console;

public class Main {


    public static void main(String[] args) {
        ArrayList<Students> students = new ArrayList<Students>();

        System.out.println("Adjon meg hallgatokat:");
        System.out.println("Hallgatok szama:");
        int numOfStudents = Console.readInt();
        String name;
        String nationality;
        double avg;

        System.out.println("Adja meg soronkent a kovetkezo adatokat: nev, nemzetiseg, jegyek atlaga");
        for (int i=0; i<numOfStudents; i++) {
            System.out.println(i+". hallgato adatai:");
            System.out.println("Nev:");
            name = Console.readLine();
            System.out.println("Nemzetiseg:");
            nationality = Console.readLine();
            System.out.println("Jegyek atlaga:");
            avg = Console.readDouble();
            System.out.println();
            students.add(new Students(name, nationality, avg));
        }

        ArrayList<Students> minhallgatok = new ArrayList<>();
        ArrayList<Students> maxhallgatok = new ArrayList<>();
        double minavg =5;
        double maxavg=0;
        for (int i=0; i<students.size(); i++){
            double currentmax = students.get(i).getGradeAvg();
            double currentmin = students.get(i).getGradeAvg();

            if (currentmax > maxavg) {
                maxavg = currentmax;
            }
            if (currentmin < minavg) {
                minavg = currentmin;
            }
        }

        System.out.println("Legjobb atlagu hallgatok: ");
        for (Students student : students) {
            if (student.getGradeAvg() == maxavg) {
                maxhallgatok.add(student);
                System.out.println(student.getName());
            }
        }

        System.out.println("Legrosszabb atlagu hallgatok: ");
        for (int i=0; i<students.size(); i++){
            if (students.get(i).getGradeAvg() == minavg){
                minhallgatok.add(students.get(i));
                System.out.println(students.get(i).getName());
            }
        }

        System.out.println("Osztondijat kaphatnak:");
        for (int i=0; i<students.size(); i++){
            if (students.get(i).getGradeAvg() > 4.0){
                System.out.println(students.get(i).getName());
            }
        }
    }
}