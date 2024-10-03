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
public class ExerciseTypeReverseConverter implements Converter<ExerciseTypeData, ExerciseTypeModel> {

    @Resource
    private Converter<ExerciseData, ExerciseModel> exerciseReverseConverter;

    @Override
    public ExerciseTypeModel convert(final ExerciseTypeData exerciseTypeData) {
        AssertUtils.notNull(exerciseTypeData, ExerciseTypeData.class);
        return ExerciseTypeModel.builder()
                                .id(exerciseTypeData.getId())
                                .name(exerciseTypeData.getName())
                                .exerciseModels(exerciseTypeData.getExerciseDatas()
                                                                .stream()
                                                                .map(exerciseReverseConverter::convert)
                                                                .toList())
                                .build();
    }

}