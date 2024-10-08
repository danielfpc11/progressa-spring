package progressa.progressaspring.facades.impl;

import jakarta.annotation.Resource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import progressa.progressaspring.datas.SetData;
import progressa.progressaspring.facades.SetFacade;
import progressa.progressaspring.models.SetModel;
import progressa.progressaspring.services.SetService;

import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
@Component
public class DefaultSetFacadeImpl implements SetFacade {

    @Resource
    private SetService setService;
    @Resource
    private Converter<SetModel, SetData> setConverter;
    @Resource
    private Converter<SetData, SetModel> setReverseConverter;

    @Override
    public List<SetData> findAll() {
        return setService.findAll()
                         .stream()
                         .map(setConverter::convert)
                         .toList();
    }

    @Override
    public Optional<SetData> findById(final Long id) throws IllegalArgumentException {
        return setService.findById(id)
                         .map(setConverter::convert);
    }

    @Override
    public void deleteById(final Long id) throws IllegalArgumentException {
        setService.deleteById(id);
    }

    @Override
    public SetData save(final SetData setData) throws IllegalArgumentException {
        return setConverter.convert(setService.save(setReverseConverter.convert(setData)));
    }

}
