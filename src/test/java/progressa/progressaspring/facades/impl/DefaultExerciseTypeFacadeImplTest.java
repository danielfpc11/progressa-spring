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
import progressa.progressaspring.datas.ExerciseTypeData;
import progressa.progressaspring.models.ExerciseTypeModel;
import progressa.progressaspring.services.ExerciseTypeService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
@ExtendWith(MockitoExtension.class)
public class DefaultExerciseTypeFacadeImplTest extends BaseTest {

    @InjectMocks
    private DefaultExerciseTypeFacadeImpl defaultExerciseTypeFacadeImpl;

    @Mock
    private ExerciseTypeService exerciseTypeService;
    @Mock
    private Converter<ExerciseTypeModel, ExerciseTypeData> exerciseTypeConverter;
    @Mock
    private Converter<ExerciseTypeData, ExerciseTypeModel> exerciseTypeReverseConverter;

    private ExerciseTypeModel exerciseTypeModel;
    private ExerciseTypeData exerciseTypeData;

    @BeforeEach
    void setUp() {
        exerciseTypeModel = new ExerciseTypeModel();
        exerciseTypeData = new ExerciseTypeData();
    }

    @Test
    void findAllTest() {
        Mockito.when(exerciseTypeService.findAll()).thenReturn(List.of(exerciseTypeModel));
        defaultExerciseTypeFacadeImpl.findAll();
        Mockito.verify(exerciseTypeService).findAll();
        Mockito.verify(exerciseTypeConverter).convert(exerciseTypeModel);
    }

    @Test
    void findAllEmptyTest() {
        Mockito.when(exerciseTypeService.findAll()).thenReturn(Collections.emptyList());
        defaultExerciseTypeFacadeImpl.findAll();
        Mockito.verify(exerciseTypeService).findAll();
        Mockito.verify(exerciseTypeConverter, Mockito.never()).convert(exerciseTypeModel);
    }

    @Test
    void findByIdTest() {
        Mockito.when(exerciseTypeService.findById(NumberUtils.LONG_ONE)).thenReturn(Optional.of(exerciseTypeModel));
        defaultExerciseTypeFacadeImpl.findById(NumberUtils.LONG_ONE);
        Mockito.verify(exerciseTypeService).findById(NumberUtils.LONG_ONE);
        Mockito.verify(exerciseTypeConverter).convert(exerciseTypeModel);
    }

    @Test
    void findByIdNullTest() {
        Mockito.doThrow(IllegalArgumentException.class).when(exerciseTypeService).findById(null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> defaultExerciseTypeFacadeImpl.findById(null));
        Mockito.verify(exerciseTypeService).findById(null);
        Mockito.verify(exerciseTypeConverter, Mockito.never()).convert(Mockito.any(ExerciseTypeModel.class));
    }

    @Test
    void deleteByIdTest() {
        Mockito.doNothing().when(exerciseTypeService).deleteById(NumberUtils.LONG_ONE);
        defaultExerciseTypeFacadeImpl.deleteById(NumberUtils.LONG_ONE);
        Mockito.verify(exerciseTypeService).deleteById(NumberUtils.LONG_ONE);
    }

    @Test
    void deleteByIdNullTest() {
        Mockito.doThrow(IllegalArgumentException.class).when(exerciseTypeService).deleteById(null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> defaultExerciseTypeFacadeImpl.deleteById(null));
        Mockito.verify(exerciseTypeService).deleteById(null);
    }

    @Test
    void saveTest() {
        Mockito.when(exerciseTypeService.save(exerciseTypeModel)).thenReturn(exerciseTypeModel);
        Mockito.when(exerciseTypeConverter.convert(exerciseTypeModel)).thenReturn(exerciseTypeData);
        Mockito.when(exerciseTypeReverseConverter.convert(exerciseTypeData)).thenReturn(exerciseTypeModel);
        defaultExerciseTypeFacadeImpl.save(exerciseTypeData);
        Mockito.verify(exerciseTypeService).save(exerciseTypeModel);
        Mockito.verify(exerciseTypeConverter).convert(exerciseTypeModel);
        Mockito.verify(exerciseTypeReverseConverter).convert(exerciseTypeData);
    }

    @Test
    void saveNullTest() {
        Mockito.doThrow(IllegalArgumentException.class).when(exerciseTypeReverseConverter).convert(null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> defaultExerciseTypeFacadeImpl.save(null));
        Mockito.verify(exerciseTypeService, Mockito.never()).save(null);
        Mockito.verify(exerciseTypeConverter, Mockito.never()).convert(null);
        Mockito.verify(exerciseTypeReverseConverter).convert(null);
    }

}
