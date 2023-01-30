package info.solidsoft.java8.scoring;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HasStableJobRule implements Rule {

    private final List<Occupation> occupationList;

    @Override
    public Scoring apply(Scoring scoring, Person person, LoanApplication loanApplication) {
        return occupationList.contains(person.getOccupation()) ? scoring.toBuilder().scoring(scoring.getScoring() * 1.5).build() : scoring;
    }
}
