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
import progressa.progressaspring.datas.WorkoutData;
import progressa.progressaspring.facades.WorkoutFacade;
import progressa.progressaspring.groups.workout.CreateWorkoutGroup;
import progressa.progressaspring.groups.workout.UpdateWorkoutGroup;
import progressa.progressaspring.populators.BasePopulator;
import progressa.progressaspring.utils.PopulatorUtils;

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
    @Resource
    private BasePopulator<WorkoutData, WorkoutData> workoutDataRelationshipsPopulator;

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
    public ResponseEntity<Object> saveNew(@RequestBody @Validated({CreateWorkoutGroup.class}) final WorkoutData workoutData) {
        return Optional.ofNullable(workoutData)
                       .map(workout -> workoutFacade.save(workout))
                       .map(workout -> ResponseEntity.status(HttpStatus.CREATED).build())
                       .orElseThrow();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> saveUpdate(@PathVariable final Long id,
                                             @RequestBody @Validated({UpdateWorkoutGroup.class}) final WorkoutData workoutData) {
        return saveUpdate(id, workoutData, List.of(workoutDataPopulator, workoutDataRelationshipsPopulator));
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<Object> savePatch(@PathVariable final Long id,
                                            @RequestBody @Valid final WorkoutData workoutData) {
        return saveUpdate(id, workoutData, List.of(workoutDataPopulator));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable final Long id) {
        workoutFacade.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<Object> saveUpdate(final Long id,
                                              final WorkoutData workoutData,
                                              final List<BasePopulator<WorkoutData, WorkoutData>> workoutDataPopulators) {
        return workoutFacade.findById(id)
                            .map(workoutDataFound -> {
                                PopulatorUtils.populateWorkoutDatas(workoutData, workoutDataFound, workoutDataPopulators);
                                return workoutFacade.save(workoutDataFound);
                            })
                            .map(workoutDataFound -> ResponseEntity.ok().build())
                            .orElseThrow();
    }

}
