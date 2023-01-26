package info.solidsoft.java8.scoring;

import info.solidsoft.java8.people.Person;

public class ObesityRule implements ScoringRule {
    @Override
    public double adjustScoring(Person person, Loan loan, double initialScoring) {
        System.out.println("Scoring before: " + initialScoring);
        System.out.println("Months: " + loan.getPeriod().getMonths());
        double scoring = calculateBMI(person.getHeight(), person.getWeight()) > 25 &&
                loan.getPeriod().getYears() > 5
                ? initialScoring / 2.0 : initialScoring;
        System.out.println("Scoring after: " + scoring);
        return scoring;
    }

    private static double calculateBMI(int height, int weight) {
        double heightInMetres = height / 100.0;
        return weight / (Math.pow(heightInMetres, 2.0));
    }
}
