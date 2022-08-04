package DenisTr.hw2_5;
import denistr.hw2_5.data.Employee;
import denistr.hw2_5.exception.EmployeeAlreadyAddedException;
import denistr.hw2_5.exception.EmployeeNotFoundException;
import denistr.hw2_5.exception.EmployeeStorageIsFullException;
import denistr.hw2_5.exception.WrongEmployeeNameException;
import denistr.hw2_5.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {
    private final String FIRST_NAME = "Denis";
    private final String LAST_NAME = "Sokolov";
    private final int DEP = 3;
    private final int SALARY = 100_000;
    private final Employee employee1 = new Employee(FIRST_NAME, LAST_NAME,DEP, SALARY);
    private final EmployeeService out = new EmployeeService();
    @Test
    public void shouldGetEmployees() {
        Collection<Employee> outCollection = new ArrayList<>();
        outCollection.add(employee1);
        out.addEmployee(FIRST_NAME, LAST_NAME, DEP, SALARY);
        assertEquals(outCollection, out.getEmployees());
    }
    @Test
    public void shouldReturnAddEmployee() {
        assertEquals(employee1, out.addEmployee(FIRST_NAME, LAST_NAME, DEP, SALARY));
    }
    @Test
    public void shouldCheckName() {
        assertEquals(employee1, out.addEmployee("deniS","sokoloV",DEP,SALARY));
        assertThrows(WrongEmployeeNameException.class,() -> out.addEmployee("Denis1", LAST_NAME,DEP,SALARY));
        assertThrows(WrongEmployeeNameException.class,() -> out.addEmployee(FIRST_NAME, "Sokolov_",DEP,SALARY));
    }
    @Test
    public void shouldThrowEmployeeStorageIsFullException() {
        assertThrows(EmployeeStorageIsFullException.class, ()-> {
            StringBuilder name = new StringBuilder(FIRST_NAME);
            for (; ; ) {
                out.addEmployee(name.toString(), LAST_NAME, DEP, SALARY);
                name.append("a");
            }
        });
    }
    @Test
    public void shouldThrowEmployeeAlreadyAddedException() {
        out.addEmployee(FIRST_NAME, LAST_NAME, DEP, SALARY);
        assertThrows(EmployeeAlreadyAddedException.class, ()->out.addEmployee(FIRST_NAME, LAST_NAME, DEP, SALARY));
    }
    @Test
    public void shouldReturnDelEmployee() {
        out.addEmployee(FIRST_NAME, LAST_NAME, DEP, SALARY);
        assertEquals(employee1, out.delEmployee(FIRST_NAME,LAST_NAME));
        assertThrows(EmployeeNotFoundException.class,()->out.findEmployee(FIRST_NAME,LAST_NAME));
    }
    @Test
    public void shouldThrowEmployeeNotFoundException() {
        assertThrows(EmployeeNotFoundException.class, ()->out.findEmployee(FIRST_NAME, LAST_NAME));
        assertThrows(EmployeeNotFoundException.class, ()->out.delEmployee(FIRST_NAME, LAST_NAME));
    }
    @Test
    public void shouldReturnFindEmployee() {
        out.addEmployee(FIRST_NAME, LAST_NAME, DEP, SALARY);
        assertEquals(employee1, out.findEmployee(FIRST_NAME,LAST_NAME));
    }
}
