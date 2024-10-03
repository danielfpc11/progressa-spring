package progressa.progressaspring.services;

import progressa.progressaspring.models.ExerciseModel;

import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
public interface ExerciseService {

    /**
     * Retrieves all exercise model entries.
     *
     * @return a list of all exercise model entries.
     */
    List<ExerciseModel> findAll();

    /**
     * Finds an exercise model entry by its id.
     *
     * @param id the id of the exercise model to retrieve.
     * @return an optional containing the found exercise model entry,
     *         or an empty optional if no entry is found.
     * @throws IllegalArgumentException if the provided id is null or invalid.
     */
    Optional<ExerciseModel> findById(final Long id) throws IllegalArgumentException;

    /**
     * Deletes an exercise model entry by its id.
     *
     * @param id the id of the exercise model to delete.
     * @throws IllegalArgumentException if the provided id is null or invalid.
     */
    void deleteById(final Long id) throws IllegalArgumentException;

    /**
     * Saves an exercise model entry.
     *
     * @param exerciseModel the exercise model entry to save.
     * @return the saved exercise model entry.
     * @throws IllegalArgumentException if the provided data is null or invalid.
     */
    ExerciseModel save(final ExerciseModel exerciseModel) throws IllegalArgumentException;

}
