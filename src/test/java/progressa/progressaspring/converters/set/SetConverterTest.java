package progressa.progressaspring.converters.set;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import progressa.progressaspring.BaseTest;
import progressa.progressaspring.datas.SetData;
import progressa.progressaspring.models.ExerciseModel;
import progressa.progressaspring.models.SetModel;

/**
 * @author danielfpc11@gmail.com
 */
@ExtendWith(MockitoExtension.class)
public class SetConverterTest extends BaseTest {

    private static final String SET_MODEL_NOT_NULL_MESSAGE = "SetModel must not be null.";

    @InjectMocks
    private SetConverter setConverter;

    private SetModel setModel;

    @BeforeEach
    void setUp() {
        setModel = SetModel.builder()
                           .id(NumberUtils.LONG_ONE)
                           .number(NumberUtils.INTEGER_ONE)
                           .weight(NumberUtils.FLOAT_ONE)
                           .repetitions(NumberUtils.INTEGER_ONE)
                           .rir(NumberUtils.INTEGER_ONE)
                           .exerciseModel(ExerciseModel.builder()
                                                       .id(NumberUtils.LONG_ONE)
                                                       .build())
                           .build();
    }

    @Test
    void convertTest() {
        final SetData setData = setConverter.convert(setModel);

        Assertions.assertNotNull(setData);
        Assertions.assertEquals(setModel.getId(), setData.getId());
        Assertions.assertEquals(setModel.getNumber(), setData.getNumber());
        Assertions.assertEquals(setModel.getWeight(), setData.getWeight());
        Assertions.assertEquals(setModel.getRepetitions(), setData.getRepetitions());
        Assertions.assertEquals(setModel.getRir(), setData.getRir());
        Assertions.assertEquals(setModel.getExerciseModel().getId(), setData.getExerciseId());
    }

    @Test
    void convertNullTest() {
        assertException(IllegalArgumentException.class, SET_MODEL_NOT_NULL_MESSAGE, () -> setConverter.convert(null));
    }

}
