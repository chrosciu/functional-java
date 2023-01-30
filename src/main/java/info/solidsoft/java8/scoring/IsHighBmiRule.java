package info.solidsoft.java8.scoring;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IsHighBmiRule implements Rule {

    public static IsHighBmiRule INSTANCE = new IsHighBmiRule();

    @Override
    public Scoring apply(PositiveScoring scoring, Person person, LoanApplication loanApplication) {
        return loanApplication.getYears() > 5 && calculateBmi(person) > 25 ? PositiveScoring.of(scoring.getScoring() - 5) : scoring;
    }

    private double calculateBmi(Person person) {
        return person.getWeightKg() / Math.pow(person.getHeightMeters(), 2);
    }
}
