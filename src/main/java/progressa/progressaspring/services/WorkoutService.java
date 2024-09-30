package progressa.progressaspring.services;

import progressa.progressaspring.models.WorkoutModel;
import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 * TODO: javadoc
 */
public interface WorkoutService {

    List<WorkoutModel> findAll();

    Optional<WorkoutModel> findById(final Long id) throws IllegalArgumentException;

    void deleteById(final Long id) throws IllegalArgumentException;

    WorkoutModel save(final WorkoutModel workoutModel) throws IllegalArgumentException;

}
