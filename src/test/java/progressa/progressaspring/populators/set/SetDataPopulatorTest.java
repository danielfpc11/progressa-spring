package progressa.progressaspring.populators.set;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import progressa.progressaspring.datas.SetData;
import progressa.progressaspring.populators.BasePopulatorTest;

/**
 * @author danielfpc11@gmail.com
 */
@ExtendWith(MockitoExtension.class)
public class SetDataPopulatorTest extends BasePopulatorTest {

    @InjectMocks
    private SetDataPopulator setDataPopulator;

    private SetData setDataSource;
    private SetData setDataTarget;

    @BeforeEach
    void setUp() {
        setDataSource = SetData.builder()
                               .number(NumberUtils.INTEGER_ONE)
                               .weight(NumberUtils.FLOAT_ONE)
                               .repetitions(NumberUtils.INTEGER_ONE)
                               .rir(NumberUtils.INTEGER_ONE)
                               .exerciseId(NumberUtils.LONG_ONE)
                               .build();
        setDataTarget = new SetData();
    }

    @Test
    void populateTest() {
        setDataPopulator.populate(setDataSource, setDataTarget);

        Assertions.assertNull(setDataTarget.getId());
        Assertions.assertNull(setDataTarget.getExerciseId());
        Assertions.assertEquals(setDataSource.getNumber(), setDataTarget.getNumber());
        Assertions.assertEquals(setDataSource.getWeight(), setDataTarget.getWeight());
        Assertions.assertEquals(setDataSource.getRepetitions(), setDataTarget.getRepetitions());
        Assertions.assertEquals(setDataSource.getRir(), setDataTarget.getRir());
    }

    @Test
    void populateSourceNullTest() {
        assertSourceNull(() -> setDataPopulator.populate(null, setDataTarget));
    }

    @Test
    void populateTargetNullTest() {
        assertTargetNull(() -> setDataPopulator.populate(setDataSource, null));
    }

}
