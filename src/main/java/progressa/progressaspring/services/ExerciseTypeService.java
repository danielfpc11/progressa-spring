package progressa.progressaspring.services;

import progressa.progressaspring.models.ExerciseTypeModel;
import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 * TODO: javadoc
 */
public interface ExerciseTypeService {

    List<ExerciseTypeModel> findAll();

    Optional<ExerciseTypeModel> findById(final Long id) throws IllegalArgumentException;

    void deleteById(final Long id) throws IllegalArgumentException;

    ExerciseTypeModel save(final ExerciseTypeModel exerciseTypeModel) throws IllegalArgumentException;

}
