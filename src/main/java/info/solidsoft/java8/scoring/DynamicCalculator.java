package info.solidsoft.java8.scoring;

import java.util.List;

import lombok.Builder;
import lombok.Singular;

@Builder
public class DynamicCalculator {

    @Singular
    public List<Rule> rules;

    public Scoring calculate(Person person, LoanApplication application) {
        //when
        Scoring scoring = new Scoring(100);
        for (Rule rule : rules) {
            scoring = rule.apply(scoring, person, application);
        }
        return scoring;
    }
}
