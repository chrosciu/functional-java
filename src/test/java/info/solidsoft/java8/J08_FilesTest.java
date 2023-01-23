package info.solidsoft.java8;

import info.solidsoft.java8.people.Person;
import info.solidsoft.java8.people.PersonDao;
import info.solidsoft.java8.people.Sex;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * - BufferedReader.lines()
 * - Comparator improvements
 */
public class J08_FilesTest {

    private final PersonDao dao = new PersonDao();

    @Test
    public void shouldLoadAllPeople() {
        final List<Person> people = dao.loadPeopleDatabase();

        assertThat(people).hasSize(137);
    }

    @Test
    public void shouldSortNamesAlphabeticallyRemovingDuplicates() {
        final List<Person> people = dao.loadPeopleDatabase();

        //Hint: Modify stream processing to get names sorted - does operation order matter?
        final List<String> names = people.stream()
                .map(Person::getName)
                //.sorted(Comparator.naturalOrder()) //also works but is less efficient
                .distinct()
                .sorted(Comparator.naturalOrder())
                .collect(toList());

        assertThat(names).startsWith("Aleksandar", "Alexander", "Alexandra", "Ali", "Alice");
    }

    /**
     * Hint: Comparator.comparing()
     */
    @Test
    public void shouldSortFemalesByHeightDescending() {
        final List<Person> people = dao.loadPeopleDatabase();

        final List<String> names = people.stream()
                .filter(person -> Sex.FEMALE == person.getSex())
                .sorted(Comparator.comparingInt(Person::getHeight).reversed())
                .map(Person::getName)
                .collect(toList());

        assertThat(names).startsWith("Mia", "Sevinj", "Anna", "Sofia");
    }

    @Test
    public void shouldSortByDateOfBirthWhenSameNames() {
        final List<Person> people = dao.loadPeopleDatabase();

        final List<String> names = people.stream()
                .sorted(Comparator.comparing(Person::getName).thenComparing(Person::getDateOfBirth))
                .map(p -> p.getName() + '-' + p.getDateOfBirth().getYear())
                .collect(toList());

        assertThat(names).startsWith("Aleksandar-1966", "Alexander-1986", "Alexander-1987", "Alexandra-1988", "Ali-1974");
    }

    /**
     * @see Files#list(Path)
     * <p>
     * Note: Do NOT use Files.walk(). Do it yourself with Files.list().
     * <p>
     * Challenging
     */
    @Test
    public void shouldGenerateStreamOfAllFilesIncludingSubdirectoriesRecursively() {
        //given
        final String fileToSearch = J08_FilesTest.class.getSimpleName() + ".java";

        //when
        final Optional<Path> found = filesInDir(Paths.get("."))
                .filter(path -> path.endsWith(fileToSearch))
                .findAny();

        //then
        assertThat(found).isPresent();
    }

    private static Stream<Path> filesInDir(Path dir) {
        try {
            return Files.list(dir).flatMap(path -> Files.isDirectory(path) ? filesInDir(path) : Stream.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
