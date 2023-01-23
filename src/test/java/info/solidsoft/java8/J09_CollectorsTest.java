package info.solidsoft.java8;

import info.solidsoft.java8.people.Person;
import info.solidsoft.java8.people.PersonDao;
import org.junit.jupiter.api.Test;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.offset;

/**
 * Explore different Collectors.* implementations
 * - Various collectors, grouping, average, toList, etc.
 */
public class J09_CollectorsTest {

    private final PersonDao dao = new PersonDao();

    @Test
    public void calculateAverageHeight() {
        final List<Person> people = dao.loadPeopleDatabase();

        final Double averageHeight = people.stream().collect(Collectors.averagingDouble(Person::getHeight));

        assertThat(averageHeight).isEqualTo(174, offset(0.5));
    }

    @Test
    public void partitionByPeopleAboveAndBelowOrEqual180CmHeight() {    //One group: above, second: below or equal 180
        final List<Person> people = dao.loadPeopleDatabase();

        final Map<Boolean, List<Person>> peopleByHeight = people.stream()
                .collect(Collectors.partitioningBy(person -> person.getHeight() > 180));

        final List<Person> tallPeople = peopleByHeight.get(true);
        assertThat(tallPeople).hasSize(33);

        final List<Person> shortPeople = peopleByHeight.get(false);
        assertThat(shortPeople).hasSize(104);
    }

    @Test
    public void groupPeopleByWeight() {
        final List<Person> people = dao.loadPeopleDatabase();

        final Map<Integer, List<Person>> peopleByWeight = people.stream()
                .collect(Collectors.groupingBy(Person::getWeight));

        assertThat(peopleByWeight.get(46)).hasSize(1);
        assertThat(peopleByWeight.get(70)).hasSize(2);
        assertThat(peopleByWeight.get(92)).hasSize(1);
    }

    @Test
    public void weightStatistics() {
        final List<Person> people = dao.loadPeopleDatabase();

        final IntSummaryStatistics stats = people.stream().collect(Collectors.summarizingInt(Person::getWeight));

        assertThat(stats.getCount()).isEqualTo(137);
        assertThat(stats.getMin()).isEqualTo(46);
        assertThat(stats.getMax()).isEqualTo(92);
        assertThat(stats.getSum()).isEqualTo(8998);
        assertThat(stats.getAverage()).isEqualTo(65.8, offset(0.5));
    }

}
