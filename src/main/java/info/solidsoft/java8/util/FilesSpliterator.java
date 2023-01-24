package info.solidsoft.java8.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Spliterator;
import java.util.function.Consumer;

public class FilesSpliterator implements Spliterator<Path> {
    private final Queue<Path> pathsToExplore = new LinkedList<>();
    private final Queue<Path> pathsToReport = new LinkedList<>();

    public FilesSpliterator(Path rootPath) {
        if (Files.isDirectory(rootPath)) {
            pathsToExplore.add(rootPath);
        }
    }

    @Override
    public boolean tryAdvance(Consumer<? super Path> action) {
        for (; ; ) {
            Path pathToReport = pathsToReport.poll();
            if (pathToReport != null) {
                action.accept(pathToReport);
                return true;
            }
            Path pathToExplore = pathsToExplore.poll();
            if (null == pathToExplore) {
                return false;
            }
            try {
                Files.list(pathToExplore).forEach(path -> {
                    if (Files.isDirectory(path)) {
                        pathsToExplore.add(path);
                    } else {
                        pathsToReport.add(path);
                    }
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Spliterator<Path> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return Long.MAX_VALUE;
    }

    @Override
    public int characteristics() {
        return 0;
    }
}
