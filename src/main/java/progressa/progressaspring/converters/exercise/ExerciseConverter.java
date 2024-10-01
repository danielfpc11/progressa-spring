package progressa.progressaspring.converters.exercise;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.Assert;
import progressa.progressaspring.datas.ExerciseData;
import progressa.progressaspring.models.ExerciseModel;

/**
 * @author danielfpc11@gmail.com
 */
public class ExerciseConverter implements Converter<ExerciseModel, ExerciseData> {

    private static final String EXERCISE_MODEL_NOT_NULL_MESSAGE = "ExerciseModel must not be null.";

    @Override
    public ExerciseData convert(final ExerciseModel exerciseModel) {
        Assert.notNull(exerciseModel, EXERCISE_MODEL_NOT_NULL_MESSAGE);
        return ExerciseData.builder()
                           .id(exerciseModel.getId())
                           .name(exerciseModel.getName())
                           .build();
    }

}
