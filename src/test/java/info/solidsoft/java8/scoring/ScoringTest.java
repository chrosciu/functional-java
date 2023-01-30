package info.solidsoft.java8.scoring;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoringTest {

    @Test
    public void shouldCalculateForBasicMan() {

        Scoring scoring = new Scoring();
        Person person = new Person(50, 25, "MALE");
        int howLong = 1;

        assertEquals(100.0, scoring.calculateScore(person, howLong));
    }

    @Test
    public void shouldCalculateForBasicFemale() {

        Scoring scoring = new Scoring();
        Person person = new Person(50, 25, "FEMALE");
        int howLong = 1;

        assertEquals(120.0, scoring.calculateScore(person, howLong));
    }

    @Test
    public void shouldCalculateForRetirements() {

        Scoring scoring = new Scoring();
        Person person = new Person(50, 25, "FEMALE");
        int howLong = 1;

        assertEquals(120.0, scoring.calculateScore(person, howLong));
    }

    @Test
    public void shouldCalculateForRetirements2() {

        Scoring scoring = new Scoring();
        Person person = new Person(50, 25, "FEMALE");
        int howLong = 20;

        assertEquals(110.0, scoring.calculateScore(person, howLong));
    }

    @Test
    public void shouldCalculateForRetirements3() {

        Scoring scoring = new Scoring();
        Person person = new Person(50, 25, "MALE");
        int howLong = 21;

        assertEquals(94.0, scoring.calculateScore(person, howLong));
    }


}