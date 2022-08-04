package denistr.hw2_5.service;
import denistr.hw2_5.data.Employee;
import denistr.hw2_5.exception.EmployeeAlreadyAddedException;
import denistr.hw2_5.exception.EmployeeNotFoundException;
import denistr.hw2_5.exception.EmployeeStorageIsFullException;
import denistr.hw2_5.exception.WrongEmployeeNameException;
import static org.apache.commons.lang3.StringUtils.*;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class EmployeeService {
    private final Map<String, Employee> employees = new HashMap<>(Map.of());
    private final int MAX_VALUE = 10;
    private String getKey(String firstName, String lastName) {
        return firstName + " " + lastName;
    }
    public Collection<Employee> getEmployees() {
        return new ArrayList<>(employees.values());
    }
    public Employee addEmployee(String firstName, String lastName, int dep, int salary)
            throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException {
        firstName=checkName(firstName);
        lastName=checkName(lastName);
        String key = getKey(firstName,lastName);
        if (employees.size() == MAX_VALUE) {
            throw new EmployeeStorageIsFullException("Массив сотрудников переполнен!");
        } else if (!employees.containsKey(key)) {
            employees.put(key,new Employee(firstName, lastName, dep, salary));
            return employees.get(key);
        }
        throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть в массиве!");
    }
    public Employee delEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        String key = getKey(firstName, lastName);
        if (employees.containsKey(key)) {
            return employees.remove(key);
        }
        throw new EmployeeNotFoundException("Сотрудник не найден!");
    }
    public Employee findEmployee(String firstName, String lastName) throws EmployeeNotFoundException{
        String key = getKey(firstName,lastName);
        if (employees.containsKey(key)) {
            return employees.get(key);
        }
        throw new EmployeeNotFoundException("Сотрудник не найден!");
    }
    private String checkName(String name) throws WrongEmployeeNameException{
        if (isAlpha(name)) {
            return capitalize(lowerCase(name));
        }
        throw new WrongEmployeeNameException("Недопустимые символы в имени сотрудника!");
    }
}
