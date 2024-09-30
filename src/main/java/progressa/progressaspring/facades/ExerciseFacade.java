package progressa.progressaspring.facades;

import progressa.progressaspring.datas.ExerciseData;

import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 * TODO: javadoc
 */
public interface ExerciseFacade {

    List<ExerciseData> findAll();

    Optional<ExerciseData> findById(final Long id) throws IllegalArgumentException;

    void deleteById(final Long id) throws IllegalArgumentException;

    ExerciseData save(final ExerciseData exerciseData) throws IllegalArgumentException;

}
