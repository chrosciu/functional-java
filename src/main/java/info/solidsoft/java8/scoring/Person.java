package info.solidsoft.java8.scoring;

import info.solidsoft.java8.people.Sex;
import lombok.Builder;
import lombok.Value;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Builder
@Value
public class Person {
    String firstName;
    String lastName;
    Sex sex;
    int age;
    Occupation occupation;
    Color hairColor;

    public boolean isBold() {
        return hairColor == null;
    }
    double heightMeters;
    double weightKg;
    String occupation;
    List<LoanHistory> pastLoans;
    BikCheckResult bik;

    public Optional<BikCheckResult> getBikCheckResult() {
        return Optional.ofNullable(bik);
    }

    public List<LoanHistory> getPastLoans() {
        return Optional.ofNullable(pastLoans)
                .orElseGet(Collections::emptyList);
    }
}
