package info.solidsoft.java8.scoring;

import java.util.List;

public class DynamicCalculator {

    public Scoring calculate(Person person, LoanApplication application, List<Rule> rules) {
        Rule allRules = rules.stream().reduce(Rule.IDENTITY, Rule::andThen);
        return allRules.apply(new Scoring(100), person, application);
    }
}
