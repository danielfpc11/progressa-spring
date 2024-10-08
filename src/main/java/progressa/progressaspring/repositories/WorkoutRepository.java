package progressa.progressaspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import progressa.progressaspring.models.WorkoutModel;

/**
 * @author danielfpc11@gmail.com
 */
public interface WorkoutRepository extends JpaRepository<WorkoutModel, Long> {
}
