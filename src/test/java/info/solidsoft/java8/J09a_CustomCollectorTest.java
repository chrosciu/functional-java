package info.solidsoft.java8;

import info.solidsoft.java8.payments.Payment;
import info.solidsoft.java8.payments.PaymentStatus;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static info.solidsoft.java8.payments.PaymentStatus.CANCELLED;
import static info.solidsoft.java8.payments.PaymentStatus.FAILED;
import static info.solidsoft.java8.payments.PaymentStatus.STARTED;
import static info.solidsoft.java8.payments.PaymentStatus.SUCCEEDED;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Implement custom Collector building map of the highest amount payments of given statuses
 *
 * Hint: Start from sequential version, add parallel one later.
 *
 */
public class J09a_CustomCollectorTest {

    @Test
    public void shouldCollectSinglePaymentOfRequestedTypes() {
        Stream<Payment> payments = Stream.of(
                new Payment(200, STARTED),
                new Payment(100, STARTED),
                new Payment(400, SUCCEEDED),
                new Payment(300, SUCCEEDED),
                new Payment(500, SUCCEEDED),
                new Payment(600, FAILED),
                new Payment(700, FAILED),
                new Payment(800, CANCELLED));
        Map<PaymentStatus, Payment> singlePaymentOfType = payments
                .collect(null);  //.collect(new PaymentsCollector(STARTED, SUCCEEDED))

        assertThat(singlePaymentOfType)
                .containsOnly(
                        Map.entry(STARTED, new Payment(200, STARTED)),
                        Map.entry(SUCCEEDED, new Payment(500, SUCCEEDED))
                );
    }

    @Test
    public void shouldWorkInParallel() {
        Map<PaymentStatus, Payment> sequential = payments(1000)
                .collect(null); //.collect(new PaymentsCollector(STARTED, SUCCEEDED))
        Map<PaymentStatus, Payment> parallel = payments(1000)
                .parallel()
                .collect(null); //.collect(new PaymentsCollector(STARTED, SUCCEEDED))

        assertThat(parallel).isEqualTo(sequential);
    }

    private Stream<Payment> payments(int n) {
        return IntStream.range(0, n).mapToObj(this::toPayment);
    }

    private Payment toPayment(int index) {
        return new Payment(index, PaymentStatus.values()[index % PaymentStatus.values().length]);
    }

}
