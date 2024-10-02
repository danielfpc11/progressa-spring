package progressa.progressaspring.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * @author danielfpc11@gmail.com
 */
@Entity
@Table(name = "exercise")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private WorkoutModel workoutModel;

    @ManyToOne
    @JoinColumn(name = "exercise_type_id")
    private ExerciseTypeModel exerciseTypeModel;

    @OneToMany(mappedBy = "exerciseModel")
    private List<SetModel> setModels;

}
