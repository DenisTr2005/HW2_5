package denistr.hw2_5.service;
import denistr.hw2_5.data.Employee;
import denistr.hw2_5.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class DepartmentService {
    private final EmployeeService employeeService;
    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    public Employee maxSalaryOfDep(int dep) throws EmployeeNotFoundException {
        return employeeService.getEmployees().stream()
                .filter(e->e.getDep()==dep)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден!"));
    }
    public Employee minSalaryOfDep(int dep) throws EmployeeNotFoundException{
        return employeeService.getEmployees().stream()
                .filter(e->e.getDep()==dep)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден!"));
    }
    public Map<Integer,List<Employee>> allDepEmployee() {
        return employeeService.getEmployees().stream()
                .map(Employee::getDep)
                .collect(Collectors.toSet())
                .stream()
                .collect(Collectors.toMap(k->k, this::depEmployee));
    }
    public List<Employee> depEmployee(int dep) {
        return employeeService.getEmployees().stream()
                .filter(e -> e.getDep() == dep)
                .collect(Collectors.toList());
    }
}
