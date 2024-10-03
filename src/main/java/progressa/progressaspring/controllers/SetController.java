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
import progressa.progressaspring.datas.SetData;
import progressa.progressaspring.facades.SetFacade;
import progressa.progressaspring.populators.BasePopulator;

import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
@Controller
@RequestMapping("/set")
public class SetController {

    @Resource
    private SetFacade setFacade;
    @Resource
    private BasePopulator<SetData, SetData> setDataPopulator;

    @GetMapping("/all")
    public ResponseEntity<List<SetData>> findAll() {
        return ResponseEntity.ok(setFacade.findAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<SetData> findAll(@PathVariable final Long id) {
        return setFacade.findById(id)
                        .map(ResponseEntity::ok)
                        .orElseThrow();
    }

    @PostMapping("/new")
    public ResponseEntity<Object> saveNew(@RequestBody final SetData setData) {
        return Optional.ofNullable(setData)
                       .map(set -> setFacade.save(set))
                       .map(set -> ResponseEntity.status(HttpStatus.CREATED).build())
                       .orElseThrow();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> saveUpdate(@PathVariable final Long id, @RequestBody final SetData setData) {
        return setFacade.findById(id)
                        .map(setDataFound -> {
                            setDataPopulator.populate(setData, setDataFound);
                            return setFacade.save(setDataFound);
                        })
                        .map(workoutDataFound -> ResponseEntity.ok().build())
                        .orElseThrow();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable final Long id) {
        setFacade.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
