
package progressa.progressaspring.populators.exercise;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import progressa.progressaspring.datas.ExerciseData;
import progressa.progressaspring.populators.BasePopulatorTest;

import java.util.Collections;

/**
 * @author danielfpc11@gmail.com
 */
@ExtendWith(MockitoExtension.class)
public class ExerciseDataRelationshipsPopulatorTest extends BasePopulatorTest {

    private static final String EXERCISE_TYPE_NAME = "Exercise Type Name";

    @InjectMocks
    private ExerciseDataRelationshipsPopulator exerciseDataRelationshipsPopulator;

    private ExerciseData exerciseDataSource;
    private ExerciseData exerciseDataTarget;

    @BeforeEach
    void setUp() {
        exerciseDataSource = ExerciseData.builder()
                                         .workoutId(NumberUtils.LONG_ONE)
                                         .exerciseTypeId(NumberUtils.LONG_ONE)
                                         .exerciseTypeName(EXERCISE_TYPE_NAME)
                                         .setDatas(Collections.emptyList())
                                         .build();
        exerciseDataTarget = new ExerciseData();
    }

    @Test
    void populateTest() {
        exerciseDataRelationshipsPopulator.populate(exerciseDataSource, exerciseDataTarget);

        Assertions.assertNull(exerciseDataTarget.getId());
        Assertions.assertEquals(exerciseDataSource.getSetDatas(), exerciseDataTarget.getSetDatas());
    }

    @Test
    void populateSourceNullTest() {
        assertSourceNull(() -> exerciseDataRelationshipsPopulator.populate(null, exerciseDataTarget));
    }

    @Test
    void populateTargetNullTest() {
        assertTargetNull(() -> exerciseDataRelationshipsPopulator.populate(exerciseDataSource, null));
    }

}
