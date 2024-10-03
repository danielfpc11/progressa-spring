package progressa.progressaspring.services;

import progressa.progressaspring.models.ExerciseTypeModel;

import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
public interface ExerciseTypeService {

    /**
     * Retrieves all exercise type model entries.
     *
     * @return a list of all exercise type model entries.
     */
    List<ExerciseTypeModel> findAll();

    /**
     * Finds an exercise type model entry by its id.
     *
     * @param id the id of the exercise type model to retrieve.
     * @return an optional containing the found exercise type model entry,
     *         or an empty optional if no entry is found.
     * @throws IllegalArgumentException if the provided id is null or invalid.
     */
    Optional<ExerciseTypeModel> findById(final Long id) throws IllegalArgumentException;

    /**
     * Deletes an exercise type model entry by its id.
     *
     * @param id the id of the exercise type model to delete.
     * @throws IllegalArgumentException if the provided id is null or invalid.
     */
    void deleteById(final Long id) throws IllegalArgumentException;

    /**
     * Saves an exercise type model entry.
     *
     * @param exerciseTypeModel the exercise type model entry to save.
     * @return the saved exercise type model entry.
     * @throws IllegalArgumentException if the provided data is null or invalid.
     */
    ExerciseTypeModel save(final ExerciseTypeModel exerciseTypeModel) throws IllegalArgumentException;

}
