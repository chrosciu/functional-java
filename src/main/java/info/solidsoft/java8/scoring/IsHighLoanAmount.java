package info.solidsoft.java8.scoring;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IsHighLoanAmount implements Rule {

    public static IsHighLoanAmount INSTANCE = new IsHighLoanAmount();

    @Override
    public Scoring apply(PositiveScoring scoring, Person person, LoanApplication loanApplication) {
        if(loanApplication.getAmount() > 100_000) {
            return PositiveScoring.of(scoring.getScoring() * 0.8);
        }
        return scoring;
    }
}
