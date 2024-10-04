package progressa.progressaspring.populators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;
import progressa.progressaspring.BaseTest;

/**
 * @author danielfpc11@gmail.com
 */
public class BasePopulatorTest extends BaseTest {

    protected static final String SOURCE_NOT_NULL_MESSAGE = "Source must not be null.";
    protected static final String TARGET_NOT_NULL_MESSAGE = "Target must not be null.";

    /**
     * Assert that an {@link IllegalArgumentException} is thrown during the execution of a given populate method
     * executable and that source is null.
     *
     * @param executable The populate method executable to throw the {@link IllegalArgumentException}.
     */
    protected void assertSourceNull(final Executable executable) {
        assertException(IllegalArgumentException.class, SOURCE_NOT_NULL_MESSAGE, executable);
    }

    /**
     * Assert that an {@link IllegalArgumentException} is thrown during the execution of a given populate method
     * executable because target is null.
     *
     * @param executable The populate method executable to throw the {@link IllegalArgumentException}.
     */
    protected void assertTargetNull(final Executable executable) {
        assertException(IllegalArgumentException.class, TARGET_NOT_NULL_MESSAGE, executable);
    }

}
