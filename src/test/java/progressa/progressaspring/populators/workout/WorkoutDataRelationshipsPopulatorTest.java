package progressa.progressaspring.populators.workout;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import progressa.progressaspring.datas.WorkoutData;
import progressa.progressaspring.populators.BasePopulatorTest;

import java.util.Collections;
import java.util.Date;

/**
 * @author danielfpc11@gmail.com
 */
@ExtendWith(MockitoExtension.class)
public class WorkoutDataRelationshipsPopulatorTest extends BasePopulatorTest {

    @InjectMocks
    private WorkoutDataRelationshipsPopulator workoutDataRelationshipsPopulator;

    private WorkoutData workoutDataSource;
    private WorkoutData workoutDataTarget;

    @BeforeEach
    void setUp() {
        workoutDataSource = WorkoutData.builder()
                                       .date(new Date())
                                       .exerciseDatas(Collections.emptyList())
                                       .build();
        workoutDataTarget = new WorkoutData();
    }

    @Test
    void populateTest() {
        workoutDataRelationshipsPopulator.populate(workoutDataSource, workoutDataTarget);

        Assertions.assertNull(workoutDataTarget.getId());
        Assertions.assertEquals(workoutDataSource.getExerciseDatas(), workoutDataTarget.getExerciseDatas());
    }

    @Test
    void populateSourceNullTest() {
        assertSourceNull(() -> workoutDataRelationshipsPopulator.populate(null, workoutDataTarget));
    }

    @Test
    void populateTargetNullTest() {
        assertTargetNull(() -> workoutDataRelationshipsPopulator.populate(workoutDataSource, null));
    }

}