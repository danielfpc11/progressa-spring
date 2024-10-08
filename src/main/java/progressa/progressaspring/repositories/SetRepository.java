package progressa.progressaspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import progressa.progressaspring.models.SetModel;

/**
 * @author danielfpc11@gmail.com
 */
public interface SetRepository extends JpaRepository<SetModel, Long> {
}
