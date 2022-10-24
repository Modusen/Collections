package pro.sky.HW25.Collections;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    List<Employee> employeeByDepartment(int department);

    List<Integer> getAllDepartments();

    Employee getMaximumSalaryInDepartment(int department);

    Employee getMinimumSalaryInDepartment(int department);

    Map<Integer, List<Employee>> printEmployeeByEveryDepartment();
}
