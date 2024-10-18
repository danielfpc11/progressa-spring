package progressa.progressaspring.populators.exercisetype;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import progressa.progressaspring.datas.ExerciseTypeData;
import progressa.progressaspring.populators.BasePopulatorTest;

import java.util.Collections;

/**
 * @author danielfpc11@gmail.com
 */
@ExtendWith(MockitoExtension.class)
public class ExerciseTypeDataRelationshipsPopulatorTest extends BasePopulatorTest {

    private static final String EXERCISE_TYPE_NAME = "Exercise Type Name";

    @InjectMocks
    private ExerciseTypeDataRelationshipsPopulator exerciseTypeDataRelationshipsPopulator;

    private ExerciseTypeData exerciseTypeDataSource;
    private ExerciseTypeData exerciseTypeDataTarget;

    @BeforeEach
    void setUp() {
        exerciseTypeDataSource = ExerciseTypeData.builder()
                                                 .name(EXERCISE_TYPE_NAME)
                                                 .exerciseDatas(Collections.emptyList())
                                                 .build();
        exerciseTypeDataTarget = new ExerciseTypeData();
    }

    @Test
    void populateTest() {
        exerciseTypeDataRelationshipsPopulator.populate(exerciseTypeDataSource, exerciseTypeDataTarget);

        Assertions.assertNull(exerciseTypeDataTarget.getId());
        Assertions.assertEquals(exerciseTypeDataSource.getExerciseDatas(), exerciseTypeDataTarget.getExerciseDatas());
    }

    @Test
    void populateSourceNullTest() {
        assertSourceNull(() -> exerciseTypeDataRelationshipsPopulator.populate(null, exerciseTypeDataTarget));
    }

    @Test
    void populateTargetNullTest() {
        assertTargetNull(() -> exerciseTypeDataRelationshipsPopulator.populate(exerciseTypeDataSource, null));
    }

}