package progressa.progressaspring.converters.exercisetype;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import progressa.progressaspring.BaseTest;
import progressa.progressaspring.converters.exercise.ExerciseConverter;
import progressa.progressaspring.datas.ExerciseTypeData;
import progressa.progressaspring.models.ExerciseTypeModel;

import java.util.Collections;

/**
 * @author danielfpc11@gmail.com
 */
@ExtendWith(MockitoExtension.class)
public class ExerciseTypeConverterTest extends BaseTest {

    private static final String EXERCISE_TYPE_MODEL_NOT_NULL_MESSAGE = "ExerciseTypeModel must not be null.";
    private static final String EXERCISE_TYPE_NAME = "Exercise Type Name";

    @InjectMocks
    private ExerciseTypeConverter exerciseTypeConverter;

    @Mock
    private ExerciseConverter exerciseConverter;

    private ExerciseTypeModel exerciseTypeModel;

    @BeforeEach
    void setUp() {
        exerciseTypeModel = ExerciseTypeModel.builder()
                                             .id(NumberUtils.LONG_ONE)
                                             .name(EXERCISE_TYPE_NAME)
                                             .exerciseModels(Collections.emptyList())
                                             .build();
    }

    @Test
    void convertTest() {
        final ExerciseTypeData exerciseTypeData = exerciseTypeConverter.convert(exerciseTypeModel);

        Assertions.assertNotNull(exerciseTypeData);
        Assertions.assertEquals(exerciseTypeModel.getId(), exerciseTypeData.getId());
        Assertions.assertEquals(exerciseTypeModel.getName(), exerciseTypeData.getName());
        Assertions.assertEquals(Collections.emptyList(), exerciseTypeData.getExerciseDatas());
    }

    @Test
    void convertNullTest() {
        assertException(IllegalArgumentException.class, EXERCISE_TYPE_MODEL_NOT_NULL_MESSAGE, () -> exerciseTypeConverter.convert(null));
    }

}
