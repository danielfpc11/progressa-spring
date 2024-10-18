package progressa.progressaspring.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import progressa.progressaspring.BaseTest;
import progressa.progressaspring.datas.ExerciseData;
import progressa.progressaspring.datas.ExerciseTypeData;
import progressa.progressaspring.datas.SetData;
import progressa.progressaspring.datas.WorkoutData;
import progressa.progressaspring.populators.BasePopulator;

import java.util.List;

/**
 * @author danielfpc11@gmail.com
 */
@ExtendWith(MockitoExtension.class)
public class PopulatorUtilsTest extends BaseTest {

    private static final String OBJECT_NOT_NULL_MESSAGE = "List must not be null.";

    @Mock
    private BasePopulator<ExerciseData, ExerciseData> exerciseDataPopulator;
    @Mock
    private BasePopulator<ExerciseTypeData, ExerciseTypeData> exerciseTypeDataPopulator;
    @Mock
    private BasePopulator<SetData, SetData> setDataPopulator;
    @Mock
    private BasePopulator<WorkoutData, WorkoutData> workoutDataPopulator;

    @Test
    public void populateExerciseDatasTest() {
        PopulatorUtils.populateExerciseDatas(new ExerciseData(), new ExerciseData(), List.of(exerciseDataPopulator));
        Mockito.verify(exerciseDataPopulator).populate(Mockito.any(ExerciseData.class), Mockito.any(ExerciseData.class));
    }

    @Test
    public void populateExerciseDatasNullTest() {
        assertException(IllegalArgumentException.class, OBJECT_NOT_NULL_MESSAGE, () -> PopulatorUtils.populateExerciseDatas(null, null, null));
    }

    @Test
    public void populateExerciseTypeDatasTest() {
        PopulatorUtils.populateExerciseTypeDatas(new ExerciseTypeData(), new ExerciseTypeData(), List.of(exerciseTypeDataPopulator));
        Mockito.verify(exerciseTypeDataPopulator).populate(Mockito.any(ExerciseTypeData.class), Mockito.any(ExerciseTypeData.class));
    }

    @Test
    public void populateExerciseTypeDatasNullTest() {
        assertException(IllegalArgumentException.class, OBJECT_NOT_NULL_MESSAGE, () -> PopulatorUtils.populateExerciseTypeDatas(null, null, null));
    }

    @Test
    public void populateSetDatasTest() {
        PopulatorUtils.populateSetDatas(new SetData(), new SetData(), List.of(setDataPopulator));
        Mockito.verify(setDataPopulator).populate(Mockito.any(SetData.class), Mockito.any(SetData.class));
    }

    @Test
    public void populateSetDatasNullTest() {
        assertException(IllegalArgumentException.class, OBJECT_NOT_NULL_MESSAGE, () -> PopulatorUtils.populateSetDatas(null, null, null));
    }

    @Test
    public void populateWorkoutDatasTest() {
        PopulatorUtils.populateWorkoutDatas(new WorkoutData(), new WorkoutData(), List.of(workoutDataPopulator));
        Mockito.verify(workoutDataPopulator).populate(Mockito.any(WorkoutData.class), Mockito.any(WorkoutData.class));
    }

    @Test
    public void populateWorkoutDatasNullTest() {
        assertException(IllegalArgumentException.class, OBJECT_NOT_NULL_MESSAGE, () -> PopulatorUtils.populateWorkoutDatas(null, null, null));
    }

}
