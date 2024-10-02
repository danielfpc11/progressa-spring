package progressa.progressaspring.converters.exercisetype;

import org.springframework.core.convert.converter.Converter;
import progressa.progressaspring.datas.ExerciseTypeData;
import progressa.progressaspring.models.ExerciseTypeModel;
import progressa.progressaspring.utils.AssertUtils;

/**
 * @author danielfpc11@gmail.com
 */
public class ExerciseTypeReverseConverter implements Converter<ExerciseTypeData, ExerciseTypeModel> {

    @Override
    public ExerciseTypeModel convert(final ExerciseTypeData exerciseTypeData) {
        AssertUtils.notNull(exerciseTypeData, ExerciseTypeData.class);
        return ExerciseTypeModel.builder()
                                .id(exerciseTypeData.getId())
                                .name(exerciseTypeData.getName())
                                .build();
    }

}