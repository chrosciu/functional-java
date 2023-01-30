package info.solidsoft.java8.scoring;

public class OverdueInstallmentRule implements Rule {
    @Override
    public Scoring apply(PositiveScoring scoring, Person person, LoanApplication loanApplication) {
        return person.getBikCheckResult()
                .map(BikCheckResult::isOverduePayment)
                .filter(Boolean::booleanValue)
                .map(xDDD -> PositiveScoring.of(0))
                .orElse(scoring);
    }
}
