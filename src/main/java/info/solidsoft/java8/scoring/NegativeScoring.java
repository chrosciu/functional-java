package info.solidsoft.java8.scoring;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class NegativeScoring extends Scoring {
    @NonNull
    @Getter
    private final String message;

    public static NegativeScoring of(String message) {
        return new NegativeScoring(message);
    }
}
