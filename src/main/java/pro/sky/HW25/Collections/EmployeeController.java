package pro.sky.HW25.Collections;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping(path = "/add")
    public String addEmployee(@RequestParam("name") String firstName, @RequestParam("lastname") String lastName, @RequestParam("salary") int salary, @RequestParam("department") int department) {
        return employeeService.addEmployee(firstName, lastName, salary, department);
    }

    @GetMapping(path = "/delete")
    public String deleteEmployee(@RequestParam("name") String firstName, @RequestParam("lastname") String lastName) {
        return employeeService.deleteEmployee(firstName, lastName);
    }

    @GetMapping(path = "/find")
    public String findEmployee(@RequestParam("name") String firstName, @RequestParam("lastname") String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }

    @GetMapping(path = "/printall")
    public Collection<Employee> findAll() {
        return employeeService.findAll();
    }
}
