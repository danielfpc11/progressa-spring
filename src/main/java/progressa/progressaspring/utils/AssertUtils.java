package progressa.progressaspring.utils;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.Assert;

/**
 * @author danielfpc11@gmail.com
 */
public class AssertUtils {

    private static final String ID_NOT_NULL_MESSAGE = "Id must not be null.";
    private static final String ID_POSITIVE_MESSAGE = "Id must be greater than zero.";
    private static final String OBJECT_NOT_NULL_MESSAGE = "%s must not be null.";

    /**
     * Validates that the provided id is not null and is a positive number.
     *
     * @param id the id to validate, must not be null and must be greater than zero.
     * @throws IllegalArgumentException if the id is null or not positive.
     */
    public static void idNotNullAndPositive(final Long id) throws IllegalArgumentException {
        Assert.notNull(id, ID_NOT_NULL_MESSAGE);
        Assert.isTrue(id > NumberUtils.LONG_ZERO, ID_POSITIVE_MESSAGE);
    }

    /**
     * Validates that the provided object is not null.
     *
     * @param object the object to validate, must not be null.
     * @param objectClass the class type of the object, used for error message formatting.
     * @throws IllegalArgumentException if the object is null.
     */
    public static void notNull(final Object object, final Class<?> objectClass) throws IllegalArgumentException {
        Assert.notNull(object, String.format(OBJECT_NOT_NULL_MESSAGE, objectClass.getSimpleName()));
    }

}
