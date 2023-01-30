package info.solidsoft.java8.util;

import com.google.common.base.Splitter;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.google.common.base.CharMatcher.anyOf;

/**
 * Assumption. Words should be compared ignoring case
 *
 * @see Map#merge(Object, Object, BiFunction) (can be used with simple "for" loop)
 * @see Collectors#groupingBy(Function)
 * @see Map#compute(Object, BiFunction)
 */
public class LoremIpsum {

    public static String text() throws IOException {
        return IOUtils.toString(LoremIpsum.class.getResourceAsStream("/lorem-ipsum.txt"));
    }

    public static Map<String, Integer> wordCount(String text) {
        return splitWords(text).stream()
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.summingInt(e -> 1)));
    }

    public static List<String> splitWords(String text) {
        return Splitter
                .on(anyOf(" .,\n"))
                .trimResults()
                .omitEmptyStrings()
                .splitToList(text);
    }

}
