package progressa.progressaspring.facades;

import progressa.progressaspring.datas.SetData;
import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
public interface SetFacade {

    /**
     * Retrieves all set data entries.
     *
     * @return a list of all set data entries.
     */
    List<SetData> findAll();

    /**
     * Finds a set data entry by its id.
     *
     * @param id the id of the set data to retrieve.
     * @return an optional containing the found set data entry,
     *         or an empty optional if no entry is found.
     * @throws IllegalArgumentException if the provided id is null or invalid.
     */
    Optional<SetData> findById(final Long id) throws IllegalArgumentException;

    /**
     * Deletes a set data entry by its id.
     *
     * @param id the id of the set data to delete.
     * @throws IllegalArgumentException if the provided id is null or invalid.
     */
    void deleteById(final Long id) throws IllegalArgumentException;

    /**
     * Saves an set data entry.
     *
     * @param setData the set data entry to save.
     * @return the saved set data entry.
     * @throws IllegalArgumentException if the provided data is null or invalid.
     */
    SetData save(final SetData setData) throws IllegalArgumentException;

}
