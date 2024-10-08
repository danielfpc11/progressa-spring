package progressa.progressaspring.services.impl;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import progressa.progressaspring.models.ExerciseModel;
import progressa.progressaspring.models.ExerciseTypeModel;
import progressa.progressaspring.models.WorkoutModel;
import progressa.progressaspring.services.JpaBidirectionalService;

/**
 * @author danielfpc11@gmail.com
 */
@Service
public class DefaultJpaBidirectionalServiceImpl implements JpaBidirectionalService {

    @Override
    public void setWorkoutRelationships(@NonNull final WorkoutModel workoutModel) {
        workoutModel.setExerciseModels(workoutModel.getExerciseModels()
                                                   .stream()
                                                   .peek(exerciseModel ->  {
                                                       exerciseModel.setWorkoutModel(workoutModel);
                                                       setExerciseRelationships(exerciseModel);
                                                   })
                                                   .toList());
    }

    @Override
    public void setExerciseRelationships(@NonNull final ExerciseModel exerciseModel) {
        exerciseModel.setSetModels(exerciseModel.getSetModels()
                                                .stream()
                                                .peek(setModel -> setModel.setExerciseModel(exerciseModel))
                                                .toList());
    }

    @Override
    public void setExerciseTypeRelationships(@NonNull final ExerciseTypeModel exerciseTypeModel) {
        exerciseTypeModel.setExerciseModels(exerciseTypeModel.getExerciseModels()
                                                             .stream()
                                                             .peek(exerciseModel -> {
                                                                 exerciseModel.setExerciseTypeModel(exerciseTypeModel);
                                                                 setExerciseRelationships(exerciseModel);
                                                             })
                                                             .toList());
    }

}
