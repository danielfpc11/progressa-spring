package progressa.progressaspring.services.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import progressa.progressaspring.models.WorkoutModel;
import progressa.progressaspring.repositories.WorkoutRepository;
import progressa.progressaspring.services.WorkoutService;
import progressa.progressaspring.utils.AssertUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
@Service
public class DefaultWorkoutServiceImpl implements WorkoutService {

    @Resource
    private WorkoutRepository workoutRepository;

    @Override
    public List<WorkoutModel> findAll() {
        return workoutRepository.findAll();
    }

    @Override
    public Optional<WorkoutModel> findById(final Long id) throws IllegalArgumentException {
        AssertUtils.idNotNullAndPositive(id);
        return workoutRepository.findById(id);
    }

    @Override
    public void deleteById(final Long id) throws IllegalArgumentException {
        AssertUtils.idNotNullAndPositive(id);
        workoutRepository.deleteById(id);
    }

    @Override
    public WorkoutModel save(final WorkoutModel workoutModel) throws IllegalArgumentException {
        AssertUtils.notNull(workoutModel, WorkoutModel.class);
        return workoutRepository.save(workoutModel);
    }

}
