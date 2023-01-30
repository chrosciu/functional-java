package info.solidsoft.java8.scoring;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public final class PositiveScoring extends Scoring {
    @Getter
    private final double scoring;

    public static PositiveScoring of(double scoring) {
        return new PositiveScoring(scoring);
    }
}
