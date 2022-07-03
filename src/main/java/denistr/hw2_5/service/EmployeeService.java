package denistr.hw2_5.service;
import denistr.hw2_5.data.Employee;
import denistr.hw2_5.exception.EmployeeAlreadyAddedException;
import denistr.hw2_5.exception.EmployeeNotFoundException;
import denistr.hw2_5.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@Service
public class EmployeeService {
    private final Map<String, Employee> employees = new HashMap<>();
    private final int MAX_VALUE = 10;
    private String getKey(String firstName, String lastName) {
        return firstName + " | " + lastName;
    }
    public Collection<Employee> getEmployees() {
        return employees.values();
    }
    public Employee addEmployee(String firstName, String lastName)
            throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException {
        String key = getKey(firstName,lastName);
        if (employees.size() == MAX_VALUE) {
            throw new EmployeeStorageIsFullException("Массив сотрудников переполнен!");
        } else if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть в массиве!");
        } else {
            employees.put(key,new Employee(firstName,lastName));
            return employees.get(key);
        }
    }
    public Employee delEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        return employees.remove(getKey(firstName,lastName));
    }
    public Employee findEmployee(String firstName, String lastName) throws EmployeeNotFoundException{
        String key = getKey(firstName,lastName);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("Сотрудник не найден!");
        } else {
            return employees.get(key);
        }
    }
}
