package info.solidsoft.java8.scoring;

import info.solidsoft.java8.people.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
    private int age;
    private Sex sex;
    private double weightKilo;
    private double heightMeters;

    public double bmi() {
        return weightKilo / Math.pow(heightMeters, 2);
    }

}
