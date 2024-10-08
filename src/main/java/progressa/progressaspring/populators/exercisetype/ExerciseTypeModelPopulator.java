package progressa.progressaspring.populators.exercisetype;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import progressa.progressaspring.models.ExerciseTypeModel;
import progressa.progressaspring.populators.BasePopulator;

/**
 * @author danielfpc11@gmail.com
 */
@Component
public class ExerciseTypeModelPopulator extends BasePopulator<ExerciseTypeModel, ExerciseTypeModel> {

    @Override
    public void populate(final ExerciseTypeModel exerciseTypeModelSource, final ExerciseTypeModel exerciseTypeModelTarget) {
        Assert.notNull(exerciseTypeModelSource, SOURCE_NOT_NULL_MESSAGE);
        Assert.notNull(exerciseTypeModelTarget, TARGET_NOT_NULL_MESSAGE);

        exerciseTypeModelTarget.setName(exerciseTypeModelSource.getName());
        exerciseTypeModelTarget.setExerciseModels(exerciseTypeModelSource.getExerciseModels());
    }

}