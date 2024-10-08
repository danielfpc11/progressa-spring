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
import progressa.progressaspring.models.ExerciseModel;
import progressa.progressaspring.repositories.ExerciseRepository;
import progressa.progressaspring.services.JpaBidirectionalService;

/**
 * @author danielfpc11@gmail.com
 */
@ExtendWith(MockitoExtension.class)
public class DefaultExerciseServiceImplTest extends BaseTest {

    private static final String ID_NOT_NULL_MESSAGE = "Id must not be null.";
    private static final String ID_POSITIVE_MESSAGE = "Id must be greater than zero.";
    private static final String EXERCISE_MODEL_NOT_NULL_MESSAGE = "ExerciseModel must not be null.";

    @InjectMocks
    private DefaultExerciseServiceImpl defaultExerciseServiceImpl;

    @Mock
    private ExerciseRepository exerciseRepository;
    @Mock
    private JpaBidirectionalService jpaBidirectionalService;

    @Test
    void findAllTest() {
        defaultExerciseServiceImpl.findAll();
        Mockito.verify(exerciseRepository).findAll();
    }

    @Test
    void findByIdTest() {
        Assertions.assertDoesNotThrow(() -> defaultExerciseServiceImpl.findById(NumberUtils.LONG_ONE));
        Mockito.verify(exerciseRepository).findById(NumberUtils.LONG_ONE);
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
        Assertions.assertDoesNotThrow(() -> defaultExerciseServiceImpl.deleteById(NumberUtils.LONG_ONE));
        Mockito.verify(exerciseRepository).deleteById(NumberUtils.LONG_ONE);
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
        final ExerciseModel exerciseModel = new ExerciseModel();
        Mockito.doNothing().when(jpaBidirectionalService).setExerciseRelationships(exerciseModel);
        Assertions.assertDoesNotThrow(() -> defaultExerciseServiceImpl.save(exerciseModel));
        Mockito.verify(jpaBidirectionalService).setExerciseRelationships(exerciseModel);
        Mockito.verify(exerciseRepository).save(exerciseModel);
    }

    @Test
    void saveNullTest() {
        assertException(IllegalArgumentException.class, EXERCISE_MODEL_NOT_NULL_MESSAGE, () -> defaultExerciseServiceImpl.save(null));
        Mockito.verify(jpaBidirectionalService, Mockito.never()).setExerciseRelationships(null);
        Mockito.verify(exerciseRepository, Mockito.never()).save(null);
    }

    private void assertFindByInvalidId(final String message, final Long id) {
        assertException(IllegalArgumentException.class, message, () -> defaultExerciseServiceImpl.findById(id));
        Mockito.verify(exerciseRepository, Mockito.never()).findById(id);
    }

    private void assertDeleteByInvalidId(final String message, final Long id) {
        assertException(IllegalArgumentException.class, message, () -> defaultExerciseServiceImpl.deleteById(id));
        Mockito.verify(exerciseRepository, Mockito.never()).deleteById(id);
    }

}
