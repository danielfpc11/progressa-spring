package progressa.progressaspring.converters.exercise;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import progressa.progressaspring.BaseTest;
import progressa.progressaspring.converters.set.SetConverter;
import progressa.progressaspring.datas.ExerciseData;
import progressa.progressaspring.models.ExerciseModel;
import progressa.progressaspring.models.ExerciseTypeModel;
import progressa.progressaspring.models.WorkoutModel;

import java.util.Collections;

/**
 * @author danielfpc11@gmail.com
 */
@ExtendWith(MockitoExtension.class)
public class ExerciseConverterTest extends BaseTest {

    private static final String EXERCISE_MODEL_NOT_NULL_MESSAGE = "ExerciseModel must not be null.";

    @InjectMocks
    private ExerciseConverter exerciseConverter;

    @Mock
    private SetConverter setConverter;

    private ExerciseModel exerciseModel;

    @BeforeEach
    void setUp() {
        exerciseModel = ExerciseModel.builder()
                                     .id(NumberUtils.LONG_ONE)
                                     .workoutModel(WorkoutModel.builder()
                                                               .id(NumberUtils.LONG_ONE)
                                                               .build())
                                     .exerciseTypeModel(ExerciseTypeModel.builder()
                                                                         .id(NumberUtils.LONG_ONE)
                                                                         .build())
                                     .setModels(Collections.emptyList())
                                     .build();
    }

    @Test
    void convertTest() {
        final ExerciseData exerciseData = exerciseConverter.convert(exerciseModel);

        Assertions.assertNotNull(exerciseData);
        Assertions.assertEquals(exerciseModel.getId(), exerciseData.getId());
        Assertions.assertEquals(exerciseModel.getWorkoutModel().getId(), exerciseData.getWorkoutId());
        Assertions.assertEquals(exerciseModel.getExerciseTypeModel().getId(), exerciseData.getExerciseTypeId());
        Assertions.assertEquals(Collections.emptyList(), exerciseData.getSetDatas());
    }

    @Test
    void convertNullValuesTest() {
        exerciseModel.setWorkoutModel(null);
        exerciseModel.setExerciseTypeModel(null);

        final ExerciseData exerciseData = exerciseConverter.convert(exerciseModel);

        Assertions.assertNotNull(exerciseData);
        Assertions.assertNull(exerciseData.getWorkoutId());
        Assertions.assertNull(exerciseData.getExerciseTypeId());
    }

    @Test
    void convertNullTest() {
        assertException(IllegalArgumentException.class, EXERCISE_MODEL_NOT_NULL_MESSAGE, () -> exerciseConverter.convert(null));
    }

}
