package info.solidsoft.java8.scoring;

import info.solidsoft.java8.people.Person;

import java.util.Arrays;
import java.util.stream.Stream;

public class ScoringCalculator {
    public static ScoringMapper getScoringMapper(Person person, ScoringRule... rules) {
        return (loan, initialScoring) -> {
            Stream<ScoringRule> ruleStream = Arrays.stream(rules);
            ScoringRule scoringRule = ruleStream.reduce(ScoringRule.identity(), ScoringRule::composed);
            System.out.println("Scoring before: " + initialScoring);
            double scoring = scoringRule.adjustScoring(person, loan, initialScoring);
            System.out.println("Scoring after: " + scoring);
            return scoring;
        };
    }
}
