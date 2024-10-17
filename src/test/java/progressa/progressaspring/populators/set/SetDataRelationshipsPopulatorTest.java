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
public class SetDataRelationshipsPopulatorTest extends BasePopulatorTest {

    @InjectMocks
    private SetDataRelationshipsPopulator setDataRelationshipsPopulator;

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
        setDataRelationshipsPopulator.populate(setDataSource, setDataTarget);

        Assertions.assertNull(setDataTarget.getId());
        Assertions.assertEquals(setDataSource.getExerciseId(), setDataTarget.getExerciseId());
    }

    @Test
    void populateSourceNullTest() {
        assertSourceNull(() -> setDataRelationshipsPopulator.populate(null, setDataTarget));
    }

    @Test
    void populateTargetNullTest() {
        assertTargetNull(() -> setDataRelationshipsPopulator.populate(setDataSource, null));
    }

}
