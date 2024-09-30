package progressa.progressaspring.facades.impl;

import lombok.AllArgsConstructor;
import progressa.progressaspring.converters.workout.WorkoutConverter;
import progressa.progressaspring.converters.workout.WorkoutReverseConverter;
import progressa.progressaspring.datas.WorkoutData;
import progressa.progressaspring.facades.WorkoutFacade;
import progressa.progressaspring.services.WorkoutService;
import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
@AllArgsConstructor
public class DefaultWorkoutFacadeImpl implements WorkoutFacade {

    private final WorkoutService workoutService;
    private final WorkoutConverter workoutConverter;
    private final WorkoutReverseConverter workoutReverseConverter;

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
