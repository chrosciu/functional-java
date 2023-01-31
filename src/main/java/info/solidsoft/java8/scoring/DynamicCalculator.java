package info.solidsoft.java8.scoring;

import java.util.List;

public class DynamicCalculator {

    public Scoring calculate(Person person, LoanApplication application, List<Rule> rules) {
        Scoring scoring = new Scoring(100);
        for (Rule rule : rules) {
            scoring = rule.apply(scoring, person, application);
        }
        return scoring;
    }
}
