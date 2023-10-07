
import com.spring.common.exceptions.DiaryNotFoundException;
import com.spring.common.services.DiaryServiceImpl;
import com.spring.dao.models.Diary;
import com.spring.dao.repositories.DiaryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class DiaryNotFoundExceptionTest {

    @Mock
    public DiaryRepository diaryRepository;

    @InjectMocks
    public DiaryServiceImpl diaryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findById_shouldThrowDiaryNotFoundException_whenDiaryIsNotFound() {
        Long diaryId = 1L;

        when(diaryRepository.findById(diaryId)).thenReturn(Optional.empty());

        assertThrows(DiaryNotFoundException.class, () -> diaryService.findById(diaryId));
    }
}
