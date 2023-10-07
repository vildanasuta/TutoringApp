import com.spring.common.exceptions.ModuleNotFoundException;
import com.spring.common.services.ModuleServiceImpl;
import com.spring.dao.models.Module;
import com.spring.dao.repositories.ModuleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ModuleNotFoundExceptionTest {

    @Mock
    ModuleRepository moduleRepository;

    @InjectMocks
    ModuleServiceImpl moduleServiceImpl;

    @Test
    public void findById_throwsModuleNotFoundException_whenModuleNotFound() {
        Long id = 1L;
        when(moduleRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(ModuleNotFoundException.class, () -> moduleServiceImpl.findById(id));
    }
}
