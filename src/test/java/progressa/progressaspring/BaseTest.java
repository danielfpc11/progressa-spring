package progressa.progressaspring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

/**
 * @author danielfpc11@gmail.com
 */
public abstract class BaseTest {

    /**
     * Assert that a specific exception is thrown during the execution of a given executable
     * and that the exception message matches the expected message.
     *
     * @param exceptionClass The class of the expected exception to be thrown.
     * @param message        The expected message of the exception that should be thrown.
     * @param executable     The executable code block expected to throw the exception.
     */
    protected void assertException(final Class<? extends Throwable> exceptionClass,
                                   final String message,
                                   final Executable executable) {
        final Throwable throwableException = Assertions.assertThrows(exceptionClass, executable);
        Assertions.assertEquals(message, throwableException.getMessage());
    }

}
