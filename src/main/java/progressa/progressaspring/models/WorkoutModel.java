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
import java.util.Date;

/**
 * @author danielfpc11@gmail.com
 */
@Entity
@Table(name = "workout")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private Date date;

}
