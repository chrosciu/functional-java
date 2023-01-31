package info.solidsoft.java8.scoring;

public interface Rule {

    Scoring apply(Scoring scoring, Person person, LoanApplication loanApplication);

    default Rule andThen(Rule after) {
        return (scoring, person, loanApplication) -> after.apply(this.apply(scoring, person, loanApplication), person, loanApplication);
    }

    static Rule IDENTITY = (scoring, person, loanApplication) -> scoring;
}
