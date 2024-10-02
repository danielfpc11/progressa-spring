package progressa.progressaspring.converters.exercise;

import org.springframework.core.convert.converter.Converter;
import progressa.progressaspring.datas.ExerciseData;
import progressa.progressaspring.models.ExerciseModel;
import progressa.progressaspring.utils.AssertUtils;

/**
 * @author danielfpc11@gmail.com
 */
public class ExerciseReverseConverter implements Converter<ExerciseData, ExerciseModel> {

    @Override
    public ExerciseModel convert(final ExerciseData exerciseData) {
        AssertUtils.notNull(exerciseData, ExerciseData.class);
        return ExerciseModel.builder()
                            .id(exerciseData.getId())
                            .build();
    }

}
