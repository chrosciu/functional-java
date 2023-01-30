package info.solidsoft.java8.scoring;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static info.solidsoft.java8.scoring.Color.GINGER;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OutlookRule implements Rule {

    public static OutlookRule INSTANCE = new OutlookRule();

    @Override
    public Scoring apply(Scoring scoring, Person person, LoanApplication loanApplication) {

        if (person.getHairColor() == GINGER || person.isBold()) {
            return scoring.toBuilder().scoring(scoring.getScoring() * 0.5).build();
        } else {
            return scoring;
        }
    }
}
