package info.solidsoft.java8;

import info.solidsoft.java8.window.WindowCollector;
import info.solidsoft.java8.window.WindowSpliterator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class J09c_WindowTest {

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

    private static <T> List<List<T>> windowList(Stream<T> stream, int size, int step) {
        //return windowStream(stream, size, step).collect(Collectors.toList());

        WindowCollector<T> collector = new WindowCollector<>(size, step);
        return stream.collect(collector);
    }

    private static <T> Stream<List<T>> windowStream(Stream<T> stream, int size, int step) {
        Spliterator<T> spliterator = stream.spliterator();
        Spliterator<List<T>> wrapper = new WindowSpliterator<>(spliterator, size, step);
        return StreamSupport.stream(wrapper, false);
    }

}
