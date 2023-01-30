package info.solidsoft.java8.scoring;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;

@UtilityClass
public class Calculator {

    private final List<Rule> ruleList = Arrays.asList(IsFemaleRule.INSTANCE, IsHighRiskRule.INSTANCE, IsHighBmiRule.INSTANCE, OutlookRule.INSTANCE);

    public static Scoring calculate(Person person, LoanApplication application) {

//        //when
//        Scoring scoring = new Scoring(100);
//        for (int i = 0; i < ruleList.size(); i++) {
//            scoring = ruleList.get(i).apply(scoring, person, application);
//        }
//        return scoring;
        return null;
    }
}
