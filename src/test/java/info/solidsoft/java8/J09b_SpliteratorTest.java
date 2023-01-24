package info.solidsoft.java8;

import info.solidsoft.java8.people.Person;
import info.solidsoft.java8.people.PersonDao;
import info.solidsoft.java8.people.Sex;
import info.solidsoft.java8.util.FilesSpliterator;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Spliterators.spliteratorUnknownSize;
import static org.assertj.core.api.Assertions.assertThat;

public class J09b_SpliteratorTest {

    @Test
    public void shouldCalculateTotalMenWeightFromIterator() {
        PersonDao personDao = new PersonDao();
        Iterator<Person> peopleIterator = personDao.getPeopleIterator();

        int totalWeight = StreamSupport.stream(spliteratorUnknownSize(peopleIterator, 0), false)
                .filter(p -> Sex.MALE == p.getSex())
                .mapToInt(Person::getWeight)
                .sum();

        assertThat(totalWeight).isEqualTo(4777);
    }

    /**
     * Implement Spliterator emitting all files in directory (and its subdirectories)
     * Similar to J08_FilesTest
     */
    @Test
    public void shouldGenerateStreamOfAllFilesIncludingSubdirectoriesRecursivelyWithSpliterator() {
        //given
        final String fileToSearch = J09b_SpliteratorTest.class.getSimpleName() + ".java";

        //when
        final Optional<Path> found = filesInDir(Paths.get("."))
                .filter(path -> path.endsWith(fileToSearch))
                .findAny();

        //then
        assertThat(found).isPresent();
    }

    private static Stream<Path> filesInDir(Path dir) {
        return StreamSupport.stream(new FilesSpliterator(dir), false);
    }

}
