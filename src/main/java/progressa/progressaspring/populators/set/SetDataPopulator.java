package progressa.progressaspring.populators.set;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import progressa.progressaspring.datas.SetData;
import progressa.progressaspring.populators.BasePopulator;

/**
 * @author danielfpc11@gmail.com
 */
@Component
public class SetDataPopulator extends BasePopulator<SetData, SetData> {

    @Override
    public void populate(final SetData setDataSource, final SetData setDataTarget) {
        Assert.notNull(setDataSource, SOURCE_NOT_NULL_MESSAGE);
        Assert.notNull(setDataTarget, TARGET_NOT_NULL_MESSAGE);

        setDataTarget.setNumber(setDataSource.getNumber());
        setDataTarget.setWeight(setDataSource.getWeight());
        setDataTarget.setRepetitions(setDataSource.getRepetitions());
        setDataTarget.setRir(setDataSource.getRir());
        setDataTarget.setExerciseId(setDataSource.getExerciseId());
    }

}
