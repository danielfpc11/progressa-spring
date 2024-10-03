package progressa.progressaspring.services;

import progressa.progressaspring.models.WorkoutModel;
import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
public interface WorkoutService {

    /**
     * Retrieves all workout model entries.
     *
     * @return a list of all workout model entries.
     */
    List<WorkoutModel> findAll();

    /**
     * Finds a workout model entry by its id.
     *
     * @param id the id of the workout model to retrieve.
     * @return an optional containing the found workout model entry,
     *         or an empty optional if no entry is found.
     * @throws IllegalArgumentException if the provided id is null or invalid.
     */
    Optional<WorkoutModel> findById(final Long id) throws IllegalArgumentException;

    /**
     * Deletes a workout model entry by its id.
     *
     * @param id the id of the workout model to delete.
     * @throws IllegalArgumentException if the provided id is null or invalid.
     */
    void deleteById(final Long id) throws IllegalArgumentException;

    /**
     * Saves a workout model entry.
     *
     * @param workoutModel the workout model entry to save.
     * @return the saved workout model entry.
     * @throws IllegalArgumentException if the provided data is null or invalid.
     */
    WorkoutModel save(final WorkoutModel workoutModel) throws IllegalArgumentException;

}
