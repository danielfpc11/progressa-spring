package progressa.progressaspring.converters.workout;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import progressa.progressaspring.converters.exercise.ExerciseConverter;
import progressa.progressaspring.datas.WorkoutData;
import progressa.progressaspring.models.WorkoutModel;
import progressa.progressaspring.utils.AssertUtils;

/**
 * @author danielfpc11@gmail.com
 */
@AllArgsConstructor
public class WorkoutConverter implements Converter<WorkoutModel, WorkoutData> {

    private final ExerciseConverter exerciseConverter;

    @Override
    public WorkoutData convert(@NonNull final WorkoutModel workoutModel) {
        AssertUtils.notNull(workoutModel, WorkoutModel.class);
        return WorkoutData.builder()
                          .id(workoutModel.getId())
                          .date(workoutModel.getDate())
                          .exerciseDatas(workoutModel.getExerciseModels()
                                                     .stream()
                                                     .map(exerciseConverter::convert)
                                                     .toList())
                          .build();
    }

}
