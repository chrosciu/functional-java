package info.solidsoft.java8.scoring;

import info.solidsoft.java8.people.Sex;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IsHighRiskRule implements Rule {

    public static IsHighRiskRule INSTANCE = new IsHighRiskRule();

    @Override
    public Scoring apply(PositiveScoring scoring, Person person, LoanApplication loanApplication) {

        int over = yearsOver(person, loanApplication);

        if (over > 0) {
            return PositiveScoring.of(scoring.getScoring() - over);
        }
        return scoring;
    }

    private int yearsOver(Person person, LoanApplication loanApplication) {
        if (person.getSex() == Sex.FEMALE) {
            return person.getAge() + loanApplication.getYears() - 65;
        } else if (person.getSex() == Sex.MALE) {
            return person.getAge() + loanApplication.getYears() - 60;
        }
        return 0;
    }
}
