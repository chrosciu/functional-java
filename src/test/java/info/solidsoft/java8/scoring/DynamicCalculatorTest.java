package info.solidsoft.java8.scoring;

import info.solidsoft.java8.people.Sex;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DynamicCalculatorTest {

    @Test
    public void should() {

        //given
        Person p1 = Person.builder()
                .age(32)
                .weightKg(250)
                .sex(Sex.MALE)
                .firstName("Pawel")
                .lastName("T")
                .heightMeters(1.96)
                .build();

        LoanApplication loanApplication = new LoanApplication(30, 100_000);

        Scoring scoring = DynamicCalculator.builder()
                .rule(IsFemaleRule.INSTANCE)
                .rule(IsHighRiskRule.INSTANCE)
                .rule(IsHighBmiRule.INSTANCE)
                .rule(OutlookRule.INSTANCE)
                .rule(new HasStableJobRule(Arrays.asList(Occupation.IT_GUY, Occupation.DOCTOR))).build()
                .calculate(p1, loanApplication);

        //then
        Scoring expected = PositiveScoring.of(46.5);
        assertThat(scoring).isEqualTo(expected);
    }

    @Test
    public void shouldCallRulesInGivenOrder() {

        Person person = mock(Person.class);
        LoanApplication loanApplication = mock(LoanApplication.class);

        Rule rule1 = getRule(100, "rule1");
        Rule rule2 = getRule(100, "rule2");
        Rule rule3 = getRule(100, "rule3");

        DynamicCalculator.builder()
                .rule(rule1)
                .rule(rule2)
                .rule(rule3)
                .build()
                .calculate(person, loanApplication);

        verify(rule1).apply(any(PositiveScoring.class), any(Person.class), any(LoanApplication.class));
        verify(rule2).apply(any(PositiveScoring.class), any(Person.class), any(LoanApplication.class));
        verify(rule3).apply(any(PositiveScoring.class), any(Person.class), any(LoanApplication.class));
    }

    private Rule getRule(double score, String name) {
        Rule rule = new Rule() {
            @Override
            public Scoring apply(PositiveScoring scoring, Person person, LoanApplication loanApplication) {
                return PositiveScoring.of(score);
            }
        };
        return Mockito.spy(rule);
    }
}