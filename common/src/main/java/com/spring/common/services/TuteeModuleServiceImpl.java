package com.spring.common.services;

import com.spring.common.exceptions.TaskNotFoundException;
import com.spring.common.exceptions.TuteeModuleNotFoundException;
import com.spring.common.interfaces.TuteeModuleService;
import com.spring.dao.models.Task;
import com.spring.dao.models.Tutee;
import com.spring.dao.models.TuteeModule;
import com.spring.dao.repositories.TuteeModuleRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service class for TuteeModuleService implementation
 */
@Service
public class TuteeModuleServiceImpl implements TuteeModuleService {
    private final TuteeModuleRepository tuteeModuleRepository;

    /**
     * Constructor method for service implementation class {@link TuteeModuleServiceImpl}
     *
     * @param tuteeModuleRepository tuteeModule repository
     */
    public TuteeModuleServiceImpl(TuteeModuleRepository tuteeModuleRepository) {
        this.tuteeModuleRepository = tuteeModuleRepository;
    }

    /**
     * CRUD service method findAll()
     *
     * @return HashSet filled with TuteeModule objects
     */
    @Override
    public Set<TuteeModule> findAll() {
        return new HashSet<>(tuteeModuleRepository.findAll());
    }

    /**
     * CRUD Service method findById()
     *
     * @param aLong ID of instance of {@link TuteeModule}
     * @return returns instance of {@link TuteeModule} with provided ID
     */
    @Override
    public TuteeModule findById(Long aLong) {
        Optional<TuteeModule> optionalTuteeModule = tuteeModuleRepository.findById(aLong);
        if (optionalTuteeModule.isEmpty())
            throw new TuteeModuleNotFoundException();
        return optionalTuteeModule.get();
    }

    /**
     * CRUD Service method save()
     *
     * @param tuteeModule instance of {@link TuteeModule}
     * @return save TuteeModule instance to repository
     */
    @Override
    public TuteeModule save(TuteeModule tuteeModule) {
        return tuteeModuleRepository.save(tuteeModule);
    }

    /**
     * CRUD Service method delete()
     *
     * @param tuteeModule instance of {@link TuteeModule}
     */
    @Override
    public void delete(TuteeModule tuteeModule) {
        tuteeModuleRepository.delete(tuteeModule);
    }

    /**
     * CRUD Service method deleteById()
     *
     * @param aLong ID of instance of {@link TuteeModule}
     */
    @Override
    public void deleteById(Long aLong) {
        tuteeModuleRepository.deleteById(aLong);

    }

    /**
     * CRUD Service method autogenerated by field 'Tutee' in model {@link TuteeModule}
     *
     * @param tutee instance of {@link Tutee}
     * @return list of instances of {@link TuteeModule} with corresponding Tutee provided in parameter
     */
    @Override
    public List<TuteeModule> findByTutee(Tutee tutee) {
        return tuteeModuleRepository.findByTutee(tutee);
    }
}
