package progressa.progressaspring.services.impl;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import progressa.progressaspring.BaseTest;
import progressa.progressaspring.models.WorkoutModel;
import progressa.progressaspring.repositories.WorkoutRepository;
import progressa.progressaspring.services.JpaBidirectionalService;

/**
 * @author danielfpc11@gmail.com
 */
@ExtendWith(MockitoExtension.class)
public class DefaultWorkoutServiceImplTest extends BaseTest {

    private static final String ID_NOT_NULL_MESSAGE = "Id must not be null.";
    private static final String ID_POSITIVE_MESSAGE = "Id must be greater than zero.";
    private static final String WORKOUT_MODEL_NOT_NULL_MESSAGE = "WorkoutModel must not be null.";

    @InjectMocks
    private DefaultWorkoutServiceImpl defaultWorkoutServiceImpl;

    @Mock
    private WorkoutRepository workoutRepository;
    @Mock
    private JpaBidirectionalService jpaBidirectionalService;

    @Test
    void findAllTest() {
        defaultWorkoutServiceImpl.findAll();
        Mockito.verify(workoutRepository).findAll();
    }

    @Test
    void findByIdTest() {
        Assertions.assertDoesNotThrow(() -> defaultWorkoutServiceImpl.findById(NumberUtils.LONG_ONE));
        Mockito.verify(workoutRepository).findById(NumberUtils.LONG_ONE);
    }

    @Test
    void findByIdNullTest() {
        assertFindByInvalidId(ID_NOT_NULL_MESSAGE, null);
    }

    @Test
    void findByNotPositiveTest() {
        assertFindByInvalidId(ID_POSITIVE_MESSAGE, NumberUtils.LONG_MINUS_ONE);
    }

    @Test
    void deleteByIdTest() {
        Assertions.assertDoesNotThrow(() -> defaultWorkoutServiceImpl.deleteById(NumberUtils.LONG_ONE));
        Mockito.verify(workoutRepository).deleteById(NumberUtils.LONG_ONE);
    }

    @Test
    void deleteByIdNullTest() {
        assertDeleteByInvalidId(ID_NOT_NULL_MESSAGE, null);
    }

    @Test
    void deleteByIdLowerThanZeroTest() {
        assertDeleteByInvalidId(ID_POSITIVE_MESSAGE, NumberUtils.LONG_MINUS_ONE);
    }

    @Test
    void saveTest() {
        final WorkoutModel workoutModel = new WorkoutModel();
        Mockito.doNothing().when(jpaBidirectionalService).setWorkoutRelationships(workoutModel);
        Assertions.assertDoesNotThrow(() -> defaultWorkoutServiceImpl.save(workoutModel));
        Mockito.verify(jpaBidirectionalService).setWorkoutRelationships(workoutModel);
        Mockito.verify(workoutRepository).save(workoutModel);
    }

    @Test
    void saveNullTest() {
        assertException(IllegalArgumentException.class, WORKOUT_MODEL_NOT_NULL_MESSAGE, () -> defaultWorkoutServiceImpl.save(null));
        Mockito.verify(jpaBidirectionalService, Mockito.never()).setExerciseRelationships(null);
        Mockito.verify(workoutRepository, Mockito.never()).save(null);
    }

    private void assertFindByInvalidId(final String message, final Long id) {
        assertException(IllegalArgumentException.class, message, () -> defaultWorkoutServiceImpl.findById(id));
        Mockito.verify(workoutRepository, Mockito.never()).findById(id);
    }

    private void assertDeleteByInvalidId(final String message, final Long id) {
        assertException(IllegalArgumentException.class, message, () -> defaultWorkoutServiceImpl.deleteById(id));
        Mockito.verify(workoutRepository, Mockito.never()).deleteById(id);
    }

}
