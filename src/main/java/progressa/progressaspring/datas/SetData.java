package progressa.progressaspring.datas;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import progressa.progressaspring.groups.set.CreateSetGroup;
import progressa.progressaspring.groups.set.UpdateSetGroup;

/**
 * @author danielfpc11@gmail.com
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SetData {

    @Null(groups = CreateSetGroup.class)
    @Positive
    private Long id;

    @NotNull
    @Positive
    private Integer number;

    @NotNull
    @Positive
    private Float weight;

    @NotNull
    @Positive
    private Integer repetitions;

    @NotNull
    @PositiveOrZero
    private Integer rir;

    @NotNull(groups = UpdateSetGroup.class)
    @Positive
    private Long exerciseId;

}
