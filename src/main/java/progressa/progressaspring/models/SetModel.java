package progressa.progressaspring.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author danielfpc11@gmail.com
 */
@Entity
@Table(name = "exercise_set")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SetModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "weight", nullable = false)
    private Float weight;

    @Column(name = "repetitions", nullable = false)
    private Integer repetitions;

    @Column(name = "rir")
    private Integer rir;

}
