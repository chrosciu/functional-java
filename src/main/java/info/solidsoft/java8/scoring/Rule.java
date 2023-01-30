package info.solidsoft.java8.scoring;

public interface Rule {
    Scoring apply(PositiveScoring scoring, Person person, LoanApplication loanApplication);

    default Rule compose(Rule before) {
        return (scoring, person, loanApplication) -> scoring
                        .applyRule(before, person, loanApplication)
                        .applyRule(this, person, loanApplication);
    }

    Rule IDENTITY = (scoring, person, loanApplication) -> scoring;

}
