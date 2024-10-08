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
import progressa.progressaspring.datas.SetData;
import progressa.progressaspring.models.SetModel;
import progressa.progressaspring.services.SetService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
@ExtendWith(MockitoExtension.class)
public class DefaultSetFacadeImplTest extends BaseTest {

    @InjectMocks
    private DefaultSetFacadeImpl defaultSetFacadeImpl;

    @Mock
    private SetService setService;
    @Mock
    private Converter<SetModel, SetData> setConverter;
    @Mock
    private Converter<SetData, SetModel> setReverseConverter;

    private SetModel setModel;
    private SetData setData;

    @BeforeEach
    void setUp() {
        setModel = new SetModel();
        setData = new SetData();
    }

    @Test
    void findAllTest() {
        Mockito.when(setService.findAll()).thenReturn(List.of(setModel));
        defaultSetFacadeImpl.findAll();
        Mockito.verify(setService).findAll();
        Mockito.verify(setConverter).convert(setModel);
    }

    @Test
    void findAllEmptyTest() {
        Mockito.when(setService.findAll()).thenReturn(Collections.emptyList());
        defaultSetFacadeImpl.findAll();
        Mockito.verify(setService).findAll();
        Mockito.verify(setConverter, Mockito.never()).convert(Mockito.any(SetModel.class));
    }

    @Test
    void findByIdTest() {
        Mockito.when(setService.findById(NumberUtils.LONG_ONE)).thenReturn(Optional.of(setModel));
        defaultSetFacadeImpl.findById(NumberUtils.LONG_ONE);
        Mockito.verify(setService).findById(NumberUtils.LONG_ONE);
        Mockito.verify(setConverter).convert(setModel);
    }

    @Test
    void findByIdNullTest() {
        Mockito.doThrow(IllegalArgumentException.class).when(setService).findById(null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> defaultSetFacadeImpl.findById(null));
        Mockito.verify(setService).findById(null);
        Mockito.verify(setConverter, Mockito.never()).convert(Mockito.any(SetModel.class));
    }

    @Test
    void deleteByIdTest() {
        Mockito.doNothing().when(setService).deleteById(NumberUtils.LONG_ONE);
        defaultSetFacadeImpl.deleteById(NumberUtils.LONG_ONE);
        Mockito.verify(setService).deleteById(NumberUtils.LONG_ONE);
    }

    @Test
    void deleteByIdNullTest() {
        Mockito.doThrow(IllegalArgumentException.class).when(setService).deleteById(null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> defaultSetFacadeImpl.deleteById(null));
        Mockito.verify(setService).deleteById(null);
    }

    @Test
    void saveTest() {
        Mockito.when(setService.save(setModel)).thenReturn(setModel);
        Mockito.when(setConverter.convert(setModel)).thenReturn(setData);
        Mockito.when(setReverseConverter.convert(setData)).thenReturn(setModel);
        defaultSetFacadeImpl.save(setData);
        Mockito.verify(setService).save(setModel);
        Mockito.verify(setConverter).convert(setModel);
        Mockito.verify(setReverseConverter).convert(setData);
    }

    @Test
    void saveNullTest() {
        Mockito.doThrow(IllegalArgumentException.class).when(setReverseConverter).convert(null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> defaultSetFacadeImpl.save(null));
        Mockito.verify(setService, Mockito.never()).save(Mockito.any(SetModel.class));
        Mockito.verify(setConverter, Mockito.never()).convert(Mockito.any(SetModel.class));
        Mockito.verify(setReverseConverter).convert(null);
    }

}
