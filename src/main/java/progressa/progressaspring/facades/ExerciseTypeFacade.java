package progressa.progressaspring.facades;

import progressa.progressaspring.datas.ExerciseTypeData;

import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 * TODO: javadoc
 */
public interface ExerciseTypeFacade {

    List<ExerciseTypeData> findAll();

    Optional<ExerciseTypeData> findById(final Long id) throws IllegalArgumentException;

    void deleteById(final Long id) throws IllegalArgumentException;

    ExerciseTypeData save(final ExerciseTypeData exerciseTypeData) throws IllegalArgumentException;

}
