import com.spring.common.exceptions.TuteeModuleNotFoundException;
import com.spring.common.services.TuteeModuleServiceImpl;
import com.spring.dao.models.TuteeModule;
import com.spring.dao.repositories.TuteeModuleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TuteeModuleNotFoundExceptionTest {

    @Mock
    TuteeModuleRepository tuteeModuleRepository;

    @InjectMocks
    TuteeModuleServiceImpl tuteeModuleServiceImpl;

    @Test
    public void findById_throwsTuteeModuleNotFoundException_whenTuteeModuleNotFound() {
        Long id = 1L;
        when(tuteeModuleRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(TuteeModuleNotFoundException.class, () -> tuteeModuleServiceImpl.findById(id));
    }
}

