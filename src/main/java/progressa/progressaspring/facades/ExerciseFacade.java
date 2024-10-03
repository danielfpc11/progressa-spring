package progressa.progressaspring.facades;

import progressa.progressaspring.datas.ExerciseData;
import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
public interface ExerciseFacade {

    /**
     * Retrieves all exercise data entries.
     *
     * @return a list of all exercise data entries.
     */
    List<ExerciseData> findAll();

    /**
     * Finds an exercise data entry by its id.
     *
     * @param id the id of the exercise data to retrieve.
     * @return an optional containing the found exercise data entry,
     *         or an empty optional if no entry is found.
     * @throws IllegalArgumentException if the provided id is null or invalid.
     */
    Optional<ExerciseData> findById(final Long id) throws IllegalArgumentException;

    /**
     * Deletes an exercise data entry by its id.
     *
     * @param id the id of the exercise data to delete.
     * @throws IllegalArgumentException if the provided id is null or invalid.
     */
    void deleteById(final Long id) throws IllegalArgumentException;

    /**
     * Saves an exercise data entry.
     *
     * @param exerciseData the exercise data entry to save.
     * @return the saved exercise data entry.
     * @throws IllegalArgumentException if the provided data is null or invalid.
     */
    ExerciseData save(final ExerciseData exerciseData) throws IllegalArgumentException;

}
