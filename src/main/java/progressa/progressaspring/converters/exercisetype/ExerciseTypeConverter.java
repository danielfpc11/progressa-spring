package progressa.progressaspring.converters.exercisetype;

import jakarta.annotation.Resource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import progressa.progressaspring.datas.ExerciseData;
import progressa.progressaspring.datas.ExerciseTypeData;
import progressa.progressaspring.models.ExerciseModel;
import progressa.progressaspring.models.ExerciseTypeModel;
import progressa.progressaspring.utils.AssertUtils;

/**
 * @author danielfpc11@gmail.com
 */
@Component
public class ExerciseTypeConverter implements Converter<ExerciseTypeModel, ExerciseTypeData> {

    @Resource
    private Converter<ExerciseModel, ExerciseData> exerciseConverter;

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
