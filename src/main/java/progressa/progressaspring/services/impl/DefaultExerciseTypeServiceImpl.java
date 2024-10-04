package progressa.progressaspring.services.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import progressa.progressaspring.models.ExerciseTypeModel;
import progressa.progressaspring.repositories.ExerciseTypeRepository;
import progressa.progressaspring.services.ExerciseTypeService;
import progressa.progressaspring.services.JpaBidirectionalService;
import progressa.progressaspring.utils.AssertUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
@Service
public class DefaultExerciseTypeServiceImpl implements ExerciseTypeService {

    @Resource
    private ExerciseTypeRepository exerciseTypeRepository;
    @Resource
    private JpaBidirectionalService jpaBidirectionalService;

    @Override
    public List<ExerciseTypeModel> findAll() {
        return exerciseTypeRepository.findAll();
    }

    @Override
    public Optional<ExerciseTypeModel> findById(final Long id) throws IllegalArgumentException {
        AssertUtils.idNotNullAndPositive(id);
        return exerciseTypeRepository.findById(id);
    }

    @Override
    public void deleteById(final Long id) throws IllegalArgumentException {
        AssertUtils.idNotNullAndPositive(id);
        exerciseTypeRepository.deleteById(id);
    }

    @Override
    public ExerciseTypeModel save(final ExerciseTypeModel exerciseTypeModel) throws IllegalArgumentException {
        AssertUtils.notNull(exerciseTypeModel, ExerciseTypeModel.class);
        jpaBidirectionalService.setExerciseTypeRelationships(exerciseTypeModel);
        return exerciseTypeRepository.save(exerciseTypeModel);
    }

}
