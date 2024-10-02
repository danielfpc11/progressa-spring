package progressa.progressaspring.converters.exercisetype;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import progressa.progressaspring.datas.ExerciseData;
import progressa.progressaspring.datas.ExerciseTypeData;
import progressa.progressaspring.models.ExerciseModel;
import progressa.progressaspring.models.ExerciseTypeModel;
import progressa.progressaspring.utils.AssertUtils;

/**
 * @author danielfpc11@gmail.com
 */
@AllArgsConstructor
public class ExerciseTypeConverter implements Converter<ExerciseTypeModel, ExerciseTypeData> {

    private final Converter<ExerciseModel, ExerciseData> exerciseConverter;

    @Override
    public ExerciseTypeData convert(final ExerciseTypeModel exerciseTypeModel) {
        AssertUtils.notNull(exerciseTypeModel, ExerciseTypeModel.class);
        return ExerciseTypeData.builder()
                               .id(exerciseTypeModel.getId())
                               .name(exerciseTypeModel.getName())
                               .exerciseDatas(exerciseTypeModel.getExerciseModels()
                                                               .stream()
                                                               .map(exerciseConverter::convert)
                                                               .toList())
                               .build();
    }

}
