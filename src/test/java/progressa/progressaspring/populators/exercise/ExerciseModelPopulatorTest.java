package progressa.progressaspring.populators.exercise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import progressa.progressaspring.models.ExerciseModel;
import progressa.progressaspring.models.ExerciseTypeModel;
import progressa.progressaspring.models.WorkoutModel;
import progressa.progressaspring.populators.BasePopulatorTest;

import java.util.Collections;

/**
 * @author danielfpc11@gmail.com
 */
@ExtendWith(MockitoExtension.class)
public class ExerciseModelPopulatorTest extends BasePopulatorTest {

    @InjectMocks
    private ExerciseModelPopulator exerciseModelPopulator;

    private ExerciseModel exerciseModelSource;
    private ExerciseModel exerciseModelTarget;

    @BeforeEach
    void setUp() {
        exerciseModelSource = ExerciseModel.builder()
                                         .workoutModel(new WorkoutModel())
                                         .exerciseTypeModel(new ExerciseTypeModel())
                                         .setModels(Collections.emptyList())
                                         .build();
        exerciseModelTarget = new ExerciseModel();
    }

    @Test
    void populateTest() {
        exerciseModelPopulator.populate(exerciseModelSource, exerciseModelTarget);

        Assertions.assertNull(exerciseModelTarget.getId());
        Assertions.assertEquals(exerciseModelSource.getWorkoutModel(), exerciseModelTarget.getWorkoutModel());
        Assertions.assertEquals(exerciseModelSource.getExerciseTypeModel(), exerciseModelTarget.getExerciseTypeModel());
        Assertions.assertEquals(exerciseModelSource.getSetModels(), exerciseModelTarget.getSetModels());
    }

    @Test
    void populateSourceNullTest() {
        assertSourceNull(() -> exerciseModelPopulator.populate(null, exerciseModelTarget));
    }

    @Test
    void populateTargetNullTest() {
        assertTargetNull(() -> exerciseModelPopulator.populate(exerciseModelSource, null));
    }

}
