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
import progressa.progressaspring.models.SetModel;
import progressa.progressaspring.repositories.SetRepository;

/**
 * @author danielfpc11@gmail.com
 */
@ExtendWith(MockitoExtension.class)
public class DefaultSetServiceImplTest extends BaseTest {

    private static final String ID_NOT_NULL_MESSAGE = "Id must not be null.";
    private static final String ID_POSITIVE_MESSAGE = "Id must be greater than zero.";
    private static final String SET_MODEL_NOT_NULL_MESSAGE = "SetModel must not be null.";

    @InjectMocks
    private DefaultSetServiceImpl defaultSetServiceImpl;

    @Mock
    private SetRepository setRepository;

    @Test
    void findAllTest() {
        defaultSetServiceImpl.findAll();
        Mockito.verify(setRepository).findAll();
    }

    @Test
    void findByIdTest() {
        Assertions.assertDoesNotThrow(() -> defaultSetServiceImpl.findById(NumberUtils.LONG_ONE));
        Mockito.verify(setRepository).findById(NumberUtils.LONG_ONE);
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
        Assertions.assertDoesNotThrow(() -> defaultSetServiceImpl.deleteById(NumberUtils.LONG_ONE));
        Mockito.verify(setRepository).deleteById(NumberUtils.LONG_ONE);
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
        final SetModel setModel = new SetModel();
        Assertions.assertDoesNotThrow(() -> defaultSetServiceImpl.save(setModel));
        Mockito.verify(setRepository).save(setModel);
    }

    @Test
    void saveNullTest() {
        assertException(IllegalArgumentException.class, SET_MODEL_NOT_NULL_MESSAGE, () -> defaultSetServiceImpl.save(null));
        Mockito.verify(setRepository, Mockito.never()).save(null);
    }

    private void assertFindByInvalidId(final String message, final Long id) {
        assertException(IllegalArgumentException.class, message, () -> defaultSetServiceImpl.findById(id));
        Mockito.verify(setRepository, Mockito.never()).findById(id);
    }

    private void assertDeleteByInvalidId(final String message, final Long id) {
        assertException(IllegalArgumentException.class, message, () -> defaultSetServiceImpl.deleteById(id));
        Mockito.verify(setRepository, Mockito.never()).deleteById(id);
    }

}
