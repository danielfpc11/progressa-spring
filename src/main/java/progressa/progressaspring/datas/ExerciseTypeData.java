package progressa.progressaspring.datas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import progressa.progressaspring.groups.exercisetype.CreateExerciseTypeGroup;
import progressa.progressaspring.groups.exercisetype.UpdateExerciseTypeGroup;

import java.util.List;

/**
 * @author danielfpc11@gmail.com
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseTypeData {

    @Null(groups = CreateExerciseTypeGroup.class)
    @Positive
    private Long id;

    @NotBlank
    private String name;

    @NotNull(groups = UpdateExerciseTypeGroup.class)
    private List<ExerciseData> exerciseDatas;

}
