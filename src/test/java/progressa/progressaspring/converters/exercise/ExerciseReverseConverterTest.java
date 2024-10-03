package progressa.progressaspring.converters.exercise;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.converter.Converter;
import progressa.progressaspring.datas.ExerciseData;
import progressa.progressaspring.datas.SetData;
import progressa.progressaspring.models.ExerciseModel;
import progressa.progressaspring.models.ExerciseTypeModel;
import progressa.progressaspring.models.SetModel;
import progressa.progressaspring.models.WorkoutModel;
import progressa.progressaspring.services.ExerciseTypeService;
import progressa.progressaspring.services.WorkoutService;
import java.util.Collections;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
@ExtendWith(MockitoExtension.class)
public class ExerciseReverseConverterTest {

    private static final String EXERCISE_DATA_NOT_NULL_MESSAGE = "ExerciseData must not be null.";

    @InjectMocks
    private ExerciseReverseConverter exerciseReverseConverter;

    @Mock
    private WorkoutService workoutService;
    @Mock
    private ExerciseTypeService exerciseTypeService;
    @Mock
    private Converter<SetData, SetModel> setReverseConverter;

    private ExerciseData exerciseData;

    @BeforeEach
    void setUp() {
        exerciseData = ExerciseData.builder()
                                   .id(NumberUtils.LONG_ONE)
                                   .workoutId(NumberUtils.LONG_ONE)
                                   .exerciseTypeId(NumberUtils.LONG_ONE)
                                   .setDatas(Collections.emptyList())
                                   .build();
    }

    @Test
    void convertTest() {
        Mockito.when(workoutService.findById(NumberUtils.LONG_ONE)).thenReturn(getWorkoutModelOptional());
        Mockito.when(exerciseTypeService.findById(NumberUtils.LONG_ONE)).thenReturn(getExerciseTypeModelOptional());

        final ExerciseModel exerciseModel = exerciseReverseConverter.convert(exerciseData);

        Assertions.assertNotNull(exerciseModel);
        Assertions.assertEquals(exerciseData.getId(), exerciseModel.getId());
        Assertions.assertEquals(exerciseData.getWorkoutId(), exerciseModel.getWorkoutModel().getId());
        Assertions.assertEquals(exerciseData.getExerciseTypeId(), exerciseModel.getExerciseTypeModel().getId());
        Assertions.assertEquals(Collections.emptyList(), exerciseModel.getSetModels());
    }

    @Test
    void convertNullValuesTest() {
        exerciseData.setWorkoutId(null);
        exerciseData.setExerciseTypeId(null);

        final ExerciseModel exerciseModel = exerciseReverseConverter.convert(exerciseData);

        Assertions.assertNotNull(exerciseModel);
        Assertions.assertNull(exerciseModel.getWorkoutModel());
        Assertions.assertNull(exerciseModel.getExerciseTypeModel());
    }

    @Test
    void convertNullTest() {
        assertIllegalArgumentException(EXERCISE_DATA_NOT_NULL_MESSAGE, () -> exerciseReverseConverter.convert(null));
    }

    private Optional<WorkoutModel> getWorkoutModelOptional() {
        return Optional.of(WorkoutModel.builder()
                                       .id(NumberUtils.LONG_ONE)
                                       .build());
    }

    private Optional<ExerciseTypeModel> getExerciseTypeModelOptional() {
        return Optional.of(ExerciseTypeModel.builder()
                                            .id(NumberUtils.LONG_ONE)
                                            .build());
    }

    private void assertIllegalArgumentException(final String message, final Executable executable) {
        final IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, executable);
        Assertions.assertEquals(message, illegalArgumentException.getMessage());
    }

}
