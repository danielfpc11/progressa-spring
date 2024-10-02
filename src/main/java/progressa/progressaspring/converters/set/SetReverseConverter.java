package progressa.progressaspring.converters.set;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import progressa.progressaspring.datas.SetData;
import progressa.progressaspring.models.SetModel;
import progressa.progressaspring.services.ExerciseService;
import progressa.progressaspring.utils.AssertUtils;

/**
 * @author danielfpc11@gmail.com
 */
@AllArgsConstructor
public class SetReverseConverter implements Converter<SetData, SetModel> {

    private final ExerciseService exerciseService;

    @Override
    public SetModel convert(final SetData setData) {
        AssertUtils.notNull(setData, SetData.class);
        return SetModel.builder()
                       .id(setData.getId())
                       .number(setData.getNumber())
                       .weight(setData.getWeight())
                       .repetitions(setData.getRepetitions())
                       .rir(setData.getRir())
                       .exerciseModel(exerciseService.findById(setData.getExerciseId())
                                                     .orElseThrow())
                       .build();
    }

}
