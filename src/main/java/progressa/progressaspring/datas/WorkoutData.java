package progressa.progressaspring.datas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * @author danielfpc11@gmail.com
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutData {

    private Long id;
    private Date date;

}
