package progressa.progressaspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import progressa.progressaspring.models.ExerciseTypeModel;

/**
 * @author danielfpc11@gmail.com
 */
public interface ExerciseTypeRepository extends JpaRepository<ExerciseTypeModel, Long> {
}
