package progressa.progressaspring.populators.workout;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import progressa.progressaspring.models.WorkoutModel;
import progressa.progressaspring.populators.BasePopulator;

/**
 * @author danielfpc11@gmail.com
 */
@Component
public class WorkoutModelPopulator extends BasePopulator<WorkoutModel, WorkoutModel> {

    @Override
    public void populate(final WorkoutModel workoutModelSource, final WorkoutModel workoutModelTarget) {
        Assert.notNull(workoutModelSource, SOURCE_NOT_NULL_MESSAGE);
        Assert.notNull(workoutModelTarget, TARGET_NOT_NULL_MESSAGE);

        workoutModelTarget.setDate(workoutModelSource.getDate());
        workoutModelTarget.setExerciseModels(workoutModelSource.getExerciseModels());

    }

}
