package info.solidsoft.java8.scoring;

import static info.solidsoft.java8.people.Sex.FEMALE;

public class ScoringCalculator {

    public static int calculate(Person candidate, Loan loan) {

        int scoring = 100;
        if (candidate.getSex() == FEMALE) {
            scoring += scoring * 0.2;
        }

        int overPensionAge = candidate.getAge() + loan.getDurationYears() - (candidate.getSex() == FEMALE ? 60 : 65);

        if (overPensionAge > 0) {
            scoring -= overPensionAge;
        }

        if (candidate.bmi() > 25 && loan.getDurationYears() > 5) {
            scoring = scoring - (int) Math.ceil((candidate.bmi() - 25) * 5);
        }

        return scoring;
    }

}
