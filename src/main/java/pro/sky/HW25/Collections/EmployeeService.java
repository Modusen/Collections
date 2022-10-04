package pro.sky.HW25.Collections;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public interface EmployeeService {
    String addEmployee(String firstName, String lastName, int salary, int department);

    String deleteEmployee(String firstName, String lastName);

    String findEmployee(String firstName, String lastName);


    List<Employee> employeeByDepartment(int department);

    List<Integer> getAllDepartments();

    List<Employee> findAll();

}
