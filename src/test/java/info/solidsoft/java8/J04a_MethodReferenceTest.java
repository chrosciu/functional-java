package info.solidsoft.java8;

import info.solidsoft.java8.listener.MessageListener;
import info.solidsoft.java8.listener.MessageSource;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class J04a_MethodReferenceTest {

    private static final Logger log = LoggerFactory.getLogger(J04a_MethodReferenceTest.class);

    private boolean notified = false;

    @Test
    public void removedListenerShouldNotBeCalled() {
        //given
        MessageSource messageSource = new MessageSource();
        messageSource.addListener(this::notify);

        //when
        messageSource.emit("First message");

        //then
        assertThat(notified).isTrue();

        //given
        messageSource.removeListener(this::notify);
        notified = false;

        //when
        messageSource.emit("Second message");

        //then
        assertThat(notified).isTrue();
    }

    @Test
    public void methodReferencesShouldNotBeEqual() {
        //given
        MessageListener listener1 = this::notify;
        MessageListener listener2 = this::notify;

        //then
        assertThat(listener1).isNotEqualTo(listener2);
    }

    private void notify(String message) {
        notified = true;
        log.info("Received: " + message);
    }

}
