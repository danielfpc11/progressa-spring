package progressa.progressaspring.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import jakarta.annotation.Resource;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import progressa.progressaspring.datas.ExerciseData;
import progressa.progressaspring.facades.ExerciseFacade;
import progressa.progressaspring.populators.BasePopulator;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
@WebMvcTest
@ContextConfiguration(classes = ExerciseController.class)
public class ExerciseControllerTest extends BaseControllerTest {

    private static final String EXERCISE_BASE_ENDPOINT = "/exercise";
    private static final String EXERCISE_ENDPOINT_ALL = "/all";
    private static final String EXERCISE_ENDPOINT_BY_ID = "/get/";
    private static final String EXERCISE_ENDPOINT_NEW = "/new";
    private static final String EXERCISE_ENDPOINT_UPDATE = "/update/";
    private static final String EXERCISE_ENDPOINT_DELETE = "/delete/";
    private static final String EXERCISE_TYPE_NAME = "Exercise Type Name";

    @Resource
    private MockMvc mockMvc;
    @Resource
    private ObjectMapper objectMapper;

    @MockBean
    private ExerciseFacade exerciseFacade;
    @MockBean
    private BasePopulator<ExerciseData, ExerciseData> exerciseDataPopulator;

    private ExerciseData exerciseData;
    private List<ExerciseData> exerciseDatas;

    @BeforeEach
    void setUp() {
        exerciseData = ExerciseData.builder()
                                   .id(NumberUtils.LONG_ONE)
                                   .workoutId(NumberUtils.LONG_ONE)
                                   .exerciseTypeId(NumberUtils.LONG_ONE)
                                   .exerciseTypeName(EXERCISE_TYPE_NAME)
                                   .setDatas(Collections.emptyList())
                                   .build();
        exerciseDatas = List.of(exerciseData);
    }

    @Test
    void findAllTest() throws Exception {
        Mockito.when(exerciseFacade.findAll()).thenReturn(exerciseDatas);
        final CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, ExerciseData.class);
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(EXERCISE_BASE_ENDPOINT + EXERCISE_ENDPOINT_ALL));
        final List<ExerciseData> responseExerciseDatas = objectMapper.readValue(getJson(resultActions), collectionType);

        Mockito.verify(exerciseFacade).findAll();
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        Assertions.assertNotNull(responseExerciseDatas);
        Assertions.assertFalse(responseExerciseDatas.isEmpty());
        Assertions.assertEquals(exerciseDatas, responseExerciseDatas);
    }

    @Test
    void findByIdTest() throws Exception {
        Mockito.when(exerciseFacade.findById(NumberUtils.LONG_ONE)).thenReturn(Optional.of(exerciseData));
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(EXERCISE_BASE_ENDPOINT + EXERCISE_ENDPOINT_BY_ID + NumberUtils.LONG_ONE));
        final ExerciseData responseExerciseData = objectMapper.readValue(getJson(resultActions), ExerciseData.class);

        Mockito.verify(exerciseFacade).findById(NumberUtils.LONG_ONE);
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        Assertions.assertNotNull(responseExerciseData);
        Assertions.assertEquals(exerciseData, responseExerciseData);
    }

    @Test
    void findByIdNullTest() {
        Mockito.when(exerciseFacade.findById(NumberUtils.LONG_ONE)).thenReturn(Optional.empty());
        assertServletException(NoSuchElementException.class, () -> mockMvc.perform(MockMvcRequestBuilders.get(EXERCISE_BASE_ENDPOINT + EXERCISE_ENDPOINT_BY_ID + NumberUtils.LONG_ONE)));
        Mockito.verify(exerciseFacade).findById(NumberUtils.LONG_ONE);
    }

    @Test
    void saveNewTest() throws Exception {
        Mockito.when(exerciseFacade.save(exerciseData)).thenReturn(exerciseData);
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(EXERCISE_BASE_ENDPOINT + EXERCISE_ENDPOINT_NEW)
                                                                                  .contentType(MediaType.APPLICATION_JSON)
                                                                                  .content(objectMapper.writeValueAsString(exerciseData)));
        Mockito.verify(exerciseFacade).save(exerciseData);
        resultActions.andExpectAll(MockMvcResultMatchers.status().isCreated(),
                                   MockMvcResultMatchers.content().bytes(ArrayUtils.EMPTY_BYTE_ARRAY));
    }

    @Test
    void saveNewNullTest() throws Exception {
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(EXERCISE_BASE_ENDPOINT + EXERCISE_ENDPOINT_NEW)
                                                                                  .contentType(MediaType.APPLICATION_JSON)
                                                                                  .content(StringUtils.EMPTY));
        Mockito.verify(exerciseFacade, Mockito.never()).save(exerciseData);
        resultActions.andExpectAll(MockMvcResultMatchers.status().isBadRequest(),
                                   MockMvcResultMatchers.content().bytes(ArrayUtils.EMPTY_BYTE_ARRAY));
    }

    @Test
    void saveUpdateTest() throws Exception {
        Mockito.when(exerciseFacade.findById(NumberUtils.LONG_ONE)).thenReturn(Optional.of(exerciseData));
        Mockito.when(exerciseFacade.save(exerciseData)).thenReturn(exerciseData);
        Mockito.doNothing().when(exerciseDataPopulator).populate(exerciseData, exerciseData);
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put(EXERCISE_BASE_ENDPOINT + EXERCISE_ENDPOINT_UPDATE + NumberUtils.LONG_ONE)
                                                                                  .contentType(MediaType.APPLICATION_JSON)
                                                                                  .content(objectMapper.writeValueAsString(exerciseData)));
        Mockito.verify(exerciseFacade).findById(NumberUtils.LONG_ONE);
        Mockito.verify(exerciseFacade).save(exerciseData);
        Mockito.verify(exerciseDataPopulator).populate(exerciseData, exerciseData);
        resultActions.andExpectAll(MockMvcResultMatchers.status().isOk(),
                                   MockMvcResultMatchers.content().bytes(ArrayUtils.EMPTY_BYTE_ARRAY));
    }

    @Test
    void saveUpdateNotFoundTest() {
        Mockito.when(exerciseFacade.findById(NumberUtils.LONG_ONE)).thenReturn(Optional.empty());
        assertServletException(NoSuchElementException.class, () -> mockMvc.perform(MockMvcRequestBuilders.put(EXERCISE_BASE_ENDPOINT + EXERCISE_ENDPOINT_UPDATE + NumberUtils.LONG_ONE)
                                                                                                         .contentType(MediaType.APPLICATION_JSON)
                                                                                                         .content(objectMapper.writeValueAsString(exerciseData))));
        Mockito.verify(exerciseFacade).findById(NumberUtils.LONG_ONE);
    }

    @Test
    void saveUpdateNullBodyTest() throws Exception {
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put(EXERCISE_BASE_ENDPOINT + EXERCISE_ENDPOINT_UPDATE + NumberUtils.LONG_ONE)
                                                                                  .contentType(MediaType.APPLICATION_JSON)
                                                                                  .content(StringUtils.EMPTY));
        Mockito.verify(exerciseFacade, Mockito.never()).save(exerciseData);
        resultActions.andExpectAll(MockMvcResultMatchers.status().isBadRequest(),
                                   MockMvcResultMatchers.content().bytes(ArrayUtils.EMPTY_BYTE_ARRAY));
    }

    @Test
    void deleteByIdTest() throws Exception {
        Mockito.doNothing().when(exerciseFacade).deleteById(NumberUtils.LONG_ONE);
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete(EXERCISE_BASE_ENDPOINT + EXERCISE_ENDPOINT_DELETE + NumberUtils.LONG_ONE));
        Mockito.verify(exerciseFacade).deleteById(NumberUtils.LONG_ONE);
        resultActions.andExpectAll(MockMvcResultMatchers.status().isOk(),
                                   MockMvcResultMatchers.content().bytes(ArrayUtils.EMPTY_BYTE_ARRAY));
    }

    @Test
    void deleteByIdNullTest() {
        Mockito.doThrow(IllegalArgumentException.class).when(exerciseFacade).deleteById(NumberUtils.LONG_ONE);
        assertServletException(IllegalArgumentException.class, () -> mockMvc.perform(MockMvcRequestBuilders.delete(EXERCISE_BASE_ENDPOINT + EXERCISE_ENDPOINT_DELETE + NumberUtils.LONG_ONE)));
        Mockito.verify(exerciseFacade).deleteById(NumberUtils.LONG_ONE);
    }

}
