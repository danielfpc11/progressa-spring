package progressa.progressaspring.facades;

import progressa.progressaspring.datas.WorkoutData;
import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 * TODO: javadoc
 */
public interface WorkoutFacade {

    List<WorkoutData> findAll();

    Optional<WorkoutData> findById(final Long id) throws IllegalArgumentException;

    void deleteById(final Long id) throws IllegalArgumentException;

    WorkoutData save(final WorkoutData workoutData) throws IllegalArgumentException;

}

