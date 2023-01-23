package info.solidsoft.java8.util;

import org.assertj.core.internal.bytebuddy.asm.Advice;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;


public class StreamTestFixture {

    public static final String PARALLEL_TEST_CASE_NAME_FORMAT = "[{index}] {method} ({0})";

    public enum StreamParallelism {
        SEQUENTIAL, PARALLEL;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    @SuppressWarnings("unused")
    public static Stream<StreamParallelism> provideTrueFalse() {
        //PARALLEL is disabled by default to not complicate things at the first approach.
        //Should be enabled and explained by the J10_ParallelStreamsTest.
        return Stream.of(StreamParallelism.SEQUENTIAL/*, StreamParallelism.PARALLEL*/);
    }

    public static <T> Stream<T> changeStreamParallelism(Stream<T> stream, StreamParallelism requestedParallelism) {
        //StreamParallelism could have a field with Function<Stream<?>, Stream<?>> to eliminate switch-case,
        // but the implementation looks awkward
        switch (requestedParallelism) {
            case SEQUENTIAL:
                return stream.sequential();
            case PARALLEL:
                return stream.parallel();
            default:
                throw new IllegalArgumentException("Unexpected enum value: " + requestedParallelism);
        }
    }
}
