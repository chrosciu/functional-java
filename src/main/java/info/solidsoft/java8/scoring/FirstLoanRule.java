package info.solidsoft.java8.scoring;

public class FirstLoanRule implements Rule {

    @Override
    public Scoring apply(Scoring scoring, Person person, LoanApplication loanApplication) {
        if (person.getPastLoans().isEmpty()) {
            return scoring.toBuilder()
                    .scoring(scoring.getScoring() * 0.7)
                    .build();
        } else {
            return scoring;
        }
    }
}
