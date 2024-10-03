package progressa.progressaspring.services;

import progressa.progressaspring.models.SetModel;

import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
public interface SetService {

    /**
     * Retrieves all set model entries.
     *
     * @return a list of all set model entries.
     */
    List<SetModel> findAll();

    /**
     * Finds a set model entry by its id.
     *
     * @param id the id of the set model to retrieve.
     * @return an optional containing the found set model entry,
     *         or an empty optional if no entry is found.
     * @throws IllegalArgumentException if the provided id is null or invalid.
     */
    Optional<SetModel> findById(final Long id) throws IllegalArgumentException;

    /**
     * Deletes a set model entry by its id.
     *
     * @param id the id of the set model to delete.
     * @throws IllegalArgumentException if the provided id is null or invalid.
     */
    void deleteById(final Long id) throws IllegalArgumentException;

    /**
     * Saves a set model entry.
     *
     * @param setModel the set model entry to save.
     * @return the saved set model entry.
     * @throws IllegalArgumentException if the provided data is null or invalid.
     */
    SetModel save(final SetModel setModel) throws IllegalArgumentException;

}
