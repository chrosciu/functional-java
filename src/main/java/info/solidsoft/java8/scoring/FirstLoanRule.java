package info.solidsoft.java8.scoring;

public class FirstLoanRule implements Rule {

    @Override
    public Scoring apply(PositiveScoring scoring, Person person, LoanApplication loanApplication) {
        if (person.getPastLoans().isEmpty()) {
            return PositiveScoring.of(scoring.getScoring() * 0.7);
        } else {
            return scoring;
        }
    }
}
