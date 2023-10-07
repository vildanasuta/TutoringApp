import com.spring.common.exceptions.TuteeNotFoundException;
import com.spring.common.services.TuteeServiceImpl;
import com.spring.dao.models.Tutee;
import com.spring.dao.repositories.TuteeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TuteeNotFoundExceptionTest {

    @Mock
    private TuteeRepository tuteeRepository;

    @InjectMocks
    private TuteeServiceImpl tuteeService;

    @Test
    public void findById_shouldThrowTuteeNotFoundException_whenTuteeIsNotFound() {
        // Given
        Long id = 1L;
        when(tuteeRepository.findById(id)).thenReturn(Optional.empty());

        // When
        assertThrows(TuteeNotFoundException.class, () -> tuteeService.findById(id));
    }
}

