package progressa.progressaspring.converters.workout;

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
import progressa.progressaspring.datas.ExerciseData;
import progressa.progressaspring.datas.WorkoutData;
import progressa.progressaspring.models.ExerciseModel;
import progressa.progressaspring.models.WorkoutModel;

import java.util.Collections;
import java.util.Date;

/**
 * @author danielfpc11@gmail.com
 */
@ExtendWith(MockitoExtension.class)
public class WorkoutConverterTest extends BaseTest {

    private static final String WORKOUT_MODEL_NOT_NULL_MESSAGE = "WorkoutModel must not be null.";

    @InjectMocks
    private WorkoutConverter workoutConverter;

    @Mock
    private Converter<ExerciseModel, ExerciseData> exerciseConverter;

    private WorkoutModel workoutModel;

    @BeforeEach
    void setUp() {
        workoutModel = WorkoutModel.builder()
                                   .id(NumberUtils.LONG_ONE)
                                   .date(new Date())
                                   .exerciseModels(Collections.emptyList())
                                   .build();
    }

    @Test
    void convertTest() {
        final WorkoutData workoutData = workoutConverter.convert(workoutModel);

        Assertions.assertNotNull(workoutData);
        Assertions.assertEquals(workoutModel.getId(), workoutData.getId());
        Assertions.assertEquals(workoutModel.getDate(), workoutData.getDate());
        Assertions.assertEquals(Collections.emptyList(), workoutData.getExerciseDatas());
    }

    @Test
    void convertNullTest() {
        assertException(IllegalArgumentException.class, WORKOUT_MODEL_NOT_NULL_MESSAGE, () -> workoutConverter.convert(null));
    }

}
