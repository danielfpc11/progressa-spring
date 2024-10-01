package progressa.progressaspring.converters.workout;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import progressa.progressaspring.datas.WorkoutData;
import progressa.progressaspring.models.WorkoutModel;

/**
 * @author danielfpc11@gmail.com
 */
public class WorkoutConverter implements Converter<WorkoutModel, WorkoutData> {

    private static final String WORKOUT_MODEL_NOT_NULL_MESSAGE = "WorkoutModel must not be null.";

    @Override
    public WorkoutData convert(@NonNull final WorkoutModel workoutModel) {
        Assert.notNull(workoutModel, WORKOUT_MODEL_NOT_NULL_MESSAGE);
        return WorkoutData.builder()
                          .id(workoutModel.getId())
                          .date(workoutModel.getDate())
                          .build();
    }

}
