package info.solidsoft.java8;

import info.solidsoft.java8.interfaces.Encrypter;
import info.solidsoft.java8.interfaces.ReverseEncrypter;
import info.solidsoft.java8.interfaces.RotEncrypter;
import info.solidsoft.java8.interfaces.XorEncrypter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * - Improve encrypter to use default methods to avoid code duplication.
 */
public class J02_InterfacesTest {

    @ParameterizedTest
    @MethodSource
    public void testAllMethods(Encrypter encrypter, byte[] expected) throws IOException {
        final byte[] input = new byte[]{90, 100, 110};
        final char[] charInput = new char[]{'Z', 'd', 'n'};

        assertThat(encrypter.encode(input)).isEqualTo(expected);
        assertThat(encrypter.encode(new String(input), UTF_8)).isEqualTo(expected);
        assertThat(encrypter.encode(charInput, UTF_8)).isEqualTo(expected);
        assertThat(encrypter.encode(new StringReader(new String(charInput)), UTF_8)).isEqualTo(expected);
        assertThat(encrypter.encode(new ByteArrayInputStream(input))).isEqualTo(expected);
    }

    private static Stream<Arguments> testAllMethods() {
        return Stream.of(
                Arguments.of(new ReverseEncrypter(), new byte[]{-91, -101, -111}),
                Arguments.of(new RotEncrypter(), new byte[]{103, 113, 123}),
                Arguments.of(new XorEncrypter(), new byte[]{-106, -88, -94})
        );
    }
}
