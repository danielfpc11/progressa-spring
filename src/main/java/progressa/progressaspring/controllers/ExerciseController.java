package progressa.progressaspring.controllers;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import progressa.progressaspring.datas.ExerciseData;
import progressa.progressaspring.facades.ExerciseFacade;
import progressa.progressaspring.groups.exercise.CreateExerciseGroup;
import progressa.progressaspring.groups.exercise.UpdateExerciseGroup;
import progressa.progressaspring.populators.BasePopulator;
import progressa.progressaspring.utils.PopulatorUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
@Controller
@RequestMapping("/exercise")
public class ExerciseController {

    @Resource
    private ExerciseFacade exerciseFacade;
    @Resource
    private BasePopulator<ExerciseData, ExerciseData> exerciseDataPopulator;
    @Resource
    private BasePopulator<ExerciseData, ExerciseData> exerciseDataRelationshipsPopulator;

    @GetMapping("/all")
    public ResponseEntity<List<ExerciseData>> findAll() {
        return ResponseEntity.ok(exerciseFacade.findAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ExerciseData> findById(@PathVariable final Long id) {
        return exerciseFacade.findById(id)
                             .map(ResponseEntity::ok)
                             .orElseThrow();
    }

    @PostMapping("/new")
    public ResponseEntity<Object> saveNew(@RequestBody @Validated({CreateExerciseGroup.class}) final ExerciseData exerciseData) {
        return Optional.ofNullable(exerciseData)
                       .map(exercise -> exerciseFacade.save(exercise))
                       .map(exercise -> ResponseEntity.status(HttpStatus.CREATED).build())
                       .orElseThrow();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> saveUpdate(@PathVariable final Long id,
                                             @RequestBody @Validated({UpdateExerciseGroup.class}) final ExerciseData exerciseData) {
       return saveUpdate(id, exerciseData, List.of(exerciseDataPopulator, exerciseDataRelationshipsPopulator));
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<Object> savePatch(@PathVariable final Long id,
                                            @RequestBody @Valid final ExerciseData exerciseData) {
        return saveUpdate(id, exerciseData, List.of(exerciseDataPopulator));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable final Long id) {
        exerciseFacade.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<Object> saveUpdate(final Long id,
                                              final ExerciseData exerciseData,
                                              final List<BasePopulator<ExerciseData, ExerciseData>> exerciseDataPopulators) {
        return exerciseFacade.findById(id)
                             .map(exerciseDataFound -> {
                                 PopulatorUtils.populateExerciseDatas(exerciseData, exerciseDataFound, exerciseDataPopulators);
                                 return exerciseFacade.save(exerciseDataFound);
                             })
                             .map(exerciseDataFound -> ResponseEntity.ok().build())
                             .orElseThrow();
    }

}
