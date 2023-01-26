package info.solidsoft.java8.scoring;

import org.javamoney.moneta.Money;

import java.time.Period;

public class Loan {
    private final LoanType loanType;
    private final Money amount;
    private final Period period;

    public Loan(LoanType loanType, Money amount, Period period) {
        this.loanType = loanType;
        this.amount = amount;
        this.period = period;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public Money getAmount() {
        return amount;
    }

    public Period getPeriod() {
        return period;
    }
}
