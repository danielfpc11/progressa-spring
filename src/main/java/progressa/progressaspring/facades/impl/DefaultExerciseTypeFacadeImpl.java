package progressa.progressaspring.facades.impl;

import jakarta.annotation.Resource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import progressa.progressaspring.datas.ExerciseTypeData;
import progressa.progressaspring.facades.ExerciseTypeFacade;
import progressa.progressaspring.models.ExerciseTypeModel;
import progressa.progressaspring.services.ExerciseTypeService;

import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
@Component
public class DefaultExerciseTypeFacadeImpl implements ExerciseTypeFacade {

    @Resource
    private ExerciseTypeService exerciseTypeService;
    @Resource
    private Converter<ExerciseTypeModel, ExerciseTypeData> exerciseTypeConverter;
    @Resource
    private Converter<ExerciseTypeData, ExerciseTypeModel> exerciseTypeReverseConverter;

    @Override
    public List<ExerciseTypeData> findAll() {
        return exerciseTypeService.findAll()
                                  .stream()
                                  .map(exerciseTypeConverter::convert)
                                  .toList();
    }

    @Override
    public Optional<ExerciseTypeData> findById(final Long id) throws IllegalArgumentException {
        return exerciseTypeService.findById(id)
                                  .map(exerciseTypeConverter::convert);
    }

    @Override
    public void deleteById(final Long id) throws IllegalArgumentException {
        exerciseTypeService.deleteById(id);
    }

    @Override
    public ExerciseTypeData save(final ExerciseTypeData exerciseTypeData) throws IllegalArgumentException {
        return exerciseTypeConverter.convert(exerciseTypeService.save(exerciseTypeReverseConverter.convert(exerciseTypeData)));
    }

}
