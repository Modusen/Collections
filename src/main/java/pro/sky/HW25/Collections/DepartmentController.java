package pro.sky.HW25.Collections;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/allInDepartment")
    public String employeeByDepartment(@RequestParam("department") int department) {
        final List<Employee> employees = departmentService.employeeByDepartment(department);

        // Iter
//        for (Employee e : employees) {
//            departments.add(e.getFullName());
//        }
        // Лямбда
//        employees.forEach(employee ->
//                departments.add(employee.getFullName()));
//        return departments.toString();
//    }
        List<String> streamFullNames = employees.stream().
                map(e -> e.getFullName()).
                map(fullName -> "~" + fullName + "~").
                collect(Collectors.toList());
        return streamFullNames.toString();
    }

    @GetMapping(path = "/all")
    public Map<Integer, List<Employee>> printEmployeeByEveryDepartment() {
       return departmentService.printEmployeeByEveryDepartment();
    }

    @GetMapping(path = "/min-salary")
    public Employee getMinimumSalaryInDepartment(@RequestParam("department") int department) {
        return departmentService.getMinimumSalaryInDepartment(department);
    }

    @GetMapping(path = "/max-salary")
    public Employee getMaximumSalaryInDepartment(@RequestParam("department") int department) {
        return departmentService.getMaximumSalaryInDepartment(department);
    }
}
