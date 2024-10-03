package progressa.progressaspring.converters.set;

import jakarta.annotation.Resource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import progressa.progressaspring.datas.SetData;
import progressa.progressaspring.models.SetModel;
import progressa.progressaspring.services.ExerciseService;
import progressa.progressaspring.utils.AssertUtils;

import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
@Component
public class SetReverseConverter implements Converter<SetData, SetModel> {

    @Resource
    private ExerciseService exerciseService;

    @Override
    public SetModel convert(final SetData setData) {
        AssertUtils.notNull(setData, SetData.class);
        return SetModel.builder()
                       .id(setData.getId())
                       .number(setData.getNumber())
                       .weight(setData.getWeight())
                       .repetitions(setData.getRepetitions())
                       .rir(setData.getRir())
                       .exerciseModel(Optional.ofNullable(setData.getExerciseId())
                                              .flatMap(exerciseService::findById)
                                              .orElse(null))
                       .build();
    }

}
