package progressa.progressaspring.converters.workout;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import progressa.progressaspring.datas.WorkoutData;
import progressa.progressaspring.models.WorkoutModel;

/**
 * @author danielfpc11@gmail.com
 */
public class WorkoutConverter implements Converter<WorkoutModel, WorkoutData> {

    @Override
    public WorkoutData convert(@NonNull final WorkoutModel workoutModel) {
        return WorkoutData.builder()
                          .id(workoutModel.getId())
                          .date(workoutModel.getDate())
                          .build();
    }

}
