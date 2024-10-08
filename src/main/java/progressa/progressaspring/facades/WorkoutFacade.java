package progressa.progressaspring.facades;

import progressa.progressaspring.datas.WorkoutData;

import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
public interface WorkoutFacade {

    /**
     * Retrieves all workout data entries.
     *
     * @return a list of all workout data entries.
     */
    List<WorkoutData> findAll();

    /**
     * Finds a workout data entry by its id.
     *
     * @param id the id of the workout data to retrieve.
     * @return an optional containing the found workout data entry,
     *         or an empty optional if no entry is found.
     * @throws IllegalArgumentException if the provided id is null or invalid.
     */
    Optional<WorkoutData> findById(final Long id) throws IllegalArgumentException;

    /**
     * Deletes a workout data entry by its id.
     *
     * @param id the id of the workout data to delete.
     * @throws IllegalArgumentException if the provided id is null or invalid.
     */
    void deleteById(final Long id) throws IllegalArgumentException;

    /**
     * Saves a workout data entry.
     *
     * @param workoutData the workout data entry to save.
     * @return the saved workout data entry.
     * @throws IllegalArgumentException if the provided data is null or invalid.
     */
    WorkoutData save(final WorkoutData workoutData) throws IllegalArgumentException;

}

