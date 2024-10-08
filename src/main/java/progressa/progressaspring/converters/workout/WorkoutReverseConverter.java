package progressa.progressaspring.converters.workout;

import jakarta.annotation.Resource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import progressa.progressaspring.datas.ExerciseData;
import progressa.progressaspring.datas.WorkoutData;
import progressa.progressaspring.models.ExerciseModel;
import progressa.progressaspring.models.WorkoutModel;
import progressa.progressaspring.utils.AssertUtils;

/**
 * @author danielfpc11@gmail.com
 */
@Component
public class WorkoutReverseConverter implements Converter<WorkoutData, WorkoutModel> {

    @Resource
    private Converter<ExerciseData, ExerciseModel> exerciseReverseConverter;

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
