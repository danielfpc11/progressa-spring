package progressa.progressaspring.populators.workout;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import progressa.progressaspring.datas.WorkoutData;
import progressa.progressaspring.populators.BasePopulator;

/**
 * @author danielfpc11@gmail.com
 */
@Component
public class WorkoutDataRelationshipsPopulator extends BasePopulator<WorkoutData, WorkoutData> {

    @Override
    public void populate(final WorkoutData workoutDataSource, final WorkoutData workoutDataTarget) {
        Assert.notNull(workoutDataSource, SOURCE_NOT_NULL_MESSAGE);
        Assert.notNull(workoutDataTarget, TARGET_NOT_NULL_MESSAGE);

        workoutDataTarget.setExerciseDatas(workoutDataSource.getExerciseDatas());
    }

}