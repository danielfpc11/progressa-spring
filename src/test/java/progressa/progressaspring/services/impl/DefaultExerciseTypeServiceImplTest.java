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
import progressa.progressaspring.models.ExerciseTypeModel;
import progressa.progressaspring.repositories.ExerciseTypeRepository;
import progressa.progressaspring.services.JpaBidirectionalService;

/**
 * @author danielfpc11@gmail.com
 */
@ExtendWith(MockitoExtension.class)
public class DefaultExerciseTypeServiceImplTest extends BaseTest {

    private static final String ID_NOT_NULL_MESSAGE = "Id must not be null.";
    private static final String ID_POSITIVE_MESSAGE = "Id must be greater than zero.";
    private static final String EXERCISE_TYPE_MODEL_NOT_NULL_MESSAGE = "ExerciseTypeModel must not be null.";

    @InjectMocks
    private DefaultExerciseTypeServiceImpl defaultExerciseTypeServiceImpl;

    @Mock
    private ExerciseTypeRepository exerciseTypeRepository;
    @Mock
    private JpaBidirectionalService jpaBidirectionalService;

    @Test
    void findAllTest() {
        defaultExerciseTypeServiceImpl.findAll();
        Mockito.verify(exerciseTypeRepository).findAll();
    }

    @Test
    void findByIdTest() {
        Assertions.assertDoesNotThrow(() -> defaultExerciseTypeServiceImpl.findById(NumberUtils.LONG_ONE));
        Mockito.verify(exerciseTypeRepository).findById(NumberUtils.LONG_ONE);
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
        Assertions.assertDoesNotThrow(() -> defaultExerciseTypeServiceImpl.deleteById(NumberUtils.LONG_ONE));
        Mockito.verify(exerciseTypeRepository).deleteById(NumberUtils.LONG_ONE);
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
        final ExerciseTypeModel exerciseTypeModel = new ExerciseTypeModel();
        Mockito.doNothing().when(jpaBidirectionalService).setExerciseTypeRelationships(exerciseTypeModel);
        Assertions.assertDoesNotThrow(() -> defaultExerciseTypeServiceImpl.save(exerciseTypeModel));
        Mockito.verify(jpaBidirectionalService).setExerciseTypeRelationships(exerciseTypeModel);
        Mockito.verify(exerciseTypeRepository).save(exerciseTypeModel);
    }

    @Test
    void saveNullTest() {
        assertException(IllegalArgumentException.class, EXERCISE_TYPE_MODEL_NOT_NULL_MESSAGE, () -> defaultExerciseTypeServiceImpl.save(null));
        Mockito.verify(jpaBidirectionalService, Mockito.never()).setExerciseRelationships(null);
        Mockito.verify(exerciseTypeRepository, Mockito.never()).save(null);
    }

    private void assertFindByInvalidId(final String message, final Long id) {
        assertException(IllegalArgumentException.class, message, () -> defaultExerciseTypeServiceImpl.findById(id));
        Mockito.verify(exerciseTypeRepository, Mockito.never()).findById(id);
    }

    private void assertDeleteByInvalidId(final String message, final Long id) {
        assertException(IllegalArgumentException.class, message, () -> defaultExerciseTypeServiceImpl.deleteById(id));
        Mockito.verify(exerciseTypeRepository, Mockito.never()).deleteById(id);
    }

}
