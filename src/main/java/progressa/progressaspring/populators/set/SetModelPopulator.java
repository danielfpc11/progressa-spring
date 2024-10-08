package progressa.progressaspring.populators.set;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import progressa.progressaspring.models.SetModel;
import progressa.progressaspring.populators.BasePopulator;

/**
 * @author danielfpc11@gmail.com
 */
@Component
public class SetModelPopulator extends BasePopulator<SetModel, SetModel> {

    @Override
    public void populate(final SetModel setModelSource, final SetModel setModelTarget) {
        Assert.notNull(setModelSource, SOURCE_NOT_NULL_MESSAGE);
        Assert.notNull(setModelTarget, TARGET_NOT_NULL_MESSAGE);

        setModelTarget.setNumber(setModelSource.getNumber());
        setModelTarget.setWeight(setModelSource.getWeight());
        setModelTarget.setRepetitions(setModelSource.getRepetitions());
        setModelTarget.setRir(setModelSource.getRir());
        setModelTarget.setExerciseModel(setModelSource.getExerciseModel());
    }

}
