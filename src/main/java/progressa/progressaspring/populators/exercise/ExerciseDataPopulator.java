package progressa.progressaspring.populators.exercise;

import org.springframework.util.Assert;
import progressa.progressaspring.datas.ExerciseData;
import progressa.progressaspring.populators.BasePopulator;

/**
 * @author danielfpc11@gmail.com
 */
public class ExerciseDataPopulator extends BasePopulator<ExerciseData, ExerciseData> {

    @Override
    public void populate(final ExerciseData exerciseDataSource, final ExerciseData exerciseDataTarget) {
        Assert.notNull(exerciseDataSource, SOURCE_NOT_NULL_MESSAGE);
        Assert.notNull(exerciseDataTarget, TARGET_NOT_NULL_MESSAGE);

        exerciseDataTarget.setWorkoutId(exerciseDataSource.getWorkoutId());
        exerciseDataTarget.setExerciseTypeId(exerciseDataSource.getExerciseTypeId());
        exerciseDataTarget.setExerciseTypeName(exerciseDataSource.getExerciseTypeName());
        exerciseDataTarget.setSetDatas(exerciseDataSource.getSetDatas());
    }

}
