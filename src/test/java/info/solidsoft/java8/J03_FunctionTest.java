package info.solidsoft.java8;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.LongConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.offset;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

/**
 * - Use explicit Function, Predicate, Supplier, Consumer (like Guava)
 * - Higher order functions - returning lambdas
 * - Change Encrypter to class taking Function<Byte, Byte>
 * - Turning Function, Supplier and Producer into lambda
 * - Method references (method, static method, constructor)
 */
public class J03_FunctionTest {

    @Test
    public void shouldPrependHello() {
        final Function<Integer, String> fun = x -> "Answer is " + x;

        assertThat(fun.apply(42)).isEqualTo("Answer is 42");
    }

    @Test
    public void shouldProduceAnswer() {
        final Supplier<Integer> answerFun = () -> 42;

        assertThat(answerFun.get()).isEqualTo(42);
    }

    @Test
    public void shouldDecideIfNegative() {
        final Predicate<Double> isNegative = x -> x < 0;

        assertThat(isNegative.test(3.0)).isFalse();
        assertThat(isNegative.test(0.0)).isFalse();
        assertThat(isNegative.test(-1.1)).isTrue();
    }

    @Test
    public void shouldCallOtherClassInConsumer() {
        final Date dateMock = mock(Date.class);

        final Consumer<Long> consumer = dateMock::setTime;

        consumer.accept(1000L);
        consumer.accept(2000L);

        final InOrder order = inOrder(dateMock);
        order.verify(dateMock).setTime(1000L);
        order.verify(dateMock).setTime(2000L);
    }

    @Test
    public void shouldCallOtherClassInPrimitiveConsumer() {
        final Date dateMock = mock(Date.class);

        final LongConsumer consumer = dateMock::setTime;

        consumer.accept(1000L);
        consumer.accept(2000L);

        final InOrder order = inOrder(dateMock);
        order.verify(dateMock).setTime(1000L);
        order.verify(dateMock).setTime(2000L);
    }

    @Test
    public void shouldInvokeReturnedLambdas() {
        //given
        final Function<String, Integer> strLenFun = createStringLenFunction();
        final Function<Integer, Double> tripleFun = multiplyFun(3.0);
        final String input = "abcd";

        //when
        final int strLen = strLenFun.apply(input);
        final double tripled = tripleFun.apply(4);

        //then
        assertThat(strLen).isEqualTo(4);
        assertThat(tripled).isEqualTo(4 * 3.0, offset(0.01));
    }

    @Test
    public void shouldComposeFunctionsInVariousWays() {
        //given
        final Function<String, Integer> strLenFun = createStringLenFunction();
        final Function<Integer, Double> tripleFun = multiplyFun(3.0);
        final Function<String, Double> andThenFun = strLenFun.andThen(tripleFun);
        final Function<String, Double> composeFun = tripleFun.compose(strLenFun);
        final String input = "abcd";

        //when
        final double naiveResult = tripleFun.apply(strLenFun.apply(input));
        final double andThenResult = andThenFun.apply(input);
        final double composeResult = composeFun.apply(input);

        //then
        assertThat(naiveResult).isEqualTo(4 * 3.0, offset(0.01));
        assertThat(andThenResult).isEqualTo(4 * 3.0, offset(0.01));
        assertThat(composeResult).isEqualTo(4 * 3.0, offset(0.01));
    }

    private Function<Integer, Double> multiplyFun(double times) {
        return x -> x * times;
    }

    private Function<String, Integer> createStringLenFunction() {
        return String::length;
    }

}
