package progressa.progressaspring.services;

import org.springframework.lang.NonNull;
import progressa.progressaspring.models.ExerciseModel;
import progressa.progressaspring.models.WorkoutModel;

/**
 * @author danielfpc11@gmail.com
 * TODO: javadoc
 */
public interface JpaBidirectionalService {

    void setWorkoutRelationships(@NonNull final WorkoutModel workoutModel);

    void setExerciseRelationships(@NonNull final ExerciseModel exerciseModel);

}