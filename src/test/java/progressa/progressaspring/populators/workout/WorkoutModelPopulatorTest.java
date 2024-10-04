package progressa.progressaspring.populators.workout;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import progressa.progressaspring.models.WorkoutModel;
import progressa.progressaspring.populators.BasePopulatorTest;

import java.util.Collections;
import java.util.Date;

/**
 * @author danielfpc11@gmail.com
 */
@ExtendWith(MockitoExtension.class)
public class WorkoutModelPopulatorTest extends BasePopulatorTest {

    @InjectMocks
    private WorkoutModelPopulator workoutModelPopulator;

    private WorkoutModel workoutModelSource;
    private WorkoutModel workoutModelTarget;

    @BeforeEach
    void setUp() {
        workoutModelSource = WorkoutModel.builder()
                                         .date(new Date())
                                         .exerciseModels(Collections.emptyList())
                                         .build();
        workoutModelTarget = new WorkoutModel();
    }

    @Test
    void populateTest() {
        workoutModelPopulator.populate(workoutModelSource, workoutModelTarget);

        Assertions.assertNull(workoutModelTarget.getId());
        Assertions.assertEquals(workoutModelSource.getDate(), workoutModelTarget.getDate());
        Assertions.assertEquals(workoutModelSource.getExerciseModels(), workoutModelTarget.getExerciseModels());
    }

    @Test
    void populateSourceNullTest() {
        assertSourceNull(() -> workoutModelPopulator.populate(null, workoutModelTarget));
    }

    @Test
    void populateTargetNullTest() {
        assertTargetNull(() -> workoutModelPopulator.populate(workoutModelSource, null));
    }

}
