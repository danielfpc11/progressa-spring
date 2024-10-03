package progressa.progressaspring.converters.exercisetype;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.converter.Converter;
import progressa.progressaspring.BaseTest;
import progressa.progressaspring.converters.exercise.ExerciseReverseConverter;
import progressa.progressaspring.datas.ExerciseData;
import progressa.progressaspring.datas.ExerciseTypeData;
import progressa.progressaspring.models.ExerciseModel;
import progressa.progressaspring.models.ExerciseTypeModel;
import java.util.Collections;

/**
 * @author danielfpc11@gmail.com
 */
@ExtendWith(MockitoExtension.class)
public class ExerciseTypeReverseConverterTest extends BaseTest {

    private static final String EXERCISE_TYPE_DATA_NOT_NULL_MESSAGE = "ExerciseTypeData must not be null.";
    private static final String EXERCISE_TYPE_NAME = "Exercise Type Name";

    @InjectMocks
    private ExerciseTypeReverseConverter exerciseTypeReverseConverter;

    @Mock
    private Converter<ExerciseData, ExerciseModel> exerciseReverseConverter;

    private ExerciseTypeData exerciseTypeData;

    @BeforeEach
    void setUp() {
        exerciseTypeData = ExerciseTypeData.builder()
                                           .id(NumberUtils.LONG_ONE)
                                           .name(EXERCISE_TYPE_NAME)
                                           .exerciseDatas(Collections.emptyList())
                                           .build();
    }

    @Test
    void convertTest() {
        final ExerciseTypeModel exerciseTypeModel = exerciseTypeReverseConverter.convert(exerciseTypeData);

        Assertions.assertNotNull(exerciseTypeModel);
        Assertions.assertEquals(exerciseTypeData.getId(), exerciseTypeModel.getId());
        Assertions.assertEquals(exerciseTypeData.getName(), exerciseTypeModel.getName());
        Assertions.assertEquals(Collections.emptyList(), exerciseTypeModel.getExerciseModels());
    }

    @Test
    void convertNullTest() {
        assertException(IllegalArgumentException.class, EXERCISE_TYPE_DATA_NOT_NULL_MESSAGE, () -> exerciseTypeReverseConverter.convert(null));
    }

}
