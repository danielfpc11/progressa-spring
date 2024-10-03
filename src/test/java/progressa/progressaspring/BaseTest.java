package progressa.progressaspring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

/**
 * @author danielfpc11@gmail.com
 */
public abstract class BaseTest {

    protected void assertException(final Class<? extends Throwable> exceptionClass,
                                   final String message,
                                   final Executable executable) {
        final Throwable throwableException = Assertions.assertThrows(exceptionClass, executable);
        Assertions.assertEquals(message, throwableException.getMessage());
    }

}
