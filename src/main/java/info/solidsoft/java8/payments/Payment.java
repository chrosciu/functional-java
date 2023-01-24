package info.solidsoft.java8.payments;

import java.util.Objects;

public class Payment {
    private final int amount;
    private final PaymentStatus status;

    public Payment(int amount, PaymentStatus status) {
        this.amount = amount;
        this.status = status;
    }

    public int getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return amount == payment.amount && status == payment.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, status);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "amount=" + amount +
                ", status=" + status +
                '}';
    }
}
