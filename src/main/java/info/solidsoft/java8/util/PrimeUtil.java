package info.solidsoft.java8.util;

import java.util.function.LongUnaryOperator;
import java.util.stream.LongStream;

public class PrimeUtil {

    /**
     * TODO: Try to implement this without loops and if's
     *
     * @see LongStream#iterate(long, LongUnaryOperator)
     */
    @SuppressWarnings({"OptionalGetWithoutIsPresent"})  //There has to some prime number sooner or later
    public static long nextPrimeAfter(long x) {
        return LongStream.iterate(x + 1, n -> n + 1).filter(PrimeUtil::isPrime).findFirst().getAsLong();
    }

    /**
     * TODO: Try to implement this without loops and if's
     *
     * @see LongStream#range(long, long)
     */
    public static boolean isPrime(long x) {
        return LongStream.range(2, x).noneMatch(n -> x % n == 0);
    }
}
