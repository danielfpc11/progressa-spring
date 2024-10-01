package progressa.progressaspring.services;

import progressa.progressaspring.models.SetModel;
import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 * TODO: javadoc
 */
public interface SetService {

    List<SetModel> findAll();

    Optional<SetModel> findById(final Long id) throws IllegalArgumentException;

    void deleteById(final Long id) throws IllegalArgumentException;

    SetModel save(final SetModel setModel) throws IllegalArgumentException;

}
