package progressa.progressaspring.converters.set;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.Assert;
import progressa.progressaspring.datas.SetData;
import progressa.progressaspring.models.SetModel;

/**
 * @author danielfpc11@gmail.com
 */
public class SetReverseConverter implements Converter<SetData, SetModel> {

    private static final String SET_DATA_NOT_NULL_MESSAGE = "SetModel must not be null.";

    @Override
    public SetModel convert(final SetData setData) {
        Assert.notNull(setData, SET_DATA_NOT_NULL_MESSAGE);
        return SetModel.builder()
                       .id(setData.getId())
                       .number(setData.getNumber())
                       .weight(setData.getWeight())
                       .repetitions(setData.getRepetitions())
                       .rir(setData.getRir())
                       .build();
    }

}
