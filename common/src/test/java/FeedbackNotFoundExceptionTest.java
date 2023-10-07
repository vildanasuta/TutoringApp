import com.spring.common.exceptions.FeedbackNotFoundException;
import com.spring.common.services.FeedbackServiceImpl;
import com.spring.dao.models.Feedback;
import com.spring.dao.repositories.FeedbackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class FeedbackNotFoundExceptionTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @InjectMocks
    private FeedbackServiceImpl feedbackServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findById_shouldThrowFeedbackNotFoundException_whenFeedbackIsNotFound() {
        Long feedbackId = 1L;

        when(feedbackRepository.findById(feedbackId)).thenReturn(Optional.empty());

        assertThrows(FeedbackNotFoundException.class, () -> feedbackServiceImpl.findById(feedbackId));
    }
}
