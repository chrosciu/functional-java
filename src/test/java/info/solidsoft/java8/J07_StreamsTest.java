package info.solidsoft.java8;

import info.solidsoft.java8.people.Person;
import info.solidsoft.java8.people.Phone;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static info.solidsoft.java8.people.Sex.FEMALE;
import static info.solidsoft.java8.people.Sex.MALE;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * - What is Stream<T>
 * - More complex operations on Stream, (map, filter, forEach, sorted)
 * - Only toList() Collector
 */
public class J07_StreamsTest {

    private static final List<Person> PEOPLE = Arrays.asList(
            new Person("Jane", FEMALE, 62, 169, LocalDate.of(1986, Month.DECEMBER, 21), new Phone(10, 555100200)),
            new Person("Bob", MALE, 71, 183, LocalDate.of(1982, Month.FEBRUARY, 5), new Phone(10, 555100201)),
            new Person("Steve", MALE, 85, 191, LocalDate.of(1980, Month.MAY, 4), new Phone(11, 555100200), new Phone(11, 555100201), new Phone(11, 555100202)),
            new Person("Alice", FEMALE, 54, 178, LocalDate.of(1984, Month.AUGUST, 17), new Phone(12, 555100202)),
            new Person("Eve", FEMALE, 61, 176, LocalDate.of(1987, Month.FEBRUARY, 9), new Phone(10, 555100200))
    );

    @Test
    public void doesAnyFemaleExist() {
        final boolean anyFemale = PEOPLE.stream()
                .anyMatch(p -> p.getSex() == FEMALE);

        assertThat(anyFemale).isTrue();
    }

    @Test
    public void shouldReturnNamesSorted() {
        final List<String> names = PEOPLE.stream()
                .map(Person::getName)
                .sorted()
                .collect(toList());

        assertThat(names).containsExactly("Alice", "Bob", "Eve", "Jane", "Steve");
    }

    /**
     * Are all people below 80 kg?
     */
    @Test
    public void areAllPeopleSlim() {
        final boolean allSlim = PEOPLE.stream()
                .allMatch(p -> p.getWeight() < 80);

        assertThat(allSlim).isFalse();
    }

    /**
     * Are all people above 80 kg?
     */
    @Test
    public void areAllPeopleNotSlim() {
        final boolean allNotSlim = PEOPLE.stream()
                .noneMatch(p -> p.getWeight() <= 80);

        assertThat(allNotSlim).isFalse();
    }

    @Test
    public void findTallestPerson() {
        final Optional<Person> max = PEOPLE.stream()
                        .max(Comparator.comparingInt(Person::getHeight));

        assertThat(max).hasValue(PEOPLE.get(2));
    }

    @Test
    public void countMales() {
        final long malesCount = PEOPLE.stream()
                .filter(p -> p.getSex() == MALE)
                .count();

        assertThat(malesCount).isEqualTo(2);
    }

    /**
     * Hint: use limit(2)
     */
    @Test
    public void twoOldestPeople() {
        final List<Person> oldest = PEOPLE.stream()
                .sorted(Comparator.comparing(Person::getDateOfBirth))
                .limit(2)
                .collect(toList());

        assertThat(oldest).containsExactly(PEOPLE.get(2), PEOPLE.get(1));
    }

    /**
     * Hint: PEOPLE.stream()...mapToInt()...sum()
     */
    @Test
    public void totalWeight() {
        final int totalWeight = PEOPLE.stream()
                .mapToInt(Person::getWeight)
                .sum();

        assertThat(totalWeight).isEqualTo(333);
    }

    /**
     * Hint: PEOPLE.stream()...flatMap()...distinct()
     * <p>
     * Challenging
     */
    @Test
    public void findUniqueCountryCodes() {
        final List<Integer> distinctCountryCodes = PEOPLE.stream()
                .flatMap(person -> person.getPhoneNumbers().stream())
                .map(Phone::getCountryCode)
                .distinct()
                .collect(toList());



        assertThat(distinctCountryCodes).containsExactly(10, 11, 12);
    }

    /**
     * For each person born after LocalDate.of(1985, Month.DECEMBER, 25), add name to 'names'
     */
    @Test
    public void forEachYoungPerson() {
        //given
        List<String> names = new ArrayList<>();

        //when
        PEOPLE.stream()
                .filter(p -> p.getDateOfBirth().isAfter(LocalDate.of(1985, Month.DECEMBER, 25)))
                .map(Person::getName)
                .forEach(names::add);

        //then
        assertThat(names).containsExactly("Jane", "Eve");
    }

    /**
     * @see Iterator#forEachRemaining(Consumer)
     */
    @Test
    public void shouldRunOverIterator() {
        //given
        final Iterator<Integer> iter = Arrays.asList(1, 2, 3).iterator();
        final StringBuilder sb = new StringBuilder();

        //when
        iter.forEachRemaining(sb::append);

        //then
        assertThat(sb.toString()).isEqualToIgnoringCase("123");
    }


}
