package progressa.progressaspring.datas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author danielfpc11@gmail.com
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SetData {

    private Long id;
    private Integer number;
    private Float weight;
    private Integer repetitions;
    private Integer rir;
    private Long exerciseId;

}
