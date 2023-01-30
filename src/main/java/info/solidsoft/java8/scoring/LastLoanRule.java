package info.solidsoft.java8.scoring;

import java.time.LocalDate;
import java.util.Comparator;

public class LastLoanRule implements Rule {
    @Override
    public Scoring apply(PositiveScoring scoring, Person person, LoanApplication loanApplication) {
        return person.getPastLoans()
                .stream()
                .filter(loan -> loan.getEndDate().isAfter(LocalDate.now().minusMonths(6)))
                .max(Comparator.comparing(LoanHistory::getEndDate))
                .map(xDDD -> PositiveScoring.of(scoring.getScoring() * 0.8))
                .orElse(scoring);
    }
}
