import com.spring.common.exceptions.TutorNotFoundException;
import com.spring.common.services.TutorServiceImpl;
import com.spring.dao.models.Tutor;
import com.spring.dao.repositories.TutorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TutorNotFoundExceptionTest {

    @Mock
    private TutorRepository tutorRepository;

    @InjectMocks
    private TutorServiceImpl tutorService;

    @Test
    public void findById_shouldThrowTutorNotFoundException_whenTutorIsNotFound() {
        // Given
        Long id = 1L;
        when(tutorRepository.findById(id)).thenReturn(Optional.empty());

        // When
        assertThrows(TutorNotFoundException.class, () -> tutorService.findById(id));
    }
}
