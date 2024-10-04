package progressa.progressaspring.populators.set;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import progressa.progressaspring.models.ExerciseModel;
import progressa.progressaspring.models.SetModel;
import progressa.progressaspring.populators.BasePopulatorTest;

/**
 * @author danielfpc11@gmail.com
 */
@ExtendWith(MockitoExtension.class)
public class SetModelPopulatorTest extends BasePopulatorTest {

    @InjectMocks
    private SetModelPopulator setModelPopulator;

    private SetModel setModelSource;
    private SetModel setModelTarget;

    @BeforeEach
    void setUp() {
        setModelSource = SetModel.builder()
                                 .number(NumberUtils.INTEGER_ONE)
                                 .weight(NumberUtils.FLOAT_ONE)
                                 .repetitions(NumberUtils.INTEGER_ONE)
                                 .rir(NumberUtils.INTEGER_ONE)
                                 .exerciseModel(new ExerciseModel())
                                 .build();
        setModelTarget = new SetModel();
    }

    @Test
    void populateTest() {
        setModelPopulator.populate(setModelSource, setModelTarget);

        Assertions.assertNull(setModelTarget.getId());
        Assertions.assertEquals(setModelSource.getNumber(), setModelTarget.getNumber());
        Assertions.assertEquals(setModelSource.getWeight(), setModelTarget.getWeight());
        Assertions.assertEquals(setModelSource.getRepetitions(), setModelTarget.getRepetitions());
        Assertions.assertEquals(setModelSource.getRir(), setModelTarget.getRir());
        Assertions.assertEquals(setModelSource.getExerciseModel(), setModelTarget.getExerciseModel());
    }

    @Test
    void populateSourceNullTest() {
        assertSourceNull(() -> setModelPopulator.populate(null, setModelTarget));
    }

    @Test
    void populateTargetNullTest() {
        assertTargetNull(() -> setModelPopulator.populate(setModelSource, null));
    }


}
