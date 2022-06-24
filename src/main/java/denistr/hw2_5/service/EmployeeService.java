package denistr.hw2_5.service;

import denistr.hw2_5.exceptions.EmployeeAlreadyAddedException;
import denistr.hw2_5.exceptions.EmployeeNotFoundException;
import denistr.hw2_5.exceptions.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();

    public List<Employee> getEmployees() {
        return employees;
    }

    public Employee addEmployee(String firstName, String lastName)
            throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException {
        Employee employee = new Employee(firstName, lastName);
        if (employees.size() == Integer.MAX_VALUE) {
            throw new EmployeeStorageIsFullException("Массив сотрудников переполнен!");
        } else if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть в массиве!");
        } else {
            employees.add(employee);
            return employee;
        }
    }

    public Employee delEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = findEmployee(firstName,lastName);
        return employees.remove(employees.indexOf(employee));
    }
    public Employee findEmployee(String firstName, String lastName) throws EmployeeNotFoundException{
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException("Сотрудник не найден!");
        } else {
            return employee;
        }
    }

}
