package info.solidsoft.java8.payments;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class PaymentsCollector implements Collector<Payment, Map<PaymentStatus, Payment>, Map<PaymentStatus, Payment>> {
    private final EnumSet<PaymentStatus> statuses;

    public PaymentsCollector(PaymentStatus... statuses) {
        this.statuses = EnumSet.of(statuses[0], statuses);
    }

    @Override
    public Supplier<Map<PaymentStatus, Payment>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<PaymentStatus, Payment>, Payment> accumulator() {
        return (map, payment) -> {
            PaymentStatus status = payment.getStatus();
            if (statuses.contains(status)) {
                map.compute(status,
                        (__, stored) -> stored != null && stored.getAmount() >= payment.getAmount() ? stored : payment);
            }
        };
    }

    @Override
    public BinaryOperator<Map<PaymentStatus, Payment>> combiner() {
        return (map1, map2) -> {
            map2.forEach((status, payment) -> map1.merge(status, payment,
                    (payment1, payment2) -> payment1.getAmount() >= payment2.getAmount() ? payment1 : payment2));
            return map1;
        };
    }

    @Override
    public Function<Map<PaymentStatus, Payment>, Map<PaymentStatus, Payment>> finisher() {
        return null;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Characteristics.IDENTITY_FINISH);
    }
}
