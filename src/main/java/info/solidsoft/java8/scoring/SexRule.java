package info.solidsoft.java8.scoring;

import info.solidsoft.java8.people.Person;
import info.solidsoft.java8.people.Sex;

public class SexRule implements ScoringRule {
    @Override
    public double adjustScoring(Person person, Loan loan, double initialScoring) {
        return Sex.FEMALE == person.getSex() ? initialScoring * 1.2 : initialScoring * 0.8;
    }
}
