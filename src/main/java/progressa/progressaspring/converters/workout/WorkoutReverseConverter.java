package progressa.progressaspring.converters.workout;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import progressa.progressaspring.datas.WorkoutData;
import progressa.progressaspring.models.WorkoutModel;

/**
 * @author danielfpc11@gmail.com
 */
public class WorkoutReverseConverter implements Converter<WorkoutData, WorkoutModel> {

    private static final String WORKOUT_DATA_NOT_NULL_MESSAGE = "WorkoutData must not be null.";

    @Override
    public WorkoutModel convert(@NonNull final WorkoutData workoutData) {
        Assert.notNull(workoutData, WORKOUT_DATA_NOT_NULL_MESSAGE);
        return WorkoutModel.builder()
                           .id(workoutData.getId())
                           .date(workoutData.getDate())
                           .build();
    }

}
