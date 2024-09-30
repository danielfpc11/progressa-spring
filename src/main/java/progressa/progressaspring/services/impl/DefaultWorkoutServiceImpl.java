package progressa.progressaspring.services.impl;

import jakarta.annotation.Resource;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import progressa.progressaspring.models.WorkoutModel;
import progressa.progressaspring.repositories.WorkoutRepository;
import progressa.progressaspring.services.WorkoutService;
import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
@Service
public class DefaultWorkoutServiceImpl implements WorkoutService {

    private static final String ID_NOT_NULL_MESSAGE = "Id must not be null.";
    private static final String ID_POSITIVE_MESSAGE = "Id must be greater than zero.";
    private static final String WORKOUT_NOT_NULL_MESSAGE = "Workout must not be null.";

    @Resource
    private WorkoutRepository workoutRepository;

    @Override
    public List<WorkoutModel> findAll() {
        return workoutRepository.findAll();
    }

    @Override
    public Optional<WorkoutModel> findById(final Long id) throws IllegalArgumentException {
        assertIdNotNullAndPositive(id);
        return workoutRepository.findById(id);
    }

    @Override
    public void deleteById(final Long id) throws IllegalArgumentException {
        assertIdNotNullAndPositive(id);
        workoutRepository.deleteById(id);
    }

    @Override
    public WorkoutModel save(final WorkoutModel workoutModel) throws IllegalArgumentException {
        Assert.notNull(workoutModel, WORKOUT_NOT_NULL_MESSAGE);
        return workoutRepository.save(workoutModel);
    }

    private void assertIdNotNullAndPositive(final Long id) throws IllegalArgumentException {
        Assert.notNull(id, ID_NOT_NULL_MESSAGE);
        Assert.isTrue(id > NumberUtils.LONG_ZERO, ID_POSITIVE_MESSAGE);
    }

}
