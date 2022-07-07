package denistr.hw2_5.service;
import denistr.hw2_5.data.Employee;
import denistr.hw2_5.exception.EmployeeAlreadyAddedException;
import denistr.hw2_5.exception.EmployeeNotFoundException;
import denistr.hw2_5.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class EmployeeService {
    private final Map<String, Employee> employees = new HashMap<>(Map.of(
        "Ivanov1 Ivan", new Employee("Ivan", "Ivanov" , 5, 105_000),
        "Ivanov2 Ivan", new Employee("Stepan", "Sidorov", 3, 90_000),
        "Ivanov3 Ivan", new Employee("Roman", "Petrov", 3, 95_000),
        "Ivanov4 Ivan", new Employee("Oleg", "Golubev", 5, 100_000),
        "Ivanov5 Ivan", new Employee("Vladimir", "Sinicin", 4, 120_000),
        "Ivanov6 Ivan", new Employee("Denis", "Abramovich", 1, 110_000),
        "Ivanov7 Ivan", new Employee("Evgeniy", "Sokolov", 2, 115_000),
        "Ivanov8 Ivan", new Employee("Petr", "Fedorov", 5, 115_000)));
    private final int MAX_VALUE = 10;
    private String getKey(String firstName, String lastName) {
        return firstName + " " + lastName;
    }
    public Collection<Employee> getEmployees() {
        return new ArrayList<>(employees.values());
    }
    public Employee addEmployee(String firstName, String lastName, int dep, int salary)
            throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException {
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
}
