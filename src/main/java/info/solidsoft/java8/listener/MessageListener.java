package info.solidsoft.java8.listener;

@FunctionalInterface
public interface MessageListener {
    void notify(String message);
}
