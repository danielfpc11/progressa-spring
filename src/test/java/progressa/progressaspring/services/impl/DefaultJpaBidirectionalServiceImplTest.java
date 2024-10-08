package progressa.progressaspring.services.impl;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import progressa.progressaspring.models.ExerciseModel;
import progressa.progressaspring.models.ExerciseTypeModel;
import progressa.progressaspring.models.SetModel;
import progressa.progressaspring.models.WorkoutModel;

import java.util.Date;
import java.util.List;

/**
 * @author danielfpc11@gmail.com
 */
@ExtendWith(MockitoExtension.class)
public class DefaultJpaBidirectionalServiceImplTest {

    private static final String EXERCISE_TYPE_NAME = "Exercise Type Name";

    @InjectMocks
    private DefaultJpaBidirectionalServiceImpl defaultJpaBidirectionalServiceImpl;

    private WorkoutModel workoutModel;
    private ExerciseModel exerciseModel;
    private ExerciseTypeModel exerciseTypeModel;
    private SetModel setModel;

    @BeforeEach
    void setUp() {
        setModel = new SetModel();
        exerciseModel = ExerciseModel.builder()
                                     .setModels(List.of(setModel))
                                     .build();
        exerciseTypeModel = ExerciseTypeModel.builder()
                                             .id(NumberUtils.LONG_ONE)
                                             .name(EXERCISE_TYPE_NAME)
                                             .exerciseModels(List.of(exerciseModel))
                                             .build();
        workoutModel = WorkoutModel.builder()
                                   .date(new Date())
                                   .exerciseModels(List.of(exerciseModel))
                                   .build();
    }

    @Test
    void setWorkoutRelationshipsTest() {
        defaultJpaBidirectionalServiceImpl.setWorkoutRelationships(workoutModel);
        Assertions.assertNotNull(exerciseModel.getWorkoutModel());
        Assertions.assertEquals(workoutModel, exerciseModel.getWorkoutModel());
        Assertions.assertEquals(exerciseModel, setModel.getExerciseModel());
    }

    @Test
    void setExerciseRelationships() {
        defaultJpaBidirectionalServiceImpl.setExerciseRelationships(exerciseModel);
        Assertions.assertNotNull(setModel.getExerciseModel());
        Assertions.assertEquals(exerciseModel, setModel.getExerciseModel());
    }

    @Test
    void setExerciseTypeRelationships() {
        defaultJpaBidirectionalServiceImpl.setExerciseTypeRelationships(exerciseTypeModel);
        Assertions.assertNotNull(exerciseModel.getExerciseTypeModel());
        Assertions.assertEquals(exerciseTypeModel, exerciseModel.getExerciseTypeModel());
        Assertions.assertEquals(exerciseModel, setModel.getExerciseModel());
    }

}
