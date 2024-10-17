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
import progressa.progressaspring.datas.SetData;
import progressa.progressaspring.facades.SetFacade;
import progressa.progressaspring.groups.set.CreateSetGroup;
import progressa.progressaspring.groups.set.UpdateSetGroup;
import progressa.progressaspring.populators.BasePopulator;
import progressa.progressaspring.utils.PopulatorUtils;

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
    @Resource
    private BasePopulator<SetData, SetData> setDataRelationshipsPopulator;

    @GetMapping("/all")
    public ResponseEntity<List<SetData>> findAll() {
        return ResponseEntity.ok(setFacade.findAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<SetData> findById(@PathVariable final Long id) {
        return setFacade.findById(id)
                        .map(ResponseEntity::ok)
                        .orElseThrow();
    }

    @PostMapping("/new")
    public ResponseEntity<Object> saveNew(@RequestBody @Validated({CreateSetGroup.class}) final SetData setData) {
        return Optional.ofNullable(setData)
                       .map(set -> setFacade.save(set))
                       .map(set -> ResponseEntity.status(HttpStatus.CREATED).build())
                       .orElseThrow();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> saveUpdate(@PathVariable final Long id,
                                             @RequestBody @Validated({UpdateSetGroup.class}) final SetData setData) {
        return saveUpdate(id, setData, List.of(setDataPopulator, setDataRelationshipsPopulator));
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<Object> savePatch(@PathVariable final Long id, @RequestBody @Valid final SetData setData) {
        return saveUpdate(id, setData, List.of(setDataPopulator));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable final Long id) {
        setFacade.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<Object> saveUpdate(final Long id,
                                              final SetData setData,
                                              final List<BasePopulator<SetData, SetData>> setDataPopulators) {
        return setFacade.findById(id)
                        .map(setDataFound -> {
                            PopulatorUtils.populateSetDatas(setData, setDataFound, setDataPopulators);
                            return setFacade.save(setDataFound);
                        })
                        .map(workoutDataFound -> ResponseEntity.ok().build())
                        .orElseThrow();
    }

}
