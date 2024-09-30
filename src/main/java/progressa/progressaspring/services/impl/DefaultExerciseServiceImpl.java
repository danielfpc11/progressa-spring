package progressa.progressaspring.services.impl;

import jakarta.annotation.Resource;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import progressa.progressaspring.models.ExerciseModel;
import progressa.progressaspring.repositories.ExerciseRepository;
import progressa.progressaspring.services.ExerciseService;

import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
@Service
public class DefaultExerciseServiceImpl implements ExerciseService {

    private static final String ID_NOT_NULL_MESSAGE = "Id must not be null.";
    private static final String ID_POSITIVE_MESSAGE = "Id must be greater than zero.";
    private static final String EXERCISE_NOT_NULL_MESSAGE = "Exercise must not be null.";

    @Resource
    private ExerciseRepository exerciseRepository;

    @Override
    public List<ExerciseModel> findAll() {
        return exerciseRepository.findAll();
    }

    @Override
    public Optional<ExerciseModel> findById(final Long id) throws IllegalArgumentException {
        assertIdNotNullAndPositive(id);
        return exerciseRepository.findById(id);
    }

    @Override
    public void deleteById(final Long id) throws IllegalArgumentException {
        assertIdNotNullAndPositive(id);
        exerciseRepository.deleteById(id);
    }

    @Override
    public ExerciseModel save(final ExerciseModel exerciseModel) throws IllegalArgumentException {
        Assert.notNull(exerciseModel, EXERCISE_NOT_NULL_MESSAGE);
        return exerciseRepository.save(exerciseModel);
    }

    private void assertIdNotNullAndPositive(final Long id) throws IllegalArgumentException {
        Assert.notNull(id, ID_NOT_NULL_MESSAGE);
        Assert.isTrue(id > NumberUtils.LONG_ZERO, ID_POSITIVE_MESSAGE);
    }

}
