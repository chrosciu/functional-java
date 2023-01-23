package info.solidsoft.java8;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

import static org.awaitility.Awaitility.await;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Implement simple utility methods for running blocks of code in multiple threads
 */
public class J05_MultiRunnerTest {

    @Test
    public void shouldExecuteTasksInMultipleThreads() {
        //given
        final Map<Long, String> threads = new ConcurrentHashMap<>();

        //when
        IntStream.range(0, 100).forEach(i ->
                MultiRunner.runMultiThreaded(() -> {
                    final Thread thread = Thread.currentThread();
                    threads.put(thread.getId(), thread.getName());
                })
        );

        //then
        await().untilAsserted(() -> assertThat(threads.size()).isGreaterThan(1));
        assertThat(threads).doesNotContainKey(Thread.currentThread().getId());
    }

    @Test
    public void shouldRunMultipleBlocks() {
        //given
        final LongAdder counter = new LongAdder();

        //when
        MultiRunner.runMultiThreaded(Arrays.asList(
                () -> counter.add(1),
                () -> counter.add(2),
                () -> counter.add(3)
        ));

        //then
        await().untilAsserted(() -> assertThat(counter.sum()).isEqualTo(1L + 2L + 3L));
    }

    @Test
    public void shouldRunTheSameTaskMultipleTimes() {
        //given
        final LongAdder counter = new LongAdder();

        //when
        MultiRunner.runMultiThreaded(3, () -> counter.add(7));

        //then
        await().untilAsserted(() -> assertThat(counter.sum()).isEqualTo(7L + 7L + 7L));
    }

}