package progressa.progressaspring.converters.exercise;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import progressa.progressaspring.datas.ExerciseData;
import progressa.progressaspring.datas.SetData;
import progressa.progressaspring.models.ExerciseModel;
import progressa.progressaspring.models.ExerciseTypeModel;
import progressa.progressaspring.models.SetModel;
import progressa.progressaspring.models.WorkoutModel;
import progressa.progressaspring.utils.AssertUtils;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
@AllArgsConstructor
public class ExerciseConverter implements Converter<ExerciseModel, ExerciseData> {

    private final Converter<SetModel, SetData> setConverter;

    @Override
    public ExerciseData convert(final ExerciseModel exerciseModel) {
        AssertUtils.notNull(exerciseModel, ExerciseModel.class);
        return ExerciseData.builder()
                           .id(exerciseModel.getId())
                           .workoutId(Optional.ofNullable(exerciseModel.getWorkoutModel())
                                              .map(WorkoutModel::getId)
                                              .orElse(null))
                           .exerciseTypeId(Optional.ofNullable(exerciseModel.getExerciseTypeModel())
                                                   .map(ExerciseTypeModel::getId)
                                                   .orElse(null))
                           .setDatas(exerciseModel.getSetModels()
                                                  .stream()
                                                  .map(setConverter::convert)
                                                  .toList())
                           .build();
    }

}
