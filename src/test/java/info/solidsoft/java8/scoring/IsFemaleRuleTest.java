package info.solidsoft.java8.scoring;


import info.solidsoft.java8.people.Sex;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IsFemaleRuleTest {

    private IsFemaleRule sut = IsFemaleRule.INSTANCE;

    @Test
    public void shouldCalculate() {

        //given
        PositiveScoring sco = PositiveScoring.of(100);

        Person person = Person.builder().sex(Sex.FEMALE).build();

        //when
        LoanApplication loan = new LoanApplication(30, 100_000);

        //when
        Scoring scoring = sut.apply(sco, person, loan);

        //then
        assertThat(((PositiveScoring)scoring).getScoring()).isEqualTo(120);
    }
}