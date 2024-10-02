package progressa.progressaspring.services.impl;

import jakarta.annotation.Resource;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import progressa.progressaspring.models.SetModel;
import progressa.progressaspring.repositories.SetRepository;
import progressa.progressaspring.services.SetService;
import progressa.progressaspring.utils.AssertUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
@Service
public class DefaultSetServiceImpl implements SetService {

    @Resource
    private SetRepository setRepository;

    @Override
    public List<SetModel> findAll() {
        return setRepository.findAll();
    }

    @Override
    public Optional<SetModel> findById(final Long id) throws IllegalArgumentException {
        return setRepository.findById(id);
    }

    @Override
    public void deleteById(final Long id) throws IllegalArgumentException {
        AssertUtils.idNotNullAndPositive(id);
        setRepository.deleteById(id);
    }

    @Override
    public SetModel save(final SetModel setModel) throws IllegalArgumentException {
        AssertUtils.notNull(setModel, SetModel.class);
        return setRepository.save(setModel);
    }

}
