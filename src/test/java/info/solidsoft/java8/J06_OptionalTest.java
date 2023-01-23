package info.solidsoft.java8;

import info.solidsoft.java8.people.Person;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static info.solidsoft.java8.people.Sex.MALE;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Transform old-fashioned code using nulls with Optional
 * Hint: use map/filter/flatMap/ifPresent
 */
public class J06_OptionalTest {

    private static final int PERSON_ID_WITH_NO_ADDRESS = 1;
    private static final int PERSON_ID_WITH_ADDRESS = 2;
    private static final int UNAVAILABLE_PERSON_ID = 0;

    private Person findPersonOrNull(int id) {
        switch (id) {
            case PERSON_ID_WITH_NO_ADDRESS:
                return new Person("James", MALE, 62, 169, LocalDate.of(2007, Month.DECEMBER, 21));
            case PERSON_ID_WITH_ADDRESS:
                return new Person("John", MALE, 62, 169, LocalDate.of(1985, Month.DECEMBER, 21));
            case UNAVAILABLE_PERSON_ID:
                return null;
            default:
                return null;
        }
    }

    private String lookupAddressOrNull(Person person) {
        if (person.getDateOfBirth().isAfter(LocalDate.of(2000, Month.JANUARY, 1))) {
            return "";
        }
        if (person.getDateOfBirth().isAfter(LocalDate.of(1980, Month.JANUARY, 1))) {
            return " Some St.   ";
        }
        return null;
    }

    /**
     * Don't change, just copy to tryLookupAddressById and refactor.
     */
    private String lookupAddressByIdOrNull(int id) {
        final Person personOrNull = findPersonOrNull(id);
        if (personOrNull != null) {
            if (personOrNull.getSex() == MALE) {
                final String addressOrNull = lookupAddressOrNull(personOrNull);
                if (addressOrNull != null && !addressOrNull.isEmpty()) {
                    return addressOrNull.trim();
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    /**
     * Don't change, call from {@link #tryLookupAddressById(int)}
     */
    private Optional<Person> tryFindPerson(int id) {
        return Optional.ofNullable(findPersonOrNull(id));
    }

    /**
     * Don't change, call from {@link #tryLookupAddressById(int)}
     */
    private Optional<String> tryLookupAddress(Person person) {
        return Optional.ofNullable(lookupAddressOrNull(person));
    }

    /**
     * TODO: Copy and refactor code from {@link #lookupAddressByIdOrNull}, but avoid nulls
     * <p>
     * Hint: use map/filter/flatMap/ifPresent methods from Optional class.
     */
    private Optional<String> tryLookupAddressById(int id) {
        return tryFindPerson(id)
                .filter(p -> p.getSex() == MALE)
                .flatMap(this::tryLookupAddress)
                .filter(a -> !a.isEmpty())
                .map(String::trim);
    }

    @Test
    public void nulls() {
        assertThat(lookupAddressByIdOrNull(UNAVAILABLE_PERSON_ID)).isNull();
        assertThat(lookupAddressByIdOrNull(PERSON_ID_WITH_NO_ADDRESS)).isNull();
        assertThat(lookupAddressByIdOrNull(PERSON_ID_WITH_ADDRESS)).isEqualTo("Some St.");
    }

    @Test
    public void optionals() {
        assertThat(tryLookupAddressById(UNAVAILABLE_PERSON_ID).isPresent()).isFalse();
        assertThat(tryLookupAddressById(PERSON_ID_WITH_NO_ADDRESS).isPresent()).isFalse();
        assertThat(tryLookupAddressById(PERSON_ID_WITH_ADDRESS).get()).isEqualTo("Some St.");
    }

}
