package progressa.progressaspring.converters.set;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.Assert;
import progressa.progressaspring.datas.SetData;
import progressa.progressaspring.models.SetModel;

/**
 * @author danielfpc11@gmail.com
 */
public class SetConverter implements Converter<SetModel, SetData> {

    private static final String SET_MODEL_NOT_NULL_MESSAGE = "SetModel must not be null.";

    @Override
    public SetData convert(final SetModel setModel) {
        Assert.notNull(setModel, SET_MODEL_NOT_NULL_MESSAGE);
        return SetData.builder()
                      .id(setModel.getId())
                      .number(setModel.getNumber())
                      .weight(setModel.getWeight())
                      .repetitions(setModel.getRepetitions())
                      .rir(setModel.getRir())
                      .build();
    }

}
