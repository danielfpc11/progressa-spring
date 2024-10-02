package progressa.progressaspring.utils;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * @author danielfpc11@gmail.com
 */
public class AssertUtilsTest {

    private static final String ID_NOT_NULL_MESSAGE = "Id must not be null.";
    private static final String ID_POSITIVE_MESSAGE = "Id must be greater than zero.";
    private static final String OBJECT_NOT_NULL_MESSAGE = "Object must not be null.";

    @Test
    public void idNotNullAndPositiveTest() {
        Assertions.assertDoesNotThrow(() -> AssertUtils.idNotNullAndPositive(NumberUtils.LONG_ONE));
    }

    @Test
    public void idNotNullAndPositiveIllegalArgumentExceptionTest() {
        assertIdNotNullAndPositiveIllegalArgumentException(null, ID_NOT_NULL_MESSAGE);
        assertIdNotNullAndPositiveIllegalArgumentException(NumberUtils.LONG_ZERO, ID_POSITIVE_MESSAGE);
        assertIdNotNullAndPositiveIllegalArgumentException(NumberUtils.LONG_MINUS_ONE, ID_POSITIVE_MESSAGE);
    }

    @Test
    public void notNullTest() {
        Assertions.assertDoesNotThrow(() -> AssertUtils.notNull(new Object(), Object.class));
    }

    @Test
    public void notNullIllegalArgumentExceptionTest() {
        assertIllegalArgumentException(OBJECT_NOT_NULL_MESSAGE, () -> AssertUtils.notNull(null, Object.class));
    }

    private void assertIdNotNullAndPositiveIllegalArgumentException(final Long id, final String message) {
        assertIllegalArgumentException(message, () -> AssertUtils.idNotNullAndPositive(id));
    }

    private void assertIllegalArgumentException(final String message, final Executable executable) {
        final IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, executable);
        Assertions.assertEquals(message, illegalArgumentException.getMessage());
    }

}
