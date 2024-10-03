package progressa.progressaspring.facades.impl;

import jakarta.annotation.Resource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import progressa.progressaspring.datas.WorkoutData;
import progressa.progressaspring.facades.WorkoutFacade;
import progressa.progressaspring.models.WorkoutModel;
import progressa.progressaspring.services.WorkoutService;

import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
@Component
public class DefaultWorkoutFacadeImpl implements WorkoutFacade {

    @Resource
    private WorkoutService workoutService;
    @Resource
    private Converter<WorkoutModel, WorkoutData> workoutConverter;
    @Resource
    private Converter<WorkoutData, WorkoutModel> workoutReverseConverter;

    @Override
    public List<WorkoutData> findAll() {
        return workoutService.findAll()
                             .stream()
                             .map(workoutConverter::convert)
                             .toList();
    }

    @Override
    public Optional<WorkoutData> findById(final Long id) throws IllegalArgumentException {
        return workoutService.findById(id)
                             .map(workoutConverter::convert);
    }

    @Override
    public void deleteById(final Long id) throws IllegalArgumentException {
        workoutService.deleteById(id);
    }

    @Override
    public WorkoutData save(final WorkoutData workoutData) throws IllegalArgumentException {
        return workoutConverter.convert(workoutService.save(workoutReverseConverter.convert(workoutData)));
    }

}
