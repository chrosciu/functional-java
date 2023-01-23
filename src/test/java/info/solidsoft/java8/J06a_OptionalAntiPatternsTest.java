package info.solidsoft.java8;

import info.solidsoft.java8.people.Person;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import static info.solidsoft.java8.people.Sex.MALE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class J06a_OptionalAntiPatternsTest {

    private static final Person PERSON = new Person("James", MALE, 62, 169,
            LocalDate.of(2007, Month.DECEMBER, 21));
    private static final Optional<Person> MAYBE_PERSON = Optional.of(PERSON);

    @Test
    public void ifPresentShouldNotBeUsedWithGet() {
        //given
        int weight = 0;
        Optional<Person> person = MAYBE_PERSON; //from external source, API, DAO, etc.

        //when
        if (person.isPresent()) {
            weight = person.get().getWeight();
        } else {
            weight = -1;
        }

        //then
        assertThat(weight).isEqualTo(62);
    }

    @Test
    public void shouldNotBeUsedAsCollectionItem() {
        //given
        List<Optional<Person>> people = List.of(MAYBE_PERSON, Optional.empty());

        //when
        int totalHeight = people.stream()
                .mapToInt(o -> o.map(Person::getHeight).orElse(0))
                .sum();

        //then
        assertThat(totalHeight).isEqualTo(169);
    }

    @Test
    public void computedValueShouldNotBeUsedInOrElse() {
        //given
        Optional<Person> person = MAYBE_PERSON;
        AtomicBoolean computed = new AtomicBoolean(false);

        //when
        int weight = person.map(Person::getWeight).orElse(computeSubstituteWeight(computed));

        //then
        assertThat(weight).isEqualTo(62);
        //and
        assertThat(computed).isTrue();
    }

    @Test
    public void shouldNotBeAbused() {
        //given
        int weight = Optional.ofNullable(PERSON).map(Person::getWeight).orElse(-1);

        //then
        assertThat(weight).isEqualTo(62);
    }

    @Test
    public void cannotBeSerialized() throws IOException {
        //given
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);

        //then
        assertThatExceptionOfType(NotSerializableException.class).isThrownBy(() -> {
            objectStream.writeObject(MAYBE_PERSON);
        }).withMessageContaining("Optional");
    }

    private int computeSubstituteWeight(AtomicBoolean computed) {
        computed.set(true);
        return -1;
    }

}
