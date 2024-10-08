package progressa.progressaspring.populators;

/**
 * @author danielfpc11@gmail.com
 */
public abstract class BasePopulator<S, T> {

    protected static final String SOURCE_NOT_NULL_MESSAGE = "Source must not be null.";
    protected static final String TARGET_NOT_NULL_MESSAGE = "Target must not be null.";

    /**
     * Populates the target object with data from the source object.
     *
     * @param source the source object containing the data.
     * @param target the target object to populate with data.
     */
    public abstract void populate(S source, T target);

}
