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
public class ExerciseTypeData {

    private Long id;
    private String name;
    private List<ExerciseData> exerciseDatas;

}
