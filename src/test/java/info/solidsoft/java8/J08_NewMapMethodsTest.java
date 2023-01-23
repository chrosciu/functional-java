package info.solidsoft.java8;


import info.solidsoft.java8.util.LoremIpsum;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class J08_NewMapMethodsTest {

    @Test
    public void shouldReturnWordCount() throws Exception {
        //given
        final String loremIpsum = LoremIpsum.text();

        //when
        Map<String, Integer> wordCount = LoremIpsum.wordCount(loremIpsum);

        //then
        assertThat(wordCount)
                .hasSize(140)
                .contains(
                        entry("eget", 12),
                        entry("sit", 9),
                        entry("amet", 9),
                        entry("et", 8)
                );

        assertThat(wordCount.getOrDefault("kot", 0)).isZero();
    }

    @Test
    public void shouldReturnTotalWords() throws Exception {
        //given
        final String loremIpsum = LoremIpsum.text();
        Map<String, Integer> wordCount = LoremIpsum.wordCount(loremIpsum);

        //when
        int totalWords = wordCount.values().stream().mapToInt(v -> v).sum();

        //then
        assertThat(totalWords).isEqualTo(441);
    }

    @Test
    public void shouldReturnFiveMostCommonWords() throws Exception {
        final String loremIpsum = LoremIpsum.text();
        Map<String, Integer> wordCount = LoremIpsum.wordCount(loremIpsum);

        //when
        final Set<String> fiveMostCommon = wordCount.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> -entry.getValue()))
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        //then
        assertThat(fiveMostCommon).containsOnly("eget", "sit", "amet", "et", "sed");
    }

    @Test
    public void shouldReturnWordsThatOccurOnlyOnce() throws Exception {
        final String loremIpsum = LoremIpsum.text();
        Map<String, Integer> wordCount = LoremIpsum.wordCount(loremIpsum);

        //when
        final Set<String> uniqueWords = wordCount.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        //then
        assertThat(uniqueWords)
                .hasSize(37)
                .contains("tempor", "laoreet", "netus");
    }

}
