package pro.sky.HW25.Collections;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    String addEmployee(String firstName, String lastName, int salary, int department);

    String deleteEmployee(String firstName, String lastName);

    String findEmployee(String firstName, String lastName);

    Map<String, Employee> getEmployeeBookMap();


    List<Employee> findAll();

}
