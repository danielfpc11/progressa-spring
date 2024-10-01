package progressa.progressaspring.facades;

import progressa.progressaspring.datas.SetData;
import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 * TODO: javadoc
 */
public interface SetFacade {

    List<SetData> findAll();

    Optional<SetData> findById(final Long id) throws IllegalArgumentException;

    void deleteById(final Long id) throws IllegalArgumentException;

    SetData save(final SetData setData) throws IllegalArgumentException;

}
