package progressa.progressaspring.converters.set;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import progressa.progressaspring.BaseTest;
import progressa.progressaspring.datas.SetData;
import progressa.progressaspring.models.ExerciseModel;
import progressa.progressaspring.models.SetModel;
import progressa.progressaspring.services.ExerciseService;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
@ExtendWith(MockitoExtension.class)
public class SetReverseConverterTest extends BaseTest {

    private static final String SET_DATA_NOT_NULL_MESSAGE = "SetData must not be null.";

    @InjectMocks
    private SetReverseConverter setReverseConverter;

    @Mock
    private ExerciseService exerciseService;

    private SetData setData;

    @BeforeEach
    void setUp() {
        setData = SetData.builder()
                         .id(NumberUtils.LONG_ONE)
                         .number(NumberUtils.INTEGER_ONE)
                         .weight(NumberUtils.FLOAT_ONE)
                         .repetitions(NumberUtils.INTEGER_ONE)
                         .rir(NumberUtils.INTEGER_ONE)
                         .exerciseId(NumberUtils.LONG_ONE)
                         .build();
    }

    @Test
    void convertTest() {
        Mockito.when(exerciseService.findById(NumberUtils.LONG_ONE)).thenReturn(getExerciseModelOptional());

        final SetModel setModel = setReverseConverter.convert(setData);

        Assertions.assertNotNull(setModel);
        Assertions.assertEquals(setData.getId(), setModel.getId());
        Assertions.assertEquals(setData.getNumber(), setModel.getNumber());
        Assertions.assertEquals(setData.getWeight(), setModel.getWeight());
        Assertions.assertEquals(setData.getRepetitions(), setModel.getRepetitions());
        Assertions.assertEquals(setData.getRir(), setModel.getRir());
        Assertions.assertEquals(setData.getExerciseId(), setModel.getExerciseModel().getId());
    }

    @Test
    void convertNullTest() {
        assertException(IllegalArgumentException.class, SET_DATA_NOT_NULL_MESSAGE, () -> setReverseConverter.convert(null));
    }

    private Optional<ExerciseModel> getExerciseModelOptional() {
        return Optional.of(ExerciseModel.builder()
                                        .id(NumberUtils.LONG_ONE)
                                        .build());
    }

}
