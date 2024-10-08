package progressa.progressaspring.controllers;

import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import progressa.progressaspring.datas.ExerciseTypeData;
import progressa.progressaspring.facades.ExerciseTypeFacade;
import progressa.progressaspring.populators.BasePopulator;

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
    public ResponseEntity<Object> saveNew(@RequestBody final ExerciseTypeData exerciseTypeData) {
        return Optional.ofNullable(exerciseTypeData)
                       .map(exerciseType -> exerciseTypeFacade.save(exerciseType))
                       .map(exerciseType -> ResponseEntity.status(HttpStatus.CREATED).build())
                       .orElseThrow();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> saveUpdate(@PathVariable final Long id, @RequestBody final ExerciseTypeData exerciseTypeData) {
        return exerciseTypeFacade.findById(id)
                                 .map(exerciseTypeDataFound -> {
                                     exerciseTypeDataPopulator.populate(exerciseTypeData, exerciseTypeDataFound);
                                     return exerciseTypeFacade.save(exerciseTypeDataFound);
                                 })
                                 .map(exerciseTypeDataFound -> ResponseEntity.ok().build())
                                 .orElseThrow();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable final Long id) {
        exerciseTypeFacade.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
