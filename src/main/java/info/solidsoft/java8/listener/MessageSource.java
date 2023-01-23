package info.solidsoft.java8.listener;

import java.util.HashSet;
import java.util.Set;

public class MessageSource {
    private Set<MessageListener> listeners = new HashSet<>();

    public void addListener(MessageListener listener) {
        listeners.add(listener);
    }

    public void removeListener(MessageListener listener) {
        listeners.remove(listener);
    }

    public void emit(String message) {
        listeners.forEach(messageListener -> messageListener.notify(message));
    }

}
