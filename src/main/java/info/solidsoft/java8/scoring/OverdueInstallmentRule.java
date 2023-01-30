package info.solidsoft.java8.scoring;

public class OverdueInstallmentRule implements Rule {
    @Override
    public Scoring apply(Scoring scoring, Person person, LoanApplication loanApplication) {
        return person.getBikCheckResult()
                .map(BikCheckResult::isOverduePayment)
                .filter(Boolean::booleanValue)
                .map(xDDD -> scoring.toBuilder().scoring(0).build())
                .orElse(scoring);
    }
}
