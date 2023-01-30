package info.solidsoft.java8.scoring;

import org.junit.jupiter.api.Test;

import static info.solidsoft.java8.people.Sex.FEMALE;
import static info.solidsoft.java8.people.Sex.MALE;
import static org.junit.jupiter.api.Assertions.*;

class ScoringCalculatorTest {

    @Test
    public void shouldCalculateForWoman() {
        Person woman = new Person(30, FEMALE, 50, 1.7);
        Loan loan = new Loan(5);

        int scoring = ScoringCalculator.calculate(woman, loan);

        assertEquals(120, scoring);
    }

    @Test
    public void shouldCalculateForMan() {
        Person woman = new Person(30, MALE, 50, 1.7);
        Loan loan = new Loan(5);

        int scoring = ScoringCalculator.calculate(woman, loan);

        assertEquals(100, scoring);
    }

    @Test
    public void shouldCalculateForLongCredit() {
        Person woman = new Person(55, FEMALE, 50, 1.7);
        Person man = new Person(60, MALE, 50, 1.7);
        Loan loan = new Loan(10);

        int scoringWoman = ScoringCalculator.calculate(woman, loan);
        int scoringMan = ScoringCalculator.calculate(man, loan);

        assertEquals(115, scoringWoman);
        assertEquals(95, scoringMan);
    }

    @Test
    public void shouldCalculateForHighBMI() {
        Person man = new Person(30, MALE, 120, 2);
        Loan loan = new Loan(10);

        int scoringMan = ScoringCalculator.calculate(man, loan);

        assertEquals(75, scoringMan);
    }

}