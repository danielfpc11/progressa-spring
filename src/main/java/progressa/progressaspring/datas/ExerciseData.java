package progressa.progressaspring.datas;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import progressa.progressaspring.groups.exercise.CreateExerciseGroup;
import progressa.progressaspring.groups.exercise.UpdateExerciseGroup;

import java.util.List;

/**
 * @author danielfpc11@gmail.com
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseData {

    @Null(groups = CreateExerciseGroup.class)
    @Positive
    private Long id;

    @NotNull
    @Positive
    private Long workoutId;

    @NotNull
    @Positive
    private Long exerciseTypeId;

    private String exerciseTypeName;

    @NotNull(groups = UpdateExerciseGroup.class)
    private List<SetData> setDatas;

}
