package progressa.progressaspring.converters.exercise;

import org.springframework.core.convert.converter.Converter;
import progressa.progressaspring.datas.ExerciseData;
import progressa.progressaspring.models.ExerciseModel;
import progressa.progressaspring.utils.AssertUtils;

/**
 * @author danielfpc11@gmail.com
 */
public class ExerciseConverter implements Converter<ExerciseModel, ExerciseData> {

    @Override
    public ExerciseData convert(final ExerciseModel exerciseModel) {
        AssertUtils.notNull(exerciseModel, ExerciseModel.class);
        return ExerciseData.builder()
                           .id(exerciseModel.getId())
                           .build();
    }

}
