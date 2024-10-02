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


    public static void idNotNullAndPositive(final Long id) throws IllegalArgumentException {
        Assert.notNull(id, ID_NOT_NULL_MESSAGE);
        Assert.isTrue(id > NumberUtils.LONG_ZERO, ID_POSITIVE_MESSAGE);
    }

    public static void notNull(final Object object, final Class<?> objectClass) throws IllegalArgumentException {
        Assert.notNull(object, String.format(OBJECT_NOT_NULL_MESSAGE, objectClass));
    }

}
