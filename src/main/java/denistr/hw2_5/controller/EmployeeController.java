package denistr.hw2_5.controller;
import denistr.hw2_5.data.Employee;
import denistr.hw2_5.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
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
}