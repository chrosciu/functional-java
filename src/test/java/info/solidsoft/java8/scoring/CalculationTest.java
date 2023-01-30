package info.solidsoft.java8.scoring;

import java.util.Arrays;
import java.util.List;

import info.solidsoft.java8.people.Sex;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculationTest {

    @Test
    void shouldCalculateScoring() {

        Person p1 = Person.builder()
                .age(32)
                .weight(250)
                .sex(Sex.MALE)
                .firstName("Pawel")
                .lastName("T")
                .height(1.96)
                .build();

        LoanApplication loanApplication = new LoanApplication(30, 100_000);


        List<Rule> ruleList = Arrays.asList(IsFemaleRule.INSTANCE, IsHighRiskRule.INSTANCE, IsHighBmiRule.INSTANCE);

        //when
        Scoring scoring = new Scoring(100);
        for (int i = 0; i < ruleList.size(); i++) {
            scoring = ruleList.get(i).apply(scoring, p1, loanApplication);
        }

        //then
        Scoring expected = new Scoring(93);
        assertThat(scoring).isEqualTo(expected);
    }
}
