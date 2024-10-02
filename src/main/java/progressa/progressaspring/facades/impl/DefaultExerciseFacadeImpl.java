package progressa.progressaspring.facades.impl;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import progressa.progressaspring.datas.ExerciseData;
import progressa.progressaspring.facades.ExerciseFacade;
import progressa.progressaspring.models.ExerciseModel;
import progressa.progressaspring.services.ExerciseService;
import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
@AllArgsConstructor
public class DefaultExerciseFacadeImpl implements ExerciseFacade {

    private final ExerciseService exerciseService;
    private final Converter<ExerciseModel, ExerciseData> exerciseConverter;
    private final Converter<ExerciseData, ExerciseModel> exerciseReverseConverter;

    @Override
    public List<ExerciseData> findAll() {
        return exerciseService.findAll()
                              .stream()
                              .map(exerciseConverter::convert)
                              .toList();
    }

    @Override
    public Optional<ExerciseData> findById(final Long id) throws IllegalArgumentException {
        return exerciseService.findById(id)
                              .map(exerciseConverter::convert);
    }

    @Override
    public void deleteById(final Long id) throws IllegalArgumentException {
        exerciseService.deleteById(id);
    }

    @Override
    public ExerciseData save(final ExerciseData exerciseData) throws IllegalArgumentException {
        return exerciseConverter.convert(exerciseService.save(exerciseReverseConverter.convert(exerciseData)));
    }

}
