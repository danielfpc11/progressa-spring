package progressa.progressaspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import progressa.progressaspring.models.ExerciseModel;

/**
 * @author danielfpc11@gmail.com
 */
public interface ExerciseRepository extends JpaRepository<ExerciseModel, Long> {
}
