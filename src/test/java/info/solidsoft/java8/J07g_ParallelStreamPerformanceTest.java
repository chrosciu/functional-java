package info.solidsoft.java8;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 1)
@Fork(value = 1)
@Measurement(iterations = 1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class J07g_ParallelStreamPerformanceTest {
    @State(Scope.Benchmark)
    public static class Lists {
        final List<Integer> arrayList = new ArrayList<>();
        final List<Integer> linkedList = new LinkedList<>();

        {
            IntStream.range(1, 1_000_000).forEach(i -> {
                arrayList.add(i);
                linkedList.add(i);
            });
        }
    }

    @Benchmark
    public void arrayListSequentialSum(Lists lists, Blackhole blackhole) {
        int sum = lists.arrayList.stream().reduce(0, Integer::sum);
        blackhole.consume(sum);
    }

    @Benchmark
    public void arrayListParallelSum(Lists lists, Blackhole blackhole) {
        int sum = lists.arrayList.parallelStream().reduce(0, Integer::sum);
        blackhole.consume(sum);
    }

    @Benchmark
    public void linkedListSequentialSum(Lists lists, Blackhole blackhole) {
        int sum = lists.linkedList.stream().reduce(0, Integer::sum);
        blackhole.consume(sum);
    }

    @Benchmark
    public void linkedListParallelSum(Lists lists, Blackhole blackhole) {
        int sum = lists.linkedList.parallelStream().reduce(0, Integer::sum);
        blackhole.consume(sum);
    }

    @Benchmark
    public void arrayListSequentialCollect(Lists lists, Blackhole blackhole) {
        Set<Integer> set = lists.arrayList.stream().collect(Collectors.toSet());
        blackhole.consume(set);
    }

    @Benchmark
    public void arrayListParallelCollect(Lists lists, Blackhole blackhole) {
        Set<Integer> set = lists.arrayList.parallelStream().collect(Collectors.toSet());
        blackhole.consume(set);
    }

    @Test
    @Disabled("For manual run only - takes a lot of time")
    public void benchmark() throws Exception {
        String[] argv = {};
        org.openjdk.jmh.Main.main(argv);
    }
}
