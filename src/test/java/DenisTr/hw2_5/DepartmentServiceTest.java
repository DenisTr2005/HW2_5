package DenisTr.hw2_5;

import denistr.hw2_5.data.Employee;
import denistr.hw2_5.exception.EmployeeNotFoundException;
import denistr.hw2_5.service.DepartmentService;
import denistr.hw2_5.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    private final Employee employee1 = new Employee("Denis","Sokolov",1,100_000);
    private final Employee employee2 = new Employee("Anna","Rusakova",2,50_000);
    private final Employee employee3 = new Employee("Tatyana","Starikova",1,75_000);
    private final Collection<Employee> list = new ArrayList<>(List.of(employee1,employee2,employee3));
    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private DepartmentService out;
    @BeforeEach
    public void setUp() {
        when(employeeService.getEmployees()).thenReturn(list);
    }
    @Test
    public void shouldReturnEmployeeWithMaxSalaryOfDep() {
        assertEquals(employee1, out.maxSalaryOfDep(1));
    }
    @Test
    public void shouldReturnEmployeeWithMinSalaryOfDep() {
        assertEquals(employee3, out.minSalaryOfDep(1));
    }
    @Test
    public void shouldReturnAllDepEmployee() {
        Map<Integer, List<Employee>> map = new HashMap<>(Map.of(1, List.of(employee1,employee3),
                                                                2, List.of(employee2)));
        assertEquals(map, out.allDepEmployee());
    }
    @Test
    public void shouldReturnDepEmployee() {
        List<Employee> dep1List = new ArrayList<>(List.of(employee1,employee3));
        assertEquals(dep1List, out.depEmployee(1));
    }
    @Test
    public void shouldThrowEmployeeNotFoundException() {
        assertThrows(EmployeeNotFoundException.class, ()->out.maxSalaryOfDep(3));
        assertThrows(EmployeeNotFoundException.class, ()->out.minSalaryOfDep(3));
    }
}
