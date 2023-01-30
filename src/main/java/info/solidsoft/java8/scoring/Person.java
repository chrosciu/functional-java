package info.solidsoft.java8.scoring;

import info.solidsoft.java8.people.Sex;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Person {
    String firstName;
    String lastName;
    Sex sex;
    int age;
    double heightMeters;
    double weightKg;
    String occupation;
}
