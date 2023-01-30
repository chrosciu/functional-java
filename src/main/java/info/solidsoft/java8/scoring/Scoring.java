package info.solidsoft.java8.scoring;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@Builder(toBuilder = true)
@Value
@With
public class Scoring {

    double scoring;

    public static Scoring of(double scoring) {
        return new Scoring(scoring);
    }
}
