package pro.sky.HW25.Collections;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final EmployeeService employeeService;

    public DepartmentController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/allInDepartment")
    public String employeeByDepartment(@RequestParam("department") int department) {
        final List<Employee> employees = employeeService.employeeByDepartment(department);

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
    public List<String> printEmployeeByDepartment() {
        final List<Integer> allDepartments = employeeService.getAllDepartments();
        final List<String> arrayForAll = new ArrayList<>();
        for (Integer num : allDepartments) {
            final List<Employee> employees = employeeService.employeeByDepartment(num);
            arrayForAll.add("Департамент № "+num);
            List<String> streamFullNames = employees.stream().
                    map(e -> e.getFullName()).
                    collect(Collectors.toList());
            arrayForAll.add(streamFullNames.toString());
        }
        return arrayForAll;
    }

    @GetMapping(path = "/min-salary")
    public String getMinimumSalaryInDepartment(@RequestParam("department") int department) {
        final List<Employee> employees = employeeService.employeeByDepartment(department);

        return employees.stream().min(Comparator.comparingInt(e -> e.getSalary())).toString();
    }

    @GetMapping(path = "/max-salary")
    public String getMaximumSalaryInDepartment(@RequestParam("department") int department) {
        final List<Employee> employees = employeeService.employeeByDepartment(department);

        return employees.stream().max(Comparator.comparingInt(e -> e.getSalary())).toString();
    }
}
