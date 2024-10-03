package progressa.progressaspring.facades;

import progressa.progressaspring.datas.ExerciseTypeData;

import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
public interface ExerciseTypeFacade {

    /**
     * Retrieves all exercise type data entries.
     *
     * @return a list of all exercise type data entries.
     */
    List<ExerciseTypeData> findAll();

    /**
     * Finds an exercise type data entry by its id.
     *
     * @param id the id of the exercise type data to retrieve.
     * @return an optional containing the found exercise type data entry,
     *         or an empty optional if no entry is found.
     * @throws IllegalArgumentException if the provided id is null or invalid.
     */
    Optional<ExerciseTypeData> findById(final Long id) throws IllegalArgumentException;

    /**
     * Deletes an exercise type data entry by its id.
     *
     * @param id the id of the exercise type data to delete.
     * @throws IllegalArgumentException if the provided id is null or invalid.
     */
    void deleteById(final Long id) throws IllegalArgumentException;

    /**
     * Saves an exercise type data entry.
     *
     * @param exerciseTypeData the exercise type data entry to save.
     * @return the saved exercise type data entry.
     * @throws IllegalArgumentException if the provided data is null or invalid.
     */
    ExerciseTypeData save(final ExerciseTypeData exerciseTypeData) throws IllegalArgumentException;

}
