package info.solidsoft.java8.scoring;

import java.util.List;

import info.solidsoft.java8.people.Sex;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Person {

    String id;
    String firstName;
    String lastName;
    Sex sex;
    int age;
    double height;
    double weight;
}
