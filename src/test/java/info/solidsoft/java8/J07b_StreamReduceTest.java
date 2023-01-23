package info.solidsoft.java8;

import info.solidsoft.java8.util.StreamTestFixture.StreamParallelism;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static info.solidsoft.java8.util.StreamTestFixture.PARALLEL_TEST_CASE_NAME_FORMAT;
import static info.solidsoft.java8.util.StreamTestFixture.changeStreamParallelism;
import static org.assertj.core.api.Assertions.assertThat;

public class J07b_StreamReduceTest {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @SuppressWarnings("Convert2MethodRef")
    @Test
    public void shouldAddNumbersUsingReduce() {
        //given
        final List<Integer> input = Arrays.asList(2, 3, 5, 7);

        //when
        final int sum = input.stream().reduce(0, (acc, x) -> acc + x);  //could be simplified to Integer::sum

        //then
        assertThat(sum).isEqualTo(2 + 3 + 5 + 7);
    }

    @SuppressWarnings("Convert2MethodRef")  //Much more readable in that case
    @Test
    public void shouldConcatNumbersBrokenWithParallelStream() {
        //given
        final List<Integer> input = Arrays.asList(2, 3, 5, 7);

        //when
        final String result = input.stream()    //WARNING: Broken with parallel stream
                .reduce(
                        new StringBuilder(),
                        (acc, x) -> acc.append(x),
                        (sb1, sb2) -> sb1.append(sb2))
                .toString();

        //then
        assertThat(result).isEqualToIgnoringCase("2357");
    }

    //Please ignore at the first approach - will be explained by the way of J10_ParallelStreamsTest
    @Test
    public void shouldConcatNumbers() {
        //given
        final List<Integer> input = Arrays.asList(2, 3, 5, 7);

        //when
        final String result = input.stream()
                .reduce(
                        new StringBuilder(),
                        (acc, x) -> {
                            log.info("Accumulator '{}', '{}'", acc, x);
                            return new StringBuilder(acc).append(x);
                        },
                        (sb1, sb2) -> {
                            log.info("Combiner '{}', '{}'", sb1, sb2);
                            return new StringBuilder(sb1).append(sb2);
                        })
                .toString();

        //then
        assertThat(result).isEqualToIgnoringCase("2357");
    }

    @Test
    public void shouldFindMaxUsingReduce() {
        //given
        final List<Integer> input = Arrays.asList(4, 2, 6, 3, 8, 1);

        //when
        final int max = 0;  //input.stream()...

        //then
        assertThat(max).isEqualTo(8);
    }

    @ParameterizedTest
    @MethodSource("info.solidsoft.java8.util.StreamTestFixture#provideTrueFalse")
    public void shouldSimulateMapUsingReduce(StreamParallelism requestedParallelism) {
        //given
        final List<Integer> input = Arrays.asList(2, 3, 5, 7);
        Stream<Integer> stream = changeStreamParallelism(input.stream(), requestedParallelism);

        //when
        final List<Integer> doubledPrimes = null;   //stream.

        //then
        assertThat(doubledPrimes).containsExactly(2 * 2, 3 * 2, 5 * 2, 7 * 2);
    }

    @ParameterizedTest
    @MethodSource("info.solidsoft.java8.util.StreamTestFixture#provideTrueFalse")
    public void shouldSimulateFilterUsingReduce(StreamParallelism requestedParallelism) {
        //given
        final List<Integer> input = Arrays.asList(2, 3, 4, 5, 6);
        Stream<Integer> stream = changeStreamParallelism(input.stream(), requestedParallelism);

        //when
        final List<Integer> onlyEvenNumbers = null;   //stream.

        //then
        assertThat(onlyEvenNumbers).containsExactly(2, 4, 6);
    }
}
