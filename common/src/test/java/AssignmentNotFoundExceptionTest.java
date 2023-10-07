
import com.spring.common.exceptions.AssignmentNotFoundException;
import com.spring.common.services.AssignmentServiceImpl;
import com.spring.dao.models.Assignment;
import com.spring.dao.repositories.AssignmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.mock;


public class AssignmentNotFoundExceptionTest {

 private final AssignmentRepository assignmentRepository= mock(AssignmentRepository.class);

 private final AssignmentServiceImpl assignmentServiceImpl=new AssignmentServiceImpl(assignmentRepository);

/*@Before
public void SetUp() {

    MockitoAnnotations.initMocks(this);
*/





 @Test
public void  findById_shouldReturnAssignment_whenAssignmentExists(){

  //arrange
  Long id=1L;
  Assignment exceptedAssignment=new Assignment();

  Mockito.when(assignmentRepository.findById(id)).thenReturn(Optional.of(exceptedAssignment));

  //act
   Assignment actualAssignment=assignmentServiceImpl.findById(id);

  //assert
     Assertions.assertEquals(exceptedAssignment,actualAssignment);

 }

 @Test
   public void findById_shouldThrowAssignmentNotFoundException_whenAssignmentDoesNotExist() {

     Long id=1L;

     Mockito.when(assignmentRepository.findById(id)).thenReturn(Optional.empty());

     //assert
     Assertions.assertThrows(AssignmentNotFoundException.class,() ->{

         //act
         assignmentServiceImpl.findById(id);
     });
 }
 }


