package progressa.progressaspring.converters.exercisetype;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.Assert;
import progressa.progressaspring.datas.ExerciseTypeData;
import progressa.progressaspring.models.ExerciseTypeModel;

/**
 * @author danielfpc11@gmail.com
 */
public class ExerciseTypeReverseConverter implements Converter<ExerciseTypeData, ExerciseTypeModel> {

    private static final String EXERCISE_TYPE_DATA_NOT_NULL_MESSAGE = "ExerciseTypeData must not be null.";

    @Override
    public ExerciseTypeModel convert(final ExerciseTypeData exerciseTypeData) {
        Assert.notNull(exerciseTypeData, EXERCISE_TYPE_DATA_NOT_NULL_MESSAGE);
        return ExerciseTypeModel.builder()
                                .id(exerciseTypeData.getId())
                                .name(exerciseTypeData.getName())
                                .build();
    }

}