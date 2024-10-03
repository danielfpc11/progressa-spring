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
import progressa.progressaspring.datas.WorkoutData;
import progressa.progressaspring.models.WorkoutModel;
import progressa.progressaspring.services.WorkoutService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
@ExtendWith(MockitoExtension.class)
public class DefaultWorkoutFacadeImplTest extends BaseTest {

    @InjectMocks
    private DefaultWorkoutFacadeImpl defaultWorkoutFacadeImpl;

    @Mock
    private WorkoutService workoutService;
    @Mock
    private Converter<WorkoutModel, WorkoutData> workoutConverter;
    @Mock
    private Converter<WorkoutData, WorkoutModel> workoutReverseConverter;

    private WorkoutModel workoutModel;
    private WorkoutData workoutData;

    @BeforeEach
    void setUp() {
        workoutModel = new WorkoutModel();
        workoutData = new WorkoutData();
    }

    @Test
    void findAllTest() {
        Mockito.when(workoutService.findAll()).thenReturn(List.of(workoutModel));
        defaultWorkoutFacadeImpl.findAll();
        Mockito.verify(workoutService).findAll();
        Mockito.verify(workoutConverter).convert(workoutModel);
    }

    @Test
    void findAllEmptyTest() {
        Mockito.when(workoutService.findAll()).thenReturn(Collections.emptyList());
        defaultWorkoutFacadeImpl.findAll();
        Mockito.verify(workoutService).findAll();
        Mockito.verify(workoutConverter, Mockito.never()).convert(workoutModel);
    }

    @Test
    void findByIdTest() {
        Mockito.when(workoutService.findById(NumberUtils.LONG_ONE)).thenReturn(Optional.of(workoutModel));
        defaultWorkoutFacadeImpl.findById(NumberUtils.LONG_ONE);
        Mockito.verify(workoutService).findById(NumberUtils.LONG_ONE);
        Mockito.verify(workoutConverter).convert(workoutModel);
    }

    @Test
    void findByIdNullTest() {
        Mockito.doThrow(IllegalArgumentException.class).when(workoutService).findById(null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> defaultWorkoutFacadeImpl.findById(null));
        Mockito.verify(workoutService).findById(null);
        Mockito.verify(workoutConverter, Mockito.never()).convert(Mockito.any(WorkoutModel.class));
    }

    @Test
    void deleteByIdTest() {
        Mockito.doNothing().when(workoutService).deleteById(NumberUtils.LONG_ONE);
        defaultWorkoutFacadeImpl.deleteById(NumberUtils.LONG_ONE);
        Mockito.verify(workoutService).deleteById(NumberUtils.LONG_ONE);
    }

    @Test
    void deleteByIdNullTest() {
        Mockito.doThrow(IllegalArgumentException.class).when(workoutService).deleteById(null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> defaultWorkoutFacadeImpl.deleteById(null));
        Mockito.verify(workoutService).deleteById(null);
    }

    @Test
    void saveTest() {
        Mockito.when(workoutService.save(workoutModel)).thenReturn(workoutModel);
        Mockito.when(workoutConverter.convert(workoutModel)).thenReturn(workoutData);
        Mockito.when(workoutReverseConverter.convert(workoutData)).thenReturn(workoutModel);
        defaultWorkoutFacadeImpl.save(workoutData);
        Mockito.verify(workoutService).save(workoutModel);
        Mockito.verify(workoutConverter).convert(workoutModel);
        Mockito.verify(workoutReverseConverter).convert(workoutData);
    }

    @Test
    void saveNullTest() {
        Mockito.doThrow(IllegalArgumentException.class).when(workoutReverseConverter).convert(null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> defaultWorkoutFacadeImpl.save(null));
        Mockito.verify(workoutService, Mockito.never()).save(null);
        Mockito.verify(workoutConverter, Mockito.never()).convert(null);
        Mockito.verify(workoutReverseConverter).convert(null);
    }

}
