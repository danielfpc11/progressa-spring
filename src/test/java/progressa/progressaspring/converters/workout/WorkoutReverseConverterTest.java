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
public class WorkoutReverseConverterTest extends BaseTest {

    private static final String WORKOUT_DATA_NOT_NULL_MESSAGE = "WorkoutData must not be null.";

    @InjectMocks
    private WorkoutReverseConverter workoutReverseConverter;

    @Mock
    private Converter<ExerciseData, ExerciseModel> exerciseReverseConverter;

    private WorkoutData workoutData;

    @BeforeEach
    void setUp() {
        workoutData = WorkoutData.builder()
                                 .id(NumberUtils.LONG_ONE)
                                 .date(new Date())
                                 .exerciseDatas(Collections.emptyList())
                                 .build();
    }

    @Test
    void convertTest() {
        final WorkoutModel workoutModel = workoutReverseConverter.convert(workoutData);

        Assertions.assertNotNull(workoutModel);
        Assertions.assertEquals(workoutData.getId(), workoutModel.getId());
        Assertions.assertEquals(workoutData.getDate(), workoutModel.getDate());
        Assertions.assertEquals(Collections.emptyList(), workoutModel.getExerciseModels());
    }

    @Test
    void convertNullTest() {
        assertException(IllegalArgumentException.class, WORKOUT_DATA_NOT_NULL_MESSAGE, () -> workoutReverseConverter.convert(null));
    }

}
