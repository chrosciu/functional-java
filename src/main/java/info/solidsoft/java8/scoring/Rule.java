package info.solidsoft.java8.scoring;

public interface Rule {
    Scoring apply(Scoring scoring, Person person, LoanApplication loanApplication);

    default Rule compose(Rule before) {
        return (scoring, person, loanApplication) ->
                apply(before.apply(scoring, person, loanApplication), person, loanApplication);
    }

    Rule IDENTITY = (scoring, person, loanApplication) -> scoring;

}
