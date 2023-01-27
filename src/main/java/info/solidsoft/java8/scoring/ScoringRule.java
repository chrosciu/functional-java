package info.solidsoft.java8.scoring;

import info.solidsoft.java8.people.Person;

@FunctionalInterface
public interface ScoringRule {
    double adjustScoring(Person person, Loan loan, double initialScoring);

    default ScoringRule compose(ScoringRule before) {
        return (person, loan, initialScoring) ->
                adjustScoring(person, loan, before.adjustScoring(person, loan, initialScoring));
    }

    static ScoringRule composed(ScoringRule before, ScoringRule after) {
        return after.compose(before);
    }

    ScoringRule IDENTITY = (person, loan, initialScoring) -> initialScoring;
}
