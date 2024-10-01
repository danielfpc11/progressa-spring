package progressa.progressaspring.converters.exercisetype;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.Assert;
import progressa.progressaspring.datas.ExerciseTypeData;
import progressa.progressaspring.models.ExerciseTypeModel;

/**
 * @author danielfpc11@gmail.com
 */
public class ExerciseTypeConverter implements Converter<ExerciseTypeModel, ExerciseTypeData> {

    private static final String EXERCISE_TYPE_MODEL_NOT_NULL_MESSAGE = "ExerciseTypeModel must not be null.";

    @Override
    public ExerciseTypeData convert(final ExerciseTypeModel exerciseTypeModel) {
        Assert.notNull(exerciseTypeModel, EXERCISE_TYPE_MODEL_NOT_NULL_MESSAGE);
        return ExerciseTypeData.builder()
                               .id(exerciseTypeModel.getId())
                               .name(exerciseTypeModel.getName())
                               .build();
    }

}
