package info.solidsoft.java8.scoring;

import java.util.List;
import java.util.function.Function;

class Scoring {

    private final double INITIAL_SCORE = 100.0;
    private final double FEMALE_RETIRE_AGE = 60;
    private final double MALE_RETIRE_AGE = 65;

    List<Function<Credit, Double>> functions = List.of(
            (credit) -> {
                if("FEMALE".equals(credit.getPerson().getSex())) {
                    return Double.valueOf(credit.getCurrentScore() * 1.2);
                }
                return INITIAL_SCORE;
            },

            (credit) -> {
                double v;
                if(credit.getPerson().isFemale()) {
                    v = FEMALE_RETIRE_AGE - credit.getPerson().getAge();
                } else {
                    v = MALE_RETIRE_AGE - credit.getPerson().getAge();
                }
                v = v - credit.getHowLong();
                if(v < 0) {

                    return credit.getCurrentScore() - Math.abs(v);
                }
                return credit.getCurrentScore();
            }
    );

    double calculateScore(Person person, int howLong) {
        Credit credit = new Credit(person, howLong, INITIAL_SCORE);

        Double score = INITIAL_SCORE;

        for(Function<Credit, Double> func : functions){
            credit.setCurrentScore(score);
            score =  func.apply(credit);
        }

        return score;
    }
}
