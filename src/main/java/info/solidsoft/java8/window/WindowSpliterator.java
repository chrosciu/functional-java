package info.solidsoft.java8.window;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/* https://stackoverflow.com/a/20477627/2274709 */
public class WindowSpliterator<T> implements Spliterator<List<T>> {

    private final Spliterator<T> wrappedSpliterator;

    private final int size;

    private final int step;

    private final Deque<T> deque;

    private final Consumer<T> dequeConsumer;

    public WindowSpliterator(Spliterator<T> wrappedSpliterator, int size, int step) {
        this.wrappedSpliterator = wrappedSpliterator;
        this.size = size;
        this.step = step;
        this.deque = new ArrayDeque<>();
        this.dequeConsumer = deque::addLast;
    }

    @Override
    public boolean tryAdvance(Consumer<? super List<T>> action) {
        for (int i = 0; i < step && deque.pollFirst() != null; ++i) {}
        fillDeque();
        if (deque.size() == size) {
            List<T> list = new ArrayList<>(deque);
            action.accept(list);
            return true;
        } else {
            return false;
        }
    }

    private void fillDeque() {
        while (deque.size() < size && wrappedSpliterator.tryAdvance(dequeConsumer)) {}
    }

    @Override
    public Spliterator<List<T>> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return wrappedSpliterator.estimateSize();
    }

    @Override
    public int characteristics() {
        return wrappedSpliterator.characteristics();
    }
}

