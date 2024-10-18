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
import progressa.progressaspring.datas.ExerciseTypeData;
import progressa.progressaspring.facades.ExerciseTypeFacade;
import progressa.progressaspring.groups.exercisetype.CreateExerciseTypeGroup;
import progressa.progressaspring.groups.exercisetype.UpdateExerciseTypeGroup;
import progressa.progressaspring.populators.BasePopulator;
import progressa.progressaspring.utils.PopulatorUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
@Controller
@RequestMapping("/exercise-type")
public class ExerciseTypeController {

    @Resource
    private ExerciseTypeFacade exerciseTypeFacade;
    @Resource
    private BasePopulator<ExerciseTypeData, ExerciseTypeData> exerciseTypeDataPopulator;
    @Resource
    private BasePopulator<ExerciseTypeData, ExerciseTypeData> exerciseTypeDataRelationshipsPopulator;

    @GetMapping("/all")
    public ResponseEntity<List<ExerciseTypeData>> findAll() {
        return ResponseEntity.ok(exerciseTypeFacade.findAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ExerciseTypeData> findById(@PathVariable final Long id) {
        return exerciseTypeFacade.findById(id)
                                 .map(ResponseEntity::ok)
                                 .orElseThrow();
    }

    @PostMapping("/new")
    public ResponseEntity<Object> saveNew(@RequestBody @Validated({CreateExerciseTypeGroup.class}) final ExerciseTypeData exerciseTypeData) {
        return Optional.ofNullable(exerciseTypeData)
                       .map(exerciseType -> exerciseTypeFacade.save(exerciseType))
                       .map(exerciseType -> ResponseEntity.status(HttpStatus.CREATED).build())
                       .orElseThrow();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> saveUpdate(@PathVariable final Long id,
                                             @RequestBody @Validated({UpdateExerciseTypeGroup.class}) final ExerciseTypeData exerciseTypeData) {
        return saveUpdate(id, exerciseTypeData, List.of(exerciseTypeDataPopulator, exerciseTypeDataRelationshipsPopulator));
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<Object> savePatch(@PathVariable final Long id,
                                            @RequestBody @Valid final ExerciseTypeData exerciseTypeData) {
        return saveUpdate(id, exerciseTypeData, List.of(exerciseTypeDataPopulator));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable final Long id) {
        exerciseTypeFacade.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<Object> saveUpdate(final Long id,
                                              final ExerciseTypeData exerciseTypeData,
                                              final List<BasePopulator<ExerciseTypeData, ExerciseTypeData>> exerciseTypeDataPopulators) {
        return exerciseTypeFacade.findById(id)
                                 .map(exerciseTypeDataFound -> {
                                     PopulatorUtils.populateExerciseTypeDatas(exerciseTypeData, exerciseTypeDataFound, exerciseTypeDataPopulators);
                                     return exerciseTypeFacade.save(exerciseTypeDataFound);
                                 })
                                 .map(exerciseTypeDataFound -> ResponseEntity.ok().build())
                                 .orElseThrow();
    }

}
