package info.solidsoft.java8.scoring;

import lombok.Builder;
import lombok.Singular;

import java.util.List;

@Builder
public class DynamicCalculator {

    @Singular
    public List<Rule> rules;

    public Scoring calculate(Person person, LoanApplication application) {
        PositiveScoring initialScoring = PositiveScoring.of(100);
        Rule composedRule = rules.stream().reduce(Rule.IDENTITY, (before, after) -> after.compose(before));
        return composedRule.apply(initialScoring, person, application);
    }
}
