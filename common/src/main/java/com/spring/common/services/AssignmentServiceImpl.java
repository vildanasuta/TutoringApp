package com.spring.common.services;

import com.spring.common.exceptions.AssignmentNotFoundException;
import com.spring.common.interfaces.AssignmentService;
import com.spring.dao.models.Assignment;
import com.spring.dao.repositories.AssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service class for AssignmentService implementation
 */
@Service
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;

    /**
     * Constructor class for service implementation class  {@link AssignmentServiceImpl}
     *
     * @param assignmentRepository assignment repository
     */
    public AssignmentServiceImpl(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public Set<Assignment> findAll() {
        return new HashSet<>(assignmentRepository.findAll());
    }

    @Override
    public Assignment findById(Long id) {
        Optional<Assignment> assignment = assignmentRepository.findById(id);
        if (assignment.isEmpty())
            throw new AssignmentNotFoundException();
        return assignment.get();
    }

    @Override
    public Assignment save(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    @Override
    public void delete(Assignment assignment) {
        assignmentRepository.delete(assignment);

    }

    @Override
    public void deleteById(Long id) {
        assignmentRepository.deleteById(id);

    }
}
