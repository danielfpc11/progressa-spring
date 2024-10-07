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
import progressa.progressaspring.datas.WorkoutData;
import progressa.progressaspring.facades.WorkoutFacade;
import progressa.progressaspring.populators.BasePopulator;

import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
@Controller
@RequestMapping("/workout")
public class WorkoutController {

    @Resource
    private WorkoutFacade workoutFacade;
    @Resource
    private BasePopulator<WorkoutData, WorkoutData> workoutDataPopulator;

    @GetMapping("/all")
    public ResponseEntity<List<WorkoutData>> findAll() {
        return ResponseEntity.ok(workoutFacade.findAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<WorkoutData> findById(@PathVariable final Long id) {
        return workoutFacade.findById(id)
                            .map(ResponseEntity::ok)
                            .orElseThrow();
    }

    @PostMapping("/new")
    public ResponseEntity<Object> saveNew(@RequestBody final WorkoutData workoutData) {
        return Optional.ofNullable(workoutData)
                       .map(workout -> workoutFacade.save(workout))
                       .map(workout -> ResponseEntity.status(HttpStatus.CREATED).build())
                       .orElseThrow();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> saveUpdate(@PathVariable final Long id, @RequestBody final WorkoutData workoutData) {
        return workoutFacade.findById(id)
                            .map(workoutDataFound -> {
                                workoutDataPopulator.populate(workoutData, workoutDataFound);
                                return workoutFacade.save(workoutDataFound);
                            })
                            .map(workoutDataFound -> ResponseEntity.ok().build())
                            .orElseThrow();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable final Long id) {
        workoutFacade.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
