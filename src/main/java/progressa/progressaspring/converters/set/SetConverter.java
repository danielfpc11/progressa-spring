package progressa.progressaspring.converters.set;

import org.springframework.core.convert.converter.Converter;
import progressa.progressaspring.datas.SetData;
import progressa.progressaspring.models.ExerciseModel;
import progressa.progressaspring.models.SetModel;
import progressa.progressaspring.utils.AssertUtils;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
public class SetConverter implements Converter<SetModel, SetData> {

    @Override
    public SetData convert(final SetModel setModel) {
        AssertUtils.notNull(setModel, SetModel.class);
        return SetData.builder()
                      .id(setModel.getId())
                      .number(setModel.getNumber())
                      .weight(setModel.getWeight())
                      .repetitions(setModel.getRepetitions())
                      .rir(setModel.getRir())
                      .exerciseId(Optional.ofNullable(setModel.getExerciseModel())
                                          .map(ExerciseModel::getId)
                                          .orElse(null))
                      .build();
    }

}
