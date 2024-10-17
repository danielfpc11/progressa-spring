package progressa.progressaspring.controllers;

import com.fasterxml.jackson.databind.type.CollectionType;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import progressa.progressaspring.datas.SetData;
import progressa.progressaspring.facades.SetFacade;
import progressa.progressaspring.populators.BasePopulator;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
@WebMvcTest
@ContextConfiguration(classes = SetController.class)
public class SetControllerTest extends BaseControllerTest {

    private static final int POPULATORS_FOR_PATCH = 1;
    private static final int POPULATORS_FOR_UPDATE = 2;
    private static final String SET_BASE_ENDPOINT = "/set";

    @MockBean
    private SetFacade setFacade;
    @MockBean
    private BasePopulator<SetData, SetData> setDataPopulator;

    private SetData setData;
    private List<SetData> setDatas;

    @BeforeEach
    void setUp() {
        setData = SetData.builder()
                         .id(NumberUtils.LONG_ONE)
                         .number(NumberUtils.INTEGER_ONE)
                         .weight(NumberUtils.FLOAT_ONE)
                         .repetitions(NumberUtils.INTEGER_ONE)
                         .rir(NumberUtils.INTEGER_ONE)
                         .exerciseId(NumberUtils.LONG_ONE)
                         .build();
        setDatas = List.of(setData);
    }

    @Test
    void findAllTest() throws Exception {
        Mockito.when(setFacade.findAll()).thenReturn(setDatas);
        final CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, SetData.class);
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(SET_BASE_ENDPOINT + FIND_ALL_ENDPOINT));
        final List<SetData> responseExerciseDatas = objectMapper.readValue(getJson(resultActions), collectionType);

        Mockito.verify(setFacade).findAll();
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        Assertions.assertNotNull(responseExerciseDatas);
        Assertions.assertFalse(responseExerciseDatas.isEmpty());
        Assertions.assertEquals(setDatas, responseExerciseDatas);
    }

    @Test
    void findByIdTest() throws Exception {
        Mockito.when(setFacade.findById(NumberUtils.LONG_ONE)).thenReturn(Optional.of(setData));
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(SET_BASE_ENDPOINT + FIND_BY_ID_ENDPOINT + NumberUtils.LONG_ONE));
        final SetData responseExerciseData = objectMapper.readValue(getJson(resultActions), SetData.class);

        Mockito.verify(setFacade).findById(NumberUtils.LONG_ONE);
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        Assertions.assertNotNull(responseExerciseData);
        Assertions.assertEquals(setData, responseExerciseData);
    }

    @Test
    void findByIdNullTest() {
        Mockito.when(setFacade.findById(NumberUtils.LONG_ONE)).thenReturn(Optional.empty());
        assertServletException(NoSuchElementException.class, () -> mockMvc.perform(MockMvcRequestBuilders.get(SET_BASE_ENDPOINT + FIND_BY_ID_ENDPOINT + NumberUtils.LONG_ONE)));
        Mockito.verify(setFacade).findById(NumberUtils.LONG_ONE);
    }

    @Test
    void saveNewTest() throws Exception {
        setData.setId(null);
        Mockito.when(setFacade.save(setData)).thenReturn(setData);
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(SET_BASE_ENDPOINT + SAVE_NEW_ENDPOINT)
                                                                                  .contentType(MediaType.APPLICATION_JSON)
                                                                                  .content(objectMapper.writeValueAsString(setData)));
        Mockito.verify(setFacade).save(setData);
        resultActions.andExpectAll(MockMvcResultMatchers.status().isCreated(),
                                   MockMvcResultMatchers.content().bytes(ArrayUtils.EMPTY_BYTE_ARRAY));
    }

    @Test
    void saveNewNullTest() throws Exception {
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(SET_BASE_ENDPOINT + SAVE_NEW_ENDPOINT)
                                                                                  .contentType(MediaType.APPLICATION_JSON)
                                                                                  .content(StringUtils.EMPTY));
        Mockito.verify(setFacade, Mockito.never()).save(setData);
        resultActions.andExpectAll(MockMvcResultMatchers.status().isBadRequest(),
                                   MockMvcResultMatchers.content().bytes(ArrayUtils.EMPTY_BYTE_ARRAY));
    }

    @Test
    void saveUpdateTest() throws Exception {
        Mockito.when(setFacade.findById(NumberUtils.LONG_ONE)).thenReturn(Optional.of(setData));
        Mockito.when(setFacade.save(setData)).thenReturn(setData);
        Mockito.doNothing().when(setDataPopulator).populate(setData, setData);
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put(SET_BASE_ENDPOINT + SAVE_UPDATE_ENDPOINT + NumberUtils.LONG_ONE)
                                                                                  .contentType(MediaType.APPLICATION_JSON)
                                                                                  .content(objectMapper.writeValueAsString(setData)));
        Mockito.verify(setFacade).findById(NumberUtils.LONG_ONE);
        Mockito.verify(setFacade).save(setData);
        Mockito.verify(setDataPopulator, Mockito.times(POPULATORS_FOR_UPDATE)).populate(setData, setData);
        resultActions.andExpectAll(MockMvcResultMatchers.status().isOk(),
                                   MockMvcResultMatchers.content().bytes(ArrayUtils.EMPTY_BYTE_ARRAY));
    }

    @Test
    void saveUpdateNotFoundTest() {
        Mockito.when(setFacade.findById(NumberUtils.LONG_ONE)).thenReturn(Optional.empty());
        assertServletException(NoSuchElementException.class, () -> mockMvc.perform(MockMvcRequestBuilders.put(SET_BASE_ENDPOINT + SAVE_UPDATE_ENDPOINT + NumberUtils.LONG_ONE)
                                                                                                         .contentType(MediaType.APPLICATION_JSON)
                                                                                                         .content(objectMapper.writeValueAsString(setData))));
        Mockito.verify(setFacade).findById(NumberUtils.LONG_ONE);
    }

    @Test
    void saveUpdateNullBodyTest() throws Exception {
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put(SET_BASE_ENDPOINT + SAVE_UPDATE_ENDPOINT + NumberUtils.LONG_ONE)
                                                                                  .contentType(MediaType.APPLICATION_JSON)
                                                                                  .content(StringUtils.EMPTY));
        Mockito.verify(setFacade, Mockito.never()).save(setData);
        resultActions.andExpectAll(MockMvcResultMatchers.status().isBadRequest(),
                                   MockMvcResultMatchers.content().bytes(ArrayUtils.EMPTY_BYTE_ARRAY));
    }

    @Test
    void savePatchTest() throws Exception {
        Mockito.when(setFacade.findById(NumberUtils.LONG_ONE)).thenReturn(Optional.of(setData));
        Mockito.when(setFacade.save(setData)).thenReturn(setData);
        Mockito.doNothing().when(setDataPopulator).populate(setData, setData);
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.patch(SET_BASE_ENDPOINT + SAVE_PATCH_ENDPOINT + NumberUtils.LONG_ONE)
                                                                                  .contentType(MediaType.APPLICATION_JSON)
                                                                                  .content(objectMapper.writeValueAsString(setData)));
        Mockito.verify(setFacade).findById(NumberUtils.LONG_ONE);
        Mockito.verify(setFacade).save(setData);
        Mockito.verify(setDataPopulator, Mockito.times(POPULATORS_FOR_PATCH)).populate(setData, setData);
        resultActions.andExpectAll(MockMvcResultMatchers.status().isOk(),
                                   MockMvcResultMatchers.content().bytes(ArrayUtils.EMPTY_BYTE_ARRAY));
    }

    @Test
    void savePatchNotFoundTest() {
        Mockito.when(setFacade.findById(NumberUtils.LONG_ONE)).thenReturn(Optional.empty());
        assertServletException(NoSuchElementException.class, () -> mockMvc.perform(MockMvcRequestBuilders.patch(SET_BASE_ENDPOINT + SAVE_PATCH_ENDPOINT + NumberUtils.LONG_ONE)
                                                                                                         .contentType(MediaType.APPLICATION_JSON)
                                                                                                         .content(objectMapper.writeValueAsString(setData))));
        Mockito.verify(setFacade).findById(NumberUtils.LONG_ONE);
    }

    @Test
    void savePatchNullBodyTest() throws Exception {
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.patch(SET_BASE_ENDPOINT + SAVE_PATCH_ENDPOINT + NumberUtils.LONG_ONE)
                                                                                  .contentType(MediaType.APPLICATION_JSON)
                                                                                  .content(StringUtils.EMPTY));
        Mockito.verify(setFacade, Mockito.never()).save(setData);
        resultActions.andExpectAll(MockMvcResultMatchers.status().isBadRequest(),
                                   MockMvcResultMatchers.content().bytes(ArrayUtils.EMPTY_BYTE_ARRAY));
    }

    @Test
    void deleteByIdTest() throws Exception {
        Mockito.doNothing().when(setFacade).deleteById(NumberUtils.LONG_ONE);
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete(SET_BASE_ENDPOINT + DELETE_BY_ID_ENDPOINT + NumberUtils.LONG_ONE));
        Mockito.verify(setFacade).deleteById(NumberUtils.LONG_ONE);
        resultActions.andExpectAll(MockMvcResultMatchers.status().isOk(),
                                   MockMvcResultMatchers.content().bytes(ArrayUtils.EMPTY_BYTE_ARRAY));
    }

    @Test
    void deleteByIdNullTest() {
        Mockito.doThrow(IllegalArgumentException.class).when(setFacade).deleteById(NumberUtils.LONG_ONE);
        assertServletException(IllegalArgumentException.class, () -> mockMvc.perform(MockMvcRequestBuilders.delete(SET_BASE_ENDPOINT + DELETE_BY_ID_ENDPOINT + NumberUtils.LONG_ONE)));
        Mockito.verify(setFacade).deleteById(NumberUtils.LONG_ONE);
    }

}
