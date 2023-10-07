package com.spring.common.services;

import com.spring.common.exceptions.AssignmentNotFoundException;
import com.spring.common.interfaces.QuestionService;
import com.spring.dao.models.Assignment;
import com.spring.dao.models.Module;
import com.spring.dao.models.Question;
import com.spring.dao.repositories.QuestionRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Service class for QuestionService implementation
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    /**
     * Constructor method for service implementation class {@link QuestionServiceImpl}
     *
     * @param questionRepository module repository
     */
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    /**
     * CRUD service method findAll()
     *
     * @return HashSet filled with questions
     */
    @Override
    public Set<Question> findAll() {
        return new HashSet<>(questionRepository.findAll(Sort.by("id").descending()));
    }

    /**
     * CRUD Service method findById()
     *
     * @param id ID of instance of {@link Question}
     * @return returns instance of {@link Question} with provided ID
     */
    @Override
    public Question findById(Long id) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isEmpty())
            throw new AssignmentNotFoundException();
        return question.get();
    }

    /**
     * CRUD Service method save()
     *
     * @param question instance of {@link Question}
     * @return save Question instance to repository
     */
    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    /**
     * CRUD Service method delete()
     *
     * @param question instance of {@link Question}
     */
    @Override
    public void delete(Question question) {
        questionRepository.delete(question);

    }

    /**
     * CRUD Service method deleteById()
     *
     * @param aLong ID of instance of {@link Question}
     */
    @Override
    public void deleteById(Long aLong) {
        questionRepository.deleteById(aLong);
    }


}
