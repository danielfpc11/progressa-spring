package progressa.progressaspring.datas;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import progressa.progressaspring.groups.workout.CreateWorkoutGroup;
import progressa.progressaspring.groups.workout.UpdateWorkoutGroup;

import java.util.Date;
import java.util.List;

/**
 * @author danielfpc11@gmail.com
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutData {

    @Null(groups = CreateWorkoutGroup.class)
    @Positive
    private Long id;

    @NotNull
    private Date date;

    @NotNull(groups = UpdateWorkoutGroup.class)
    private List<ExerciseData> exerciseDatas;

}
