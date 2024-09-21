/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people;

/**
 *
 * @author bli
 */
public class Person {
    
    private String Name;
    private int age;

    public Person(String name, int age) {
        setAllAttributes(name, age);
    }

    private void setAllAttributes(String name, int age) {
        this.Name = name;
        this.age = age;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
