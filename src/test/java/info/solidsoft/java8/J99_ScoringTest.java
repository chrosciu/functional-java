package info.solidsoft.java8;

import info.solidsoft.java8.people.Person;
import info.solidsoft.java8.people.Sex;
import info.solidsoft.java8.scoring.Loan;
import info.solidsoft.java8.scoring.LoanType;
import info.solidsoft.java8.scoring.ObesityRule;
import info.solidsoft.java8.scoring.ScoringCalculator;
import info.solidsoft.java8.scoring.ScoringMapper;
import info.solidsoft.java8.scoring.SexRule;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Percentage;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;

public class J99_ScoringTest {
    @Test
    public void shouldCalculateScoringAccordingToRules() {
        Loan loan = new Loan(LoanType.REGULAR, Money.of(1000, "PLN"), Period.ofYears(10));
        Person person = new Person("Marcin", Sex.MALE, 105, 180,
                LocalDate.of(1982, 7, 30));

        ScoringMapper scoringMapper = ScoringCalculator.getScoringMapper(person, new ObesityRule(), new SexRule());

        double scoring = scoringMapper.getScoring(loan, 100);
        Assertions.assertThat(scoring).isCloseTo(40.0, Percentage.withPercentage(1));
    }
}
