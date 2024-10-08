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
import progressa.progressaspring.datas.WorkoutData;
import progressa.progressaspring.facades.WorkoutFacade;
import progressa.progressaspring.populators.BasePopulator;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
@WebMvcTest
@ContextConfiguration(classes = WorkoutController.class)
public class WorkoutControllerTest extends BaseControllerTest {

    private static final String WORKOUT_BASE_ENDPOINT = "/workout";

    @MockBean
    private WorkoutFacade workoutFacade;
    @MockBean
    private BasePopulator<WorkoutData, WorkoutData> workoutDataPopulator;

    private WorkoutData workoutData;
    private List<WorkoutData> workoutDatas;

    @BeforeEach
    void setUp() {
        workoutData = WorkoutData.builder()
                                 .id(NumberUtils.LONG_ONE)
                                 .date(new Date())
                                 .exerciseDatas(Collections.emptyList())
                                 .build();
        workoutDatas = List.of(workoutData);
    }

    @Test
    void findAllTest() throws Exception {
        Mockito.when(workoutFacade.findAll()).thenReturn(workoutDatas);
        final CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, WorkoutData.class);
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(WORKOUT_BASE_ENDPOINT + FIND_ALL_ENDPOINT));
        final List<WorkoutData> responseExerciseDatas = objectMapper.readValue(getJson(resultActions), collectionType);

        Mockito.verify(workoutFacade).findAll();
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        Assertions.assertNotNull(responseExerciseDatas);
        Assertions.assertFalse(responseExerciseDatas.isEmpty());
        Assertions.assertEquals(workoutDatas, responseExerciseDatas);
    }

    @Test
    void findByIdTest() throws Exception {
        Mockito.when(workoutFacade.findById(NumberUtils.LONG_ONE)).thenReturn(Optional.of(workoutData));
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(WORKOUT_BASE_ENDPOINT + FIND_BY_ID_ENDPOINT + NumberUtils.LONG_ONE));
        final WorkoutData responseExerciseData = objectMapper.readValue(getJson(resultActions), WorkoutData.class);

        Mockito.verify(workoutFacade).findById(NumberUtils.LONG_ONE);
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        Assertions.assertNotNull(responseExerciseData);
        Assertions.assertEquals(workoutData, responseExerciseData);
    }

    @Test
    void findByIdNullTest() {
        Mockito.when(workoutFacade.findById(NumberUtils.LONG_ONE)).thenReturn(Optional.empty());
        assertServletException(NoSuchElementException.class, () -> mockMvc.perform(MockMvcRequestBuilders.get(WORKOUT_BASE_ENDPOINT + FIND_BY_ID_ENDPOINT + NumberUtils.LONG_ONE)));
        Mockito.verify(workoutFacade).findById(NumberUtils.LONG_ONE);
    }

    @Test
    void saveNewTest() throws Exception {
        Mockito.when(workoutFacade.save(workoutData)).thenReturn(workoutData);
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(WORKOUT_BASE_ENDPOINT + SAVE_NEW_ENDPOINT)
                                                                                  .contentType(MediaType.APPLICATION_JSON)
                                                                                  .content(objectMapper.writeValueAsString(workoutData)));
        Mockito.verify(workoutFacade).save(workoutData);
        resultActions.andExpectAll(MockMvcResultMatchers.status().isCreated(),
                                   MockMvcResultMatchers.content().bytes(ArrayUtils.EMPTY_BYTE_ARRAY));
    }

    @Test
    void saveNewNullTest() throws Exception {
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(WORKOUT_BASE_ENDPOINT + SAVE_NEW_ENDPOINT)
                                                                                  .contentType(MediaType.APPLICATION_JSON)
                                                                                  .content(StringUtils.EMPTY));
        Mockito.verify(workoutFacade, Mockito.never()).save(workoutData);
        resultActions.andExpectAll(MockMvcResultMatchers.status().isBadRequest(),
                                   MockMvcResultMatchers.content().bytes(ArrayUtils.EMPTY_BYTE_ARRAY));
    }

    @Test
    void saveUpdateTest() throws Exception {
        Mockito.when(workoutFacade.findById(NumberUtils.LONG_ONE)).thenReturn(Optional.of(workoutData));
        Mockito.when(workoutFacade.save(workoutData)).thenReturn(workoutData);
        Mockito.doNothing().when(workoutDataPopulator).populate(workoutData, workoutData);
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put(WORKOUT_BASE_ENDPOINT + SAVE_UPDATE_ENDPOINT + NumberUtils.LONG_ONE)
                                                                                  .contentType(MediaType.APPLICATION_JSON)
                                                                                  .content(objectMapper.writeValueAsString(workoutData)));
        Mockito.verify(workoutFacade).findById(NumberUtils.LONG_ONE);
        Mockito.verify(workoutFacade).save(workoutData);
        Mockito.verify(workoutDataPopulator).populate(workoutData, workoutData);
        resultActions.andExpectAll(MockMvcResultMatchers.status().isOk(),
                                   MockMvcResultMatchers.content().bytes(ArrayUtils.EMPTY_BYTE_ARRAY));
    }

    @Test
    void saveUpdateNotFoundTest() {
        Mockito.when(workoutFacade.findById(NumberUtils.LONG_ONE)).thenReturn(Optional.empty());
        assertServletException(NoSuchElementException.class, () -> mockMvc.perform(MockMvcRequestBuilders.put(WORKOUT_BASE_ENDPOINT + SAVE_UPDATE_ENDPOINT + NumberUtils.LONG_ONE)
                                                                                                         .contentType(MediaType.APPLICATION_JSON)
                                                                                                         .content(objectMapper.writeValueAsString(workoutData))));
        Mockito.verify(workoutFacade).findById(NumberUtils.LONG_ONE);
    }

    @Test
    void saveUpdateNullBodyTest() throws Exception {
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put(WORKOUT_BASE_ENDPOINT + SAVE_UPDATE_ENDPOINT + NumberUtils.LONG_ONE)
                                                                                  .contentType(MediaType.APPLICATION_JSON)
                                                                                  .content(StringUtils.EMPTY));
        Mockito.verify(workoutFacade, Mockito.never()).save(workoutData);
        resultActions.andExpectAll(MockMvcResultMatchers.status().isBadRequest(),
                                   MockMvcResultMatchers.content().bytes(ArrayUtils.EMPTY_BYTE_ARRAY));
    }

    @Test
    void deleteByIdTest() throws Exception {
        Mockito.doNothing().when(workoutFacade).deleteById(NumberUtils.LONG_ONE);
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete(WORKOUT_BASE_ENDPOINT + DELETE_BY_ID_ENDPOINT + NumberUtils.LONG_ONE));
        Mockito.verify(workoutFacade).deleteById(NumberUtils.LONG_ONE);
        resultActions.andExpectAll(MockMvcResultMatchers.status().isOk(),
                                   MockMvcResultMatchers.content().bytes(ArrayUtils.EMPTY_BYTE_ARRAY));
    }

    @Test
    void deleteByIdNullTest() {
        Mockito.doThrow(IllegalArgumentException.class).when(workoutFacade).deleteById(NumberUtils.LONG_ONE);
        assertServletException(IllegalArgumentException.class, () -> mockMvc.perform(MockMvcRequestBuilders.delete(WORKOUT_BASE_ENDPOINT + DELETE_BY_ID_ENDPOINT + NumberUtils.LONG_ONE)));
        Mockito.verify(workoutFacade).deleteById(NumberUtils.LONG_ONE);
    }

}
