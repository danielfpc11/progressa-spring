package progressa.progressaspring.services.impl;

import jakarta.annotation.Resource;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import progressa.progressaspring.models.ExerciseTypeModel;
import progressa.progressaspring.repositories.ExerciseTypeRepository;
import progressa.progressaspring.services.ExerciseTypeService;

import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
@Service
public class DefaultExerciseTypeServiceImpl implements ExerciseTypeService {

    private static final String ID_NOT_NULL_MESSAGE = "Id must not be null.";
    private static final String ID_POSITIVE_MESSAGE = "Id must be greater than zero.";
    private static final String EXERCISE_TYPE_NOT_NULL_MESSAGE = "ExerciseType must not be null.";

    @Resource
    private ExerciseTypeRepository exerciseTypeRepository;

    @Override
    public List<ExerciseTypeModel> findAll() {
        return exerciseTypeRepository.findAll();
    }

    @Override
    public Optional<ExerciseTypeModel> findById(final Long id) throws IllegalArgumentException {
        assertIdNotNullAndPositive(id);
        return exerciseTypeRepository.findById(id);
    }

    @Override
    public void deleteById(final Long id) throws IllegalArgumentException {
        assertIdNotNullAndPositive(id);
        exerciseTypeRepository.deleteById(id);
    }

    @Override
    public ExerciseTypeModel save(final ExerciseTypeModel exerciseTypeModel) throws IllegalArgumentException {
        Assert.notNull(exerciseTypeModel, EXERCISE_TYPE_NOT_NULL_MESSAGE);
        return exerciseTypeRepository.save(exerciseTypeModel);
    }

    private void assertIdNotNullAndPositive(final Long id) throws IllegalArgumentException {
        Assert.notNull(id, ID_NOT_NULL_MESSAGE);
        Assert.isTrue(id > NumberUtils.LONG_ZERO, ID_POSITIVE_MESSAGE);
    }

}
