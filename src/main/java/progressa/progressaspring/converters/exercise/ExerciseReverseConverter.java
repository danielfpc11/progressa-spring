package progressa.progressaspring.converters.exercise;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import progressa.progressaspring.datas.ExerciseData;
import progressa.progressaspring.models.ExerciseModel;
import progressa.progressaspring.services.ExerciseTypeService;
import progressa.progressaspring.services.WorkoutService;
import progressa.progressaspring.utils.AssertUtils;

/**
 * @author danielfpc11@gmail.com
 */
@AllArgsConstructor
public class ExerciseReverseConverter implements Converter<ExerciseData, ExerciseModel> {

    private final WorkoutService workoutService;
    private final ExerciseTypeService exerciseTypeService;

    @Override
    public ExerciseModel convert(final ExerciseData exerciseData) {
        AssertUtils.notNull(exerciseData, ExerciseData.class);
        return ExerciseModel.builder()
                            .id(exerciseData.getId())
                            .workoutModel(workoutService.findById(exerciseData.getWorkoutId())
                                                        .orElseThrow())
                            .exerciseTypeModel(exerciseTypeService.findById(exerciseData.getExerciseTypeId())
                                                                  .orElseThrow())
                            .build();
    }

}
