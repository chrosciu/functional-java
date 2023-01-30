package info.solidsoft.java8.scoring;

import lombok.Value;

@Value
public class Scoring {
    Person person;
    Loan loan;
    int score;
}
