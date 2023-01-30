package info.solidsoft.java8.scoring;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ScoringCalculator {
    public static Scoring calculate(Person person, LoanApplication application) {
//        List<Rule> ruleList = Arrays.asList(IsFemaleRule.INSTANCE, IsHighRiskRule.INSTANCE, IsHighBmiRule.INSTANCE);
//
//        Scoring scoring = new Scoring(100);
//        for (int i = 0; i < ruleList.size(); i++) {
//            scoring = ruleList.get(i).apply(scoring, person, application);
//        }
//
//        return scoring;
        return null;
    }
}
