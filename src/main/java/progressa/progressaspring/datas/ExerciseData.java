package progressa.progressaspring.datas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author danielfpc11@gmail.com
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseData {

    private Long id;
    private Long workoutId;
    private Long exerciseTypeId;
    private List<SetData> setDatas;

}
