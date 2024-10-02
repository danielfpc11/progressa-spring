package progressa.progressaspring.converters.exercisetype;

import org.springframework.core.convert.converter.Converter;
import progressa.progressaspring.datas.ExerciseTypeData;
import progressa.progressaspring.models.ExerciseTypeModel;
import progressa.progressaspring.utils.AssertUtils;

/**
 * @author danielfpc11@gmail.com
 */
public class ExerciseTypeConverter implements Converter<ExerciseTypeModel, ExerciseTypeData> {

    @Override
    public ExerciseTypeData convert(final ExerciseTypeModel exerciseTypeModel) {
        AssertUtils.notNull(exerciseTypeModel, ExerciseTypeModel.class);
        return ExerciseTypeData.builder()
                               .id(exerciseTypeModel.getId())
                               .name(exerciseTypeModel.getName())
                               .build();
    }

}
