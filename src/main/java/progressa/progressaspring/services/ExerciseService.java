package progressa.progressaspring.services;

import progressa.progressaspring.models.ExerciseModel;
import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 * TODO: javadoc
 */
public interface ExerciseService {

    List<ExerciseModel> findAll();

    Optional<ExerciseModel> findById(final Long id) throws IllegalArgumentException;

    void deleteById(final Long id) throws IllegalArgumentException;

    ExerciseModel save(final ExerciseModel exerciseModel) throws IllegalArgumentException;

}
