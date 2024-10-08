package progressa.progressaspring.services.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import progressa.progressaspring.models.ExerciseModel;
import progressa.progressaspring.repositories.ExerciseRepository;
import progressa.progressaspring.services.ExerciseService;
import progressa.progressaspring.services.JpaBidirectionalService;
import progressa.progressaspring.utils.AssertUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
@Service
public class DefaultExerciseServiceImpl implements ExerciseService {

    @Resource
    private ExerciseRepository exerciseRepository;
    @Resource
    private JpaBidirectionalService jpaBidirectionalService;

    @Override
    public List<ExerciseModel> findAll() {
        return exerciseRepository.findAll();
    }

    @Override
    public Optional<ExerciseModel> findById(final Long id) throws IllegalArgumentException {
        AssertUtils.idNotNullAndPositive(id);
        return exerciseRepository.findById(id);
    }

    @Override
    public void deleteById(final Long id) throws IllegalArgumentException {
        AssertUtils.idNotNullAndPositive(id);
        exerciseRepository.deleteById(id);
    }

    @Override
    public ExerciseModel save(final ExerciseModel exerciseModel) throws IllegalArgumentException {
        AssertUtils.notNull(exerciseModel, ExerciseModel.class);
        jpaBidirectionalService.setExerciseRelationships(exerciseModel);
        return exerciseRepository.save(exerciseModel);
    }

}
