package progressa.progressaspring.facades.impl;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.converter.Converter;
import progressa.progressaspring.BaseTest;
import progressa.progressaspring.datas.ExerciseData;
import progressa.progressaspring.models.ExerciseModel;
import progressa.progressaspring.services.ExerciseService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
@ExtendWith(MockitoExtension.class)
public class DefaultExerciseFacadeImplTest extends BaseTest {

    @InjectMocks
    private DefaultExerciseFacadeImpl defaultExerciseFacadeImpl;

    @Mock
    private ExerciseService exerciseService;
    @Mock
    private Converter<ExerciseModel, ExerciseData> exerciseConverter;
    @Mock
    private Converter<ExerciseData, ExerciseModel> exerciseReverseConverter;

    private ExerciseModel exerciseModel;
    private ExerciseData exerciseData;

    @BeforeEach
    void setUp() {
        exerciseModel = new ExerciseModel();
        exerciseData = new ExerciseData();
    }

    @Test
    void findAllTest() {
        Mockito.when(exerciseService.findAll()).thenReturn(List.of(exerciseModel));
        defaultExerciseFacadeImpl.findAll();
        Mockito.verify(exerciseService).findAll();
        Mockito.verify(exerciseConverter).convert(exerciseModel);
    }

    @Test
    void findAllEmptyTest() {
        Mockito.when(exerciseService.findAll()).thenReturn(Collections.emptyList());
        defaultExerciseFacadeImpl.findAll();
        Mockito.verify(exerciseService).findAll();
        Mockito.verify(exerciseConverter, Mockito.never()).convert(Mockito.any(ExerciseModel.class));
    }

    @Test
    void findByIdTest() {
        Mockito.when(exerciseService.findById(NumberUtils.LONG_ONE)).thenReturn(Optional.of(exerciseModel));
        defaultExerciseFacadeImpl.findById(NumberUtils.LONG_ONE);
        Mockito.verify(exerciseService).findById(NumberUtils.LONG_ONE);
        Mockito.verify(exerciseConverter).convert(exerciseModel);
    }

    @Test
    void findByIdNullTest() {
        Mockito.doThrow(IllegalArgumentException.class).when(exerciseService).findById(null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> defaultExerciseFacadeImpl.findById(null));
        Mockito.verify(exerciseService).findById(null);
        Mockito.verify(exerciseConverter, Mockito.never()).convert(Mockito.any(ExerciseModel.class));
    }

    @Test
    void deleteByIdTest() {
        Mockito.doNothing().when(exerciseService).deleteById(NumberUtils.LONG_ONE);
        defaultExerciseFacadeImpl.deleteById(NumberUtils.LONG_ONE);
        Mockito.verify(exerciseService).deleteById(NumberUtils.LONG_ONE);
    }

    @Test
    void deleteByIdNullTest() {
        Mockito.doThrow(IllegalArgumentException.class).when(exerciseService).deleteById(null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> defaultExerciseFacadeImpl.deleteById(null));
        Mockito.verify(exerciseService).deleteById(null);
    }

    @Test
    void saveTest() {
        Mockito.when(exerciseService.save(exerciseModel)).thenReturn(exerciseModel);
        Mockito.when(exerciseConverter.convert(exerciseModel)).thenReturn(exerciseData);
        Mockito.when(exerciseReverseConverter.convert(exerciseData)).thenReturn(exerciseModel);
        defaultExerciseFacadeImpl.save(exerciseData);
        Mockito.verify(exerciseService).save(exerciseModel);
        Mockito.verify(exerciseConverter).convert(exerciseModel);
        Mockito.verify(exerciseReverseConverter).convert(exerciseData);
    }

    @Test
    void saveNullTest() {
        Mockito.doThrow(IllegalArgumentException.class).when(exerciseReverseConverter).convert(null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> defaultExerciseFacadeImpl.save(null));
        Mockito.verify(exerciseService, Mockito.never()).save(Mockito.any(ExerciseModel.class));
        Mockito.verify(exerciseConverter, Mockito.never()).convert(Mockito.any(ExerciseModel.class));
        Mockito.verify(exerciseReverseConverter).convert(null);
    }

}
