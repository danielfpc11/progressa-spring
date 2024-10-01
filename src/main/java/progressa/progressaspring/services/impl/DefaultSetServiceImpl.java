package progressa.progressaspring.services.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
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
        setRepository.deleteById(id);
    }

    @Override
    public SetModel save(final SetModel setModel) throws IllegalArgumentException {
        return setRepository.save(setModel);
    }

}
