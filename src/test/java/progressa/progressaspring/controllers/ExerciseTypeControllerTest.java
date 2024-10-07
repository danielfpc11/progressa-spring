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
import progressa.progressaspring.datas.ExerciseTypeData;
import progressa.progressaspring.facades.ExerciseTypeFacade;
import progressa.progressaspring.populators.BasePopulator;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
@WebMvcTest
@ContextConfiguration(classes = ExerciseTypeController.class)
public class ExerciseTypeControllerTest extends BaseControllerTest {

    private static final String EXERCISE_TYPE_BASE_ENDPOINT = "/exercise-type";
    private static final String EXERCISE_TYPE_NAME = "Exercise Type Name";

    @MockBean
    private ExerciseTypeFacade exerciseTypeFacade;
    @MockBean
    private BasePopulator<ExerciseTypeData, ExerciseTypeData> exerciseTypeDataPopulator;

    private ExerciseTypeData exerciseTypeData;
    private List<ExerciseTypeData> exerciseTypeDatas;

    @BeforeEach
    void setUp() {
        exerciseTypeData = ExerciseTypeData.builder()
                                           .id(NumberUtils.LONG_ONE)
                                           .name(EXERCISE_TYPE_NAME)
                                           .exerciseDatas(Collections.emptyList())
                                           .build();
        exerciseTypeDatas = List.of(exerciseTypeData);
    }

    @Test
    void findAllTest() throws Exception {
        Mockito.when(exerciseTypeFacade.findAll()).thenReturn(exerciseTypeDatas);
        final CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, ExerciseTypeData.class);
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(EXERCISE_TYPE_BASE_ENDPOINT + FIND_ALL_ENDPOINT));
        final List<ExerciseTypeData> responseExerciseDatas = objectMapper.readValue(getJson(resultActions), collectionType);

        Mockito.verify(exerciseTypeFacade).findAll();
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        Assertions.assertNotNull(responseExerciseDatas);
        Assertions.assertFalse(responseExerciseDatas.isEmpty());
        Assertions.assertEquals(exerciseTypeDatas, responseExerciseDatas);
    }

    @Test
    void findByIdTest() throws Exception {
        Mockito.when(exerciseTypeFacade.findById(NumberUtils.LONG_ONE)).thenReturn(Optional.of(exerciseTypeData));
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(EXERCISE_TYPE_BASE_ENDPOINT + FIND_BY_ID_ENDPOINT + NumberUtils.LONG_ONE));
        final ExerciseTypeData responseExerciseData = objectMapper.readValue(getJson(resultActions), ExerciseTypeData.class);

        Mockito.verify(exerciseTypeFacade).findById(NumberUtils.LONG_ONE);
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        Assertions.assertNotNull(responseExerciseData);
        Assertions.assertEquals(exerciseTypeData, responseExerciseData);
    }

    @Test
    void findByIdNullTest() {
        Mockito.when(exerciseTypeFacade.findById(NumberUtils.LONG_ONE)).thenReturn(Optional.empty());
        assertServletException(NoSuchElementException.class, () -> mockMvc.perform(MockMvcRequestBuilders.get(EXERCISE_TYPE_BASE_ENDPOINT + FIND_BY_ID_ENDPOINT + NumberUtils.LONG_ONE)));
        Mockito.verify(exerciseTypeFacade).findById(NumberUtils.LONG_ONE);
    }

    @Test
    void saveNewTest() throws Exception {
        Mockito.when(exerciseTypeFacade.save(exerciseTypeData)).thenReturn(exerciseTypeData);
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(EXERCISE_TYPE_BASE_ENDPOINT + SAVE_NEW_ENDPOINT)
                                                                                  .contentType(MediaType.APPLICATION_JSON)
                                                                                  .content(objectMapper.writeValueAsString(exerciseTypeData)));
        Mockito.verify(exerciseTypeFacade).save(exerciseTypeData);
        resultActions.andExpectAll(MockMvcResultMatchers.status().isCreated(),
                                   MockMvcResultMatchers.content().bytes(ArrayUtils.EMPTY_BYTE_ARRAY));
    }

    @Test
    void saveNewNullTest() throws Exception {
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(EXERCISE_TYPE_BASE_ENDPOINT + SAVE_NEW_ENDPOINT)
                                                                                  .contentType(MediaType.APPLICATION_JSON)
                                                                                  .content(StringUtils.EMPTY));
        Mockito.verify(exerciseTypeFacade, Mockito.never()).save(exerciseTypeData);
        resultActions.andExpectAll(MockMvcResultMatchers.status().isBadRequest(),
                                   MockMvcResultMatchers.content().bytes(ArrayUtils.EMPTY_BYTE_ARRAY));
    }

    @Test
    void saveUpdateTest() throws Exception {
        Mockito.when(exerciseTypeFacade.findById(NumberUtils.LONG_ONE)).thenReturn(Optional.of(exerciseTypeData));
        Mockito.when(exerciseTypeFacade.save(exerciseTypeData)).thenReturn(exerciseTypeData);
        Mockito.doNothing().when(exerciseTypeDataPopulator).populate(exerciseTypeData, exerciseTypeData);
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put(EXERCISE_TYPE_BASE_ENDPOINT + SAVE_UPDATE_ENDPOINT + NumberUtils.LONG_ONE)
                                                                                  .contentType(MediaType.APPLICATION_JSON)
                                                                                  .content(objectMapper.writeValueAsString(exerciseTypeData)));
        Mockito.verify(exerciseTypeFacade).findById(NumberUtils.LONG_ONE);
        Mockito.verify(exerciseTypeFacade).save(exerciseTypeData);
        Mockito.verify(exerciseTypeDataPopulator).populate(exerciseTypeData, exerciseTypeData);
        resultActions.andExpectAll(MockMvcResultMatchers.status().isOk(),
                                   MockMvcResultMatchers.content().bytes(ArrayUtils.EMPTY_BYTE_ARRAY));
    }

    @Test
    void saveUpdateNotFoundTest() {
        Mockito.when(exerciseTypeFacade.findById(NumberUtils.LONG_ONE)).thenReturn(Optional.empty());
        assertServletException(NoSuchElementException.class, () -> mockMvc.perform(MockMvcRequestBuilders.put(EXERCISE_TYPE_BASE_ENDPOINT + SAVE_UPDATE_ENDPOINT + NumberUtils.LONG_ONE)
                                                                                                         .contentType(MediaType.APPLICATION_JSON)
                                                                                                         .content(objectMapper.writeValueAsString(exerciseTypeData))));
        Mockito.verify(exerciseTypeFacade).findById(NumberUtils.LONG_ONE);
    }

    @Test
    void saveUpdateNullBodyTest() throws Exception {
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put(EXERCISE_TYPE_BASE_ENDPOINT + SAVE_UPDATE_ENDPOINT + NumberUtils.LONG_ONE)
                                                                                  .contentType(MediaType.APPLICATION_JSON)
                                                                                  .content(StringUtils.EMPTY));
        Mockito.verify(exerciseTypeFacade, Mockito.never()).save(exerciseTypeData);
        resultActions.andExpectAll(MockMvcResultMatchers.status().isBadRequest(),
                                   MockMvcResultMatchers.content().bytes(ArrayUtils.EMPTY_BYTE_ARRAY));
    }

    @Test
    void deleteByIdTest() throws Exception {
        Mockito.doNothing().when(exerciseTypeFacade).deleteById(NumberUtils.LONG_ONE);
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete(EXERCISE_TYPE_BASE_ENDPOINT + DELETE_BY_ID_ENDPOINT + NumberUtils.LONG_ONE));
        Mockito.verify(exerciseTypeFacade).deleteById(NumberUtils.LONG_ONE);
        resultActions.andExpectAll(MockMvcResultMatchers.status().isOk(),
                                   MockMvcResultMatchers.content().bytes(ArrayUtils.EMPTY_BYTE_ARRAY));
    }

    @Test
    void deleteByIdNullTest() {
        Mockito.doThrow(IllegalArgumentException.class).when(exerciseTypeFacade).deleteById(NumberUtils.LONG_ONE);
        assertServletException(IllegalArgumentException.class, () -> mockMvc.perform(MockMvcRequestBuilders.delete(EXERCISE_TYPE_BASE_ENDPOINT + DELETE_BY_ID_ENDPOINT + NumberUtils.LONG_ONE)));
        Mockito.verify(exerciseTypeFacade).deleteById(NumberUtils.LONG_ONE);
    }

}
