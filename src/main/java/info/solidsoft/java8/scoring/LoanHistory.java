package info.solidsoft.java8.scoring;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class LoanHistory {
    int amount;
    LocalDate endDate;
}
