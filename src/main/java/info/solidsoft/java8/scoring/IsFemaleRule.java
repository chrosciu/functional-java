package info.solidsoft.java8.scoring;

import info.solidsoft.java8.people.Sex;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IsFemaleRule implements Rule {

    public static IsFemaleRule INSTANCE = new IsFemaleRule();

    @Override
    public Scoring apply(Scoring scoring, Person person, LoanApplication loanApplication) {
        return person.getSex() == Sex.FEMALE ? scoring.toBuilder().scoring(scoring.getScoring() * 1.2).build() : scoring;
    }
}
