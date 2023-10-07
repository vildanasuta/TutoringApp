import com.spring.common.exceptions.TaskNotFoundException;
import com.spring.common.services.TaskServiceImpl;
import com.spring.dao.repositories.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskNotFoundExceptionTest {

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    TaskServiceImpl taskServiceImpl;

    @Test
    public void findById_throwsTaskNotFoundException_whenTaskNotFound() {
        Long id = 1L;
        when(taskRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(TaskNotFoundException.class, () -> taskServiceImpl.findById(id));
    }
}
