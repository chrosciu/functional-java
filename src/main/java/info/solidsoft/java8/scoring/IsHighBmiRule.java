package info.solidsoft.java8.scoring;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IsHighBmiRule implements Rule {

    public static IsHighBmiRule INSTANCE = new IsHighBmiRule();

    @Override
    public Scoring apply(Scoring scoring, Person person, LoanApplication loanApplication) {
        return loanApplication.getYears() > 5 && calculateBmi(person) > 25 ? scoring.toBuilder().scoring(scoring.getScoring() - 5).build() : scoring;
    }

    private double calculateBmi(Person person) {
        return person.getWeight() / Math.pow(person.getHeight(), 2);
    }
}
