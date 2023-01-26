package info.solidsoft.java8.scoring;

@FunctionalInterface
public interface ScoringMapper {
    double getScoring(Loan loan, double initialScoring);
}
