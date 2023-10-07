package com.spring.common.services;

import com.spring.common.exceptions.TaskNotFoundException;
import com.spring.common.interfaces.TaskService;
import com.spring.dao.models.Question;
import com.spring.dao.models.Task;
import com.spring.dao.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service class for QuestionService implementation
 */
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    /**
     * Constructor method for service implementation class {@link TaskServiceImpl}
     *
     * @param taskRepository task repository
     */
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * CRUD service method findAll()
     *
     * @return HashSet filled with tasks
     */
    @Override
    public Set<Task> findAll() {
        return new HashSet<>(taskRepository.findAll());
    }

    /**
     * CRUD Service method findById()
     *
     * @param aLong ID of instance of {@link Task}
     * @return returns instance of {@link Task} with provided ID
     */
    @Override
    public Task findById(Long aLong) {
        Optional<Task> optionalTask = taskRepository.findById(aLong);
        if (optionalTask.isEmpty())
            throw new TaskNotFoundException();
        return optionalTask.get();
    }

    /**
     * CRUD Service method save()
     *
     * @param task instance of {@link Task}
     * @return save Task instance to repository
     */
    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    /**
     * CRUD Service method delete()
     *
     * @param task instance of {@link Task}
     */
    @Override
    public void delete(Task task) {
        taskRepository.delete(task);
    }

    /**
     * CRUD Service method deleteById()
     *
     * @param aLong ID of instance of {@link Task}
     */
    @Override
    public void deleteById(Long aLong) {
        taskRepository.deleteById(aLong);
    }


}