package progressa.progressaspring.populators.exercise;

import org.springframework.util.Assert;
import progressa.progressaspring.models.ExerciseModel;
import progressa.progressaspring.populators.BasePopulator;

/**
 * @author danielfpc11@gmail.com
 */
public class ExerciseModelPopulator extends BasePopulator<ExerciseModel, ExerciseModel> {

    @Override
    public void populate(final ExerciseModel exerciseModelSource, final ExerciseModel exerciseModelTarget) {
        Assert.notNull(exerciseModelSource, SOURCE_NOT_NULL_MESSAGE);
        Assert.notNull(exerciseModelTarget, TARGET_NOT_NULL_MESSAGE);

        exerciseModelTarget.setWorkoutModel(exerciseModelSource.getWorkoutModel());
        exerciseModelTarget.setExerciseTypeModel(exerciseModelSource.getExerciseTypeModel());
        exerciseModelTarget.setSetModels(exerciseModelSource.getSetModels());
    }

}
