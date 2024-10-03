package progressa.progressaspring.services;

import org.springframework.lang.NonNull;
import progressa.progressaspring.models.ExerciseModel;
import progressa.progressaspring.models.WorkoutModel;

/**
 * @author danielfpc11@gmail.com
 */
public interface JpaBidirectionalService {


    /**
     * Sets the relationships for the given workout model, ensuring all
     * associated entities are correctly linked.
     *
     * @param workoutModel the workout model for which relationships are to be set, must not be null.
     */
    void setWorkoutRelationships(@NonNull final WorkoutModel workoutModel);

    /**
     * Sets the relationships for the given exercise model, ensuring all
     * associated entities are correctly linked.
     *
     * @param exerciseModel the exercise model for which relationships are to be set, must not be null.
     */
    void setExerciseRelationships(@NonNull final ExerciseModel exerciseModel);

}