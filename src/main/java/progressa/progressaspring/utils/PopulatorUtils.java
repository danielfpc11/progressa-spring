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

    /**
     * Populates the target {@link ExerciseData} object with data from the source {@link ExerciseData} object
     * using a list of {@link BasePopulator} instances.
     *
     * @param exerciseDataSource the source {@link ExerciseData} object from which data will be copied
     * @param exerciseDataTarget the target {@link ExerciseData} object to which data will be copied
     * @param exercisePopulators a list of {@link BasePopulator} instances used to populate the target object.
     */
    public static void populateExerciseDatas(final ExerciseData exerciseDataSource,
                                             final ExerciseData exerciseDataTarget,
                                             final List<BasePopulator<ExerciseData, ExerciseData>> exercisePopulators) {
        AssertUtils.notNull(exercisePopulators, List.class);
        exercisePopulators.forEach(exercisePopulator -> exercisePopulator.populate(exerciseDataSource, exerciseDataTarget));
    }

    /**
     * Populates the target {@link ExerciseTypeData} object with data from the source {@link ExerciseTypeData} object
     * using a list of {@link BasePopulator} instances.
     *
     * @param exerciseTypeDataSource the source {@link ExerciseTypeData} object from which data will be copied
     * @param exerciseTypeDataTarget the target {@link ExerciseTypeData} object to which data will be copied
     * @param exerciseTypePopulators a list of {@link BasePopulator} instances used to populate the target object.
     */
    public static void populateExerciseTypeDatas(final ExerciseTypeData exerciseTypeDataSource,
                                                 final ExerciseTypeData exerciseTypeDataTarget,
                                                 final List<BasePopulator<ExerciseTypeData, ExerciseTypeData>> exerciseTypePopulators) {
        AssertUtils.notNull(exerciseTypePopulators, List.class);
        exerciseTypePopulators.forEach(exerciseTypePopulator -> exerciseTypePopulator.populate(exerciseTypeDataSource, exerciseTypeDataTarget));
    }

    /**
     * Populates the target {@link SetData} object with data from the source {@link SetData} object
     * using a list of {@link BasePopulator} instances.
     *
     * @param setDataSource the source {@link SetData} object from which data will be copied
     * @param setDataTarget the target {@link SetData} object to which data will be copied
     * @param setPopulators a list of {@link BasePopulator} instances used to populate the target object.
     */
    public static void populateSetDatas(final SetData setDataSource,
                                        final SetData setDataTarget,
                                        final List<BasePopulator<SetData, SetData>> setPopulators) {
        AssertUtils.notNull(setPopulators, List.class);
        setPopulators.forEach(setPopulator -> setPopulator.populate(setDataSource, setDataTarget));
    }

    /**
     * Populates the target {@link WorkoutData} object with data from the source {@link WorkoutData} object
     * using a list of {@link BasePopulator} instances.
     *
     * @param workoutDataSource the source {@link WorkoutData} object from which data will be copied
     * @param workoutDataTarget the target {@link WorkoutData} object to which data will be copied
     * @param workoutPopulators a list of {@link BasePopulator} instances used to populate the target object.
     */
    public static void populateWorkoutDatas(final WorkoutData workoutDataSource,
                                            final WorkoutData workoutDataTarget,
                                            final List<BasePopulator<WorkoutData, WorkoutData>> workoutPopulators) {
        AssertUtils.notNull(workoutPopulators, List.class);
        workoutPopulators.forEach(workoutPopulator -> workoutPopulator.populate(workoutDataSource, workoutDataTarget));
    }

}
