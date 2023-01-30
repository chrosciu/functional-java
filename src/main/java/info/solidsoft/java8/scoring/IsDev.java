package info.solidsoft.java8.scoring;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IsDev implements Rule {

    public static IsDev INSTANCE = new IsDev();

    @Override
    public Scoring apply(Scoring scoring, Person person, LoanApplication loanApplication) {
        if("DEV".equals(person.getOccupation())) {
            return scoring.withScoring(scoring.getScoring() * 2);
        }
        return scoring;
    }
}
