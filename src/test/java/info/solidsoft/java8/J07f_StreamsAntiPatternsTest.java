package info.solidsoft.java8;

import info.solidsoft.java8.people.Person;
import info.solidsoft.java8.people.Sex;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class J07f_StreamsAntiPatternsTest {

    private static final Person MARCIN = new Person("Marcin", Sex.MALE, 105, 180,
            LocalDate.of(1982, 7, 30));
    private static final Person MAJA = new Person("Maja", Sex.FEMALE, 8, 30,
            LocalDate.of(2014, 11, 19));

    @Test
    public void shouldNotBeConsumedTwice() {
        //given
        Stream<Person> people = Stream.of(MARCIN, MAJA);
        AtomicBoolean filtered = new AtomicBoolean(false);

        //when
        Stream<Person> men = people.filter(p -> {
            filtered.set(true);
            return Sex.MALE == p.getSex();
        });

        //then
        assertThat(filtered).isFalse();
        //and
        assertThatExceptionOfType(IllegalStateException.class).isThrownBy(
                () -> people.filter(p -> Sex.FEMALE == p.getSex()))
                .withMessageContaining("stream has already been operated upon or closed");
    }

    @Test
    public void shouldNotBeLeftUnconsumed() {
        //given
        List<String> savedPeople = new ArrayList<>();
        Stream<Person> people = Stream.of(MARCIN, MAJA);

        //when
        Stream<Person> saved = people.peek(person -> {
            savedPeople.add(person.getName());
        });

        //then
        assertThat(savedPeople).isEmpty();
    }

    @Test
    public void shouldReflectChangingInBackingCollection() {
        //given
        List<Person> peopleList = new ArrayList<>();
        peopleList.add(MARCIN);
        Stream<Person> people = peopleList.stream();

        //when
        peopleList.add(MAJA);

        //then
        assertThat(people).contains(MARCIN, MAJA);
    }

}
