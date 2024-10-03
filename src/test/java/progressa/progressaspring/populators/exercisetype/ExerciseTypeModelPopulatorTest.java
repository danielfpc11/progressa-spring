package progressa.progressaspring.populators.exercisetype;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import progressa.progressaspring.datas.ExerciseTypeData;
import progressa.progressaspring.models.ExerciseTypeModel;
import progressa.progressaspring.populators.BasePopulator;
import progressa.progressaspring.populators.BasePopulatorTest;

import java.util.Collections;

/**
 * @author danielfpc11@gmail.com
 */
@ExtendWith(MockitoExtension.class)
public class ExerciseTypeModelPopulatorTest extends BasePopulatorTest {

    private static final String EXERCISE_TYPE_NAME = "Exercise Type Name";

    @InjectMocks
    private ExerciseTypeModelPopulator exerciseTypeModelPopulator;

    private ExerciseTypeModel exerciseTypeModelSource;
    private ExerciseTypeModel exerciseTypeModelTarget;

    @BeforeEach
    void setUp() {
        exerciseTypeModelSource = ExerciseTypeModel.builder()
                                                   .name(EXERCISE_TYPE_NAME)
                                                   .exerciseModels(Collections.emptyList())
                                                   .build();
        exerciseTypeModelTarget = new ExerciseTypeModel();
    }

    @Test
    void populateTest() {
        exerciseTypeModelPopulator.populate(exerciseTypeModelSource, exerciseTypeModelTarget);

        Assertions.assertNull(exerciseTypeModelTarget.getId());
        Assertions.assertEquals(exerciseTypeModelSource.getName(), exerciseTypeModelTarget.getName());
        Assertions.assertEquals(exerciseTypeModelSource.getExerciseModels(), exerciseTypeModelTarget.getExerciseModels());
    }

    @Test
    void populateSourceNullTest() {
        assertSourceNull(() -> exerciseTypeModelPopulator.populate(null, exerciseTypeModelTarget));
    }

    @Test
    void populateTargetNullTest() {
        assertTargetNull(() -> exerciseTypeModelPopulator.populate(exerciseTypeModelSource, null));
    }

}