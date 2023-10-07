package com.spring.common.services;

import com.spring.common.exceptions.FeedbackNotFoundException;
import com.spring.common.interfaces.FeedbackService;
import com.spring.dao.models.Answer;
import com.spring.dao.models.Diary;
import com.spring.dao.models.Feedback;
import com.spring.dao.repositories.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service class for FeedbackService implementation
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    /**
     * Constructor method for service implementation class {@link FeedbackServiceImpl}
     *
     * @param feedbackRepository feedback repository
     */
    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    /**
     * CRUD service method findAll()
     *
     * @return HashSet filled with feedbacks
     */
    @Override
    public Set<Feedback> findAll() {
        return new HashSet<>(feedbackRepository.findAll());
    }

    /**
     * CRUD Service method findById()
     *
     * @param aLong ID of instance of {@link Feedback}
     * @return returns instance of {@link Feedback} with provided ID
     */
    @Override
    public Feedback findById(Long aLong) {
        Optional<Feedback> optionalFeedback = feedbackRepository.findById(aLong);
        if (optionalFeedback.isEmpty()) {
            throw new FeedbackNotFoundException();
        }
        return optionalFeedback.get();
    }

    /**
     * CRUD Service method save()
     *
     * @param feedback instance of {@link Feedback}
     * @return save Feedback instance to repository
     */
    @Override
    public Feedback save(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    /**
     * CRUD Service method delete()
     *
     * @param feedback instance of {@link Feedback}
     */
    @Override
    public void delete(Feedback feedback) {
        feedbackRepository.delete(feedback);
    }

    /**
     * CRUD Service method deleteById()
     *
     * @param aLong ID of instance of {@link Feedback}
     */
    @Override
    public void deleteById(Long aLong) {
        feedbackRepository.deleteById(aLong);
    }

}