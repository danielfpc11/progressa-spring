package progressa.progressaspring.converters.exercise;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.Assert;
import progressa.progressaspring.datas.ExerciseData;
import progressa.progressaspring.models.ExerciseModel;

/**
 * @author danielfpc11@gmail.com
 */
public class ExerciseReverseConverter implements Converter<ExerciseData, ExerciseModel> {

    private static final String EXERCISE_DATA_NOT_NULL_MESSAGE = "ExerciseData must not be null.";

    @Override
    public ExerciseModel convert(final ExerciseData exerciseData) {
        Assert.notNull(exerciseData, EXERCISE_DATA_NOT_NULL_MESSAGE);
        return ExerciseModel.builder()
                            .id(exerciseData.getId())
                            .name(exerciseData.getName())
                            .build();
    }

}