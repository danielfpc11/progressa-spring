package progressa.progressaspring.converters.exercise;

import org.springframework.core.convert.converter.Converter;
import progressa.progressaspring.datas.ExerciseData;
import progressa.progressaspring.models.ExerciseModel;

/**
 * @author danielfpc11@gmail.com
 */
public class ExerciseReverseConverter implements Converter<ExerciseData, ExerciseModel> {

    @Override
    public ExerciseModel convert(final ExerciseData exerciseData) {
        return ExerciseModel.builder()
                            .id(exerciseData.getId())
                            .name(exerciseData.getName())
                            .build();
    }

}