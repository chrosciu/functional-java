package info.solidsoft.java8;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

public class J09c_WindowTest {

    @Test
    public void shouldCreatePairListFromFiniteStream() {
        Stream<String> stream = finiteLettersStream(10);

        List<List<String>> windows = pairList(stream);

        Assertions.assertThat(windows).containsExactly(
                List.of("A", "B"),
                List.of("B", "C"),
                List.of("C", "D"),
                List.of("D", "E"),
                List.of("E", "F"),
                List.of("F", "G"),
                List.of("G", "H"),
                List.of("H", "I"),
                List.of("I", "J")
        );
    }

    @Test
    public void shouldCreateTupleListFromFiniteStream() {
        Stream<String> stream = finiteLettersStream(10);

        List<List<String>> windows = tupleList(stream, 3);

        Assertions.assertThat(windows).containsExactly(
                List.of("A", "B", "C"),
                List.of("B", "C", "D"),
                List.of("C", "D", "E"),
                List.of("D", "E", "F"),
                List.of("E", "F", "G"),
                List.of("F", "G", "H"),
                List.of("G", "H", "I"),
                List.of("H", "I", "J")
        );
    }

    @Test
    public void shouldCreateWindowListFromFiniteStream() {
        Stream<String> stream = finiteLettersStream(10);

        List<List<String>> windows = windowList(stream, 3, 2);

        Assertions.assertThat(windows).containsExactly(
                List.of("A", "B", "C"),
                List.of("C", "D", "E"),
                List.of("E", "F", "G"),
                List.of("G", "H", "I")
        );
    }

    @Test
    public void shouldCreateWindowStreamFromFiniteStream() {
        Stream<String> stream = finiteLettersStream(10);

        Stream<List<String>> windows = windowStream(stream, 3, 2);

        Assertions.assertThat(windows).containsExactly(
                List.of("A", "B", "C"),
                List.of("C", "D", "E"),
                List.of("E", "F", "G"),
                List.of("G", "H", "I")
        );
    }

    @Test
    public void shouldCreateWindowStreamFromInFiniteStream() {
        Stream<String> stream = inifiniteLettersStream();

        Stream<List<String>> windows = windowStream(stream, 3, 2);

        Assertions.assertThat(windows.limit(4)).containsExactly(
                List.of("A", "B", "C"),
                List.of("C", "D", "E"),
                List.of("E", "F", "G"),
                List.of("G", "H", "I")
        );
    }

    private static Stream<String> inifiniteLettersStream() {
        return Stream.iterate('A', c -> 'Z' == c ? 'A' : (char)(c + 1)).map(Object::toString);
    }

    private static Stream<String> finiteLettersStream(int n) {
        return inifiniteLettersStream().limit(n);
    }

    private static <T> List<List<T>> pairList(Stream<T> stream) {
        return null;
    }

    private static <T> List<List<T>> tupleList(Stream<T> stream, int size) {
        return null;
    }

    private static <T> List<List<T>> windowList(Stream<T> stream, int size, int step) {
        return null;
    }

    private static <T> Stream<List<T>> windowStream(Stream<T> stream, int size, int step) {
        return null;
    }

}
