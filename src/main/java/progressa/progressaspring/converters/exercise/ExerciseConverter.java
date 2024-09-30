package progressa.progressaspring.converters.exercise;

import org.springframework.core.convert.converter.Converter;
import progressa.progressaspring.datas.ExerciseData;
import progressa.progressaspring.models.ExerciseModel;

/**
 * @author danielfpc11@gmail.com
 */
public class ExerciseConverter implements Converter<ExerciseModel, ExerciseData> {

    @Override
    public ExerciseData convert(final ExerciseModel exerciseModel) {
        return ExerciseData.builder()
                           .id(exerciseModel.getId())
                           .name(exerciseModel.getName())
                           .build();
    }

}
