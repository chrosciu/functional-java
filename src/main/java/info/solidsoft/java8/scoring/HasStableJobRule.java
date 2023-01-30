package info.solidsoft.java8.scoring;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class HasStableJobRule implements Rule {

    private final List<Occupation> occupationList;

    @Override
    public Scoring apply(PositiveScoring scoring, Person person, LoanApplication loanApplication) {
        return occupationList.contains(person.getOccupation()) ? PositiveScoring.of(scoring.getScoring() * 1.5) : scoring;
    }
}
