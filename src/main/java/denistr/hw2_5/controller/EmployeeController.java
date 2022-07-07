package denistr.hw2_5.controller;
import denistr.hw2_5.data.Employee;
import denistr.hw2_5.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/add")
    public Employee add(@RequestParam("firstName") String firstName,
                        @RequestParam("lastName") String lastName,
                        @RequestParam("dep") int dep,
                        @RequestParam("salary") int salary) {
        return employeeService.addEmployee(firstName, lastName, dep, salary);
    }
    @GetMapping("/remove")
    public Employee remove(@RequestParam("firstName") String firstName,
                        @RequestParam("lastName") String lastName) {
        return employeeService.delEmployee(firstName, lastName);
    }
    @GetMapping("/find")
    public Employee find(@RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }
    @GetMapping
    public Collection<Employee> employeeList() {
        return employeeService.getEmployees();
    }
    @GetMapping("/departments/max-salary")
    public Employee maxSalary(@RequestParam("dep") int dep) {
        return employeeService.maxSalaryOfDep(dep);
    }
    @GetMapping("/departments/min-salary")
    public Employee minSalary(@RequestParam("dep") int dep) {
        return employeeService.minSalaryOfDep(dep);
    }
    @GetMapping("/departments/all")
    public Map<Integer, List<Employee>> depAll(Integer dep) {
        if (dep == null) {
            return employeeService.allDepEmployee();
        }
        return Map.of(dep,employeeService.depEmployee(dep));
    }
}