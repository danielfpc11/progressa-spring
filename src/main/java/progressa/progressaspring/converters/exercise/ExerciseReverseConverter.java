package progressa.progressaspring.converters.exercise;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import progressa.progressaspring.datas.ExerciseData;
import progressa.progressaspring.datas.SetData;
import progressa.progressaspring.models.ExerciseModel;
import progressa.progressaspring.models.SetModel;
import progressa.progressaspring.services.ExerciseTypeService;
import progressa.progressaspring.services.WorkoutService;
import progressa.progressaspring.utils.AssertUtils;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
@AllArgsConstructor
public class ExerciseReverseConverter implements Converter<ExerciseData, ExerciseModel> {

    private final WorkoutService workoutService;
    private final ExerciseTypeService exerciseTypeService;
    private final Converter<SetData, SetModel> setReverseConverter;

    @Override
    public ExerciseModel convert(final ExerciseData exerciseData) {
        AssertUtils.notNull(exerciseData, ExerciseData.class);
        return ExerciseModel.builder()
                            .id(exerciseData.getId())
                            .workoutModel(Optional.ofNullable(exerciseData.getWorkoutId())
                                                  .flatMap(workoutService::findById)
                                                  .orElse(null))
                            .exerciseTypeModel(Optional.ofNullable(exerciseData.getExerciseTypeId())
                                                       .flatMap(exerciseTypeService::findById)
                                                       .orElse(null))
                            .setModels(exerciseData.getSetDatas()
                                                   .stream()
                                                   .map(setReverseConverter::convert)
                                                   .toList())
                            .build();
    }

}
