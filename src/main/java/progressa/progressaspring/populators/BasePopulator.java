package progressa.progressaspring.populators;

/**
 * @author danielfpc11@gmail.com
 * TODO: javadoc
 */
public abstract class BasePopulator<S, T> {

    protected static final String SOURCE_NOT_NULL_MESSAGE = "Source must not be null.";
    protected static final String TARGET_NOT_NULL_MESSAGE = "Target must not be null.";

    public abstract void populate(S source, T target);

}
