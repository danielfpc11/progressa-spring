package progressa.progressaspring.converters.workout;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import progressa.progressaspring.datas.ExerciseData;
import progressa.progressaspring.datas.WorkoutData;
import progressa.progressaspring.models.ExerciseModel;
import progressa.progressaspring.models.WorkoutModel;
import progressa.progressaspring.utils.AssertUtils;

/**
 * @author danielfpc11@gmail.com
 */
@AllArgsConstructor
public class WorkoutReverseConverter implements Converter<WorkoutData, WorkoutModel> {

    private final Converter<ExerciseData, ExerciseModel> exerciseReverseConverter;

    @Override
    public WorkoutModel convert(@NonNull final WorkoutData workoutData) {
        AssertUtils.notNull(workoutData, WorkoutData.class);
        return WorkoutModel.builder()
                           .id(workoutData.getId())
                           .date(workoutData.getDate())
                           .exerciseModels(workoutData.getExerciseDatas()
                                                      .stream()
                                                      .map(exerciseReverseConverter::convert)
                                                      .toList())
                           .build();
    }

}
