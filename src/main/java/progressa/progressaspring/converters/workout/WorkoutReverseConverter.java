package progressa.progressaspring.converters.workout;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import progressa.progressaspring.datas.WorkoutData;
import progressa.progressaspring.models.WorkoutModel;
import progressa.progressaspring.utils.AssertUtils;

/**
 * @author danielfpc11@gmail.com
 */
public class WorkoutReverseConverter implements Converter<WorkoutData, WorkoutModel> {

    @Override
    public WorkoutModel convert(@NonNull final WorkoutData workoutData) {
        AssertUtils.notNull(workoutData, WorkoutData.class);
        return WorkoutModel.builder()
                           .id(workoutData.getId())
                           .date(workoutData.getDate())
                           .build();
    }

}
