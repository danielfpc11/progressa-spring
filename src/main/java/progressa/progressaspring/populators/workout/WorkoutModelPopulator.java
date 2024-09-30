package progressa.progressaspring.populators.workout;

import org.springframework.util.Assert;
import progressa.progressaspring.populators.BasePopulator;
import progressa.progressaspring.models.WorkoutModel;

/**
 * @author danielfpc11@gmail.com
 */
public class WorkoutModelPopulator extends BasePopulator<WorkoutModel, WorkoutModel> {

    @Override
    public void populate(final WorkoutModel workoutModelSource, final WorkoutModel workoutModelTarget) {
        Assert.notNull(workoutModelSource, SOURCE_NOT_NULL_MESSAGE);
        Assert.notNull(workoutModelTarget, TARGET_NOT_NULL_MESSAGE);

        workoutModelTarget.setDate(workoutModelSource.getDate());
    }

}
