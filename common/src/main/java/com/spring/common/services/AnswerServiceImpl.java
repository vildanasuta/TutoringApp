package com.spring.common.services;

import com.spring.common.exceptions.AnswerNotFoundException;
import com.spring.common.interfaces.AnswerService;
import com.spring.dao.models.Answer;
import com.spring.dao.repositories.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Service class for AnswerService implementation
 */
@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    /**
     * Constructor method for service implementation class {@link AnswerServiceImpl}
     * @param answerRepository answer repository
     */
    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    /**
     * CRUD service method findAll()
     * @return HashSet filled with answers
     */
    @Override
    public Set<Answer> findAll() {
        return new HashSet<>(answerRepository.findAll());
    }

    /**
     * CRUD Service method findById()
     * @param aLong ID of instance of {@link Answer}
     * @return returns instance of {@link Answer} with provided ID
     */
    @Override
    public Answer findById(Long aLong) {
        Optional<Answer> answer = answerRepository.findById(aLong);
        if (answer.isEmpty()){
            throw new AnswerNotFoundException();
        }
        return answer.get();
    }

    /**
     * CRUD Service method save()
     * @param answer instance of {@link Answer}
     * @return save Answer instance to repository
     */
    @Override
    public Answer save(Answer answer) {
        return answerRepository.save(answer);
    }

    /**
     * CRUD Service method delete()
     * @param answer instance of {@link Answer}
     */
    @Override
    public void delete(Answer answer) {
        answerRepository.delete(answer);
    }

    /**
     * CRUD Service method deleteById()
     * @param aLong ID of instance of {@link Answer}
     */
    @Override
    public void deleteById(Long aLong) {
        answerRepository.deleteById(aLong);
    }
}
