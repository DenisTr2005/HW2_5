package denistr.hw2_5.controller;
import denistr.hw2_5.data.Employee;
import denistr.hw2_5.service.DepartmentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping("/max-salary")
    public Employee maxSalary(@RequestParam("dep") int dep) {
        return departmentService.maxSalaryOfDep(dep);
    }
    @GetMapping("/min-salary")
    public Employee minSalary(@RequestParam("dep") int dep) {
        return departmentService.minSalaryOfDep(dep);
    }
    @GetMapping()
    public List<Employee> depEmployees(@RequestParam("dep") int dep) {
        return departmentService.depEmployee(dep);
    }
    @GetMapping("/all")
    public Map<Integer, List<Employee>> allDepEmployees() {
        return departmentService.allDepEmployee();
    }
}