package info.solidsoft.java8.scoring;

public interface Rule {

    Scoring apply(Scoring scoring, Person person, LoanApplication loanApplication);
}
