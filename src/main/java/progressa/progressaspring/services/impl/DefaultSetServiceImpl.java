package progressa.progressaspring.services.impl;

import jakarta.annotation.Resource;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import progressa.progressaspring.models.SetModel;
import progressa.progressaspring.repositories.SetRepository;
import progressa.progressaspring.services.SetService;

import java.util.List;
import java.util.Optional;

/**
 * @author danielfpc11@gmail.com
 */
@Service
public class DefaultSetServiceImpl implements SetService {

    private static final String ID_NOT_NULL_MESSAGE = "Id must not be null.";
    private static final String ID_POSITIVE_MESSAGE = "Id must be greater than zero.";
    private static final String SET_NOT_NULL_MESSAGE = "Set must not be null.";

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
        assertIdNotNullAndPositive(id);
        setRepository.deleteById(id);
    }

    @Override
    public SetModel save(final SetModel setModel) throws IllegalArgumentException {
        Assert.notNull(setModel, SET_NOT_NULL_MESSAGE);
        return setRepository.save(setModel);
    }

    private void assertIdNotNullAndPositive(final Long id) throws IllegalArgumentException {
        Assert.notNull(id, ID_NOT_NULL_MESSAGE);
        Assert.isTrue(id > NumberUtils.LONG_ZERO, ID_POSITIVE_MESSAGE);
    }

}
