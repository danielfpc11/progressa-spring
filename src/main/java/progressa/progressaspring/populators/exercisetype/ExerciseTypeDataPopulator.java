package progressa.progressaspring.populators.exercisetype;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import progressa.progressaspring.datas.ExerciseTypeData;
import progressa.progressaspring.populators.BasePopulator;

/**
 * @author danielfpc11@gmail.com
 */
@Component
public class ExerciseTypeDataPopulator extends BasePopulator<ExerciseTypeData, ExerciseTypeData> {

    @Override
    public void populate(final ExerciseTypeData exerciseTypeDataSource, final ExerciseTypeData exerciseTypeDataTarget) {
        Assert.notNull(exerciseTypeDataSource, SOURCE_NOT_NULL_MESSAGE);
        Assert.notNull(exerciseTypeDataTarget, TARGET_NOT_NULL_MESSAGE);

        exerciseTypeDataTarget.setName(exerciseTypeDataSource.getName());
        exerciseTypeDataTarget.setExerciseDatas(exerciseTypeDataSource.getExerciseDatas());
    }

}