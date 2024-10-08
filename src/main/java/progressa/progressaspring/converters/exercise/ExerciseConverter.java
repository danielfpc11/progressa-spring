package progressa.progressaspring.converters.exercise;

import jakarta.annotation.Resource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
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
@Component
public class ExerciseConverter implements Converter<ExerciseModel, ExerciseData> {

    @Resource
    private Converter<SetModel, SetData> setConverter;

    @Override
    public ExerciseData convert(final ExerciseModel exerciseModel) {
        AssertUtils.notNull(exerciseModel, ExerciseModel.class);
        final Optional<ExerciseTypeModel> exerciseTypeModelOptional = Optional.ofNullable(exerciseModel.getExerciseTypeModel());
        return ExerciseData.builder()
                           .id(exerciseModel.getId())
                           .workoutId(Optional.ofNullable(exerciseModel.getWorkoutModel())
                                              .map(WorkoutModel::getId)
                                              .orElse(null))
                           .exerciseTypeId(exerciseTypeModelOptional.map(ExerciseTypeModel::getId)
                                                                    .orElse(null))
                           .exerciseTypeName(exerciseTypeModelOptional.map(ExerciseTypeModel::getName)
                                                                      .orElse(null))
                           .setDatas(exerciseModel.getSetModels()
                                                  .stream()
                                                  .map(setConverter::convert)
                                                  .toList())
                           .build();
    }

}
