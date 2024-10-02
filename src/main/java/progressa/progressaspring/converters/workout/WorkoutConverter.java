package progressa.progressaspring.converters.workout;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import progressa.progressaspring.datas.WorkoutData;
import progressa.progressaspring.models.WorkoutModel;
import progressa.progressaspring.utils.AssertUtils;

/**
 * @author danielfpc11@gmail.com
 */
public class WorkoutConverter implements Converter<WorkoutModel, WorkoutData> {

    @Override
    public WorkoutData convert(@NonNull final WorkoutModel workoutModel) {
        AssertUtils.notNull(workoutModel, WorkoutModel.class);
        return WorkoutData.builder()
                          .id(workoutModel.getId())
                          .date(workoutModel.getDate())
                          .build();
    }

}
