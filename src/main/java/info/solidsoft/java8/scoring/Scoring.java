package info.solidsoft.java8.scoring;

public abstract class Scoring {
    public Scoring applyRule(Rule rule, Person person, LoanApplication loanApplication) {
        return (this instanceof PositiveScoring) ? rule.apply((PositiveScoring) this, person, loanApplication) : this;
    }
}
