package progressa.progressaspring.utils;

import progressa.progressaspring.datas.ExerciseData;
import progressa.progressaspring.datas.ExerciseTypeData;
import progressa.progressaspring.datas.SetData;
import progressa.progressaspring.datas.WorkoutData;
import progressa.progressaspring.populators.BasePopulator;

import java.util.List;

/**
 * @author danielfpc11@gmail.com
 */
public class PopulatorUtils {

    public static void populateExerciseDatas(final ExerciseData exerciseDataSource,
                                             final ExerciseData exerciseDataTarget,
                                             final List<BasePopulator<ExerciseData, ExerciseData>> exercisePopulators) {
        exercisePopulators.forEach(exercisePopulator -> exercisePopulator.populate(exerciseDataSource, exerciseDataTarget));
    }

    public static void populateExerciseTypeDatas(final ExerciseTypeData exerciseTypeDataSource,
                                                 final ExerciseTypeData exerciseTypeDataTarget,
                                                 final List<BasePopulator<ExerciseTypeData, ExerciseTypeData>> exerciseTypePopulators) {
        exerciseTypePopulators.forEach(exerciseTypePopulator -> exerciseTypePopulator.populate(exerciseTypeDataSource, exerciseTypeDataTarget));
    }

    public static void populateSetDatas(final SetData setDataSource,
                                        final SetData setDataTarget,
                                        final List<BasePopulator<SetData, SetData>> setPopulators) {
        setPopulators.forEach(setPopulator -> setPopulator.populate(setDataSource, setDataTarget));
    }

    public static void populateWorkoutDatas(final WorkoutData workoutDataSource,
                                            final WorkoutData workoutDataTarget,
                                            final List<BasePopulator<WorkoutData, WorkoutData>> workoutPopulators) {
        workoutPopulators.forEach(workoutPopulator -> workoutPopulator.populate(workoutDataSource, workoutDataTarget));
    }

}
