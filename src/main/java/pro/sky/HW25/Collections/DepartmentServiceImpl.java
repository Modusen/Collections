package pro.sky.HW25.Collections;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeServiceImpl employeeServiceImpl;

    public DepartmentServiceImpl(EmployeeServiceImpl employeeServiceImpl) {

        this.employeeServiceImpl = employeeServiceImpl;
    }

    @Override
    public List<Employee> employeeByDepartment(int department) {
        return employeeServiceImpl.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> getAllDepartments() {
        final HashSet<Integer> result = new HashSet<>();
        for (Employee e : employeeServiceImpl.getEmployeeBookMap().values()) {
            result.add(e.getDepartment());
        }
        return result.stream().sorted().collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> printEmployeeByEveryDepartment() {
//        final List<Integer> allDepartments = getAllDepartments();
//        final List<String> arrayForAll = new ArrayList<>();
//        for (Integer num : allDepartments) {
//            final List<Employee> employees = employeeByDepartment(num);
//            arrayForAll.add("Департамент № " + num);
//            List<String> streamFullNames = employees.stream().
//                    map(Employee::getFullName).
//                    collect(Collectors.toList());
//            arrayForAll.add(streamFullNames.toString());
//        }
        return employeeServiceImpl.findAll().stream().collect(Collectors.groupingBy(Employee::getDepartment));
    }

    public Employee getMaximumSalaryInDepartment(int department) {
        return employeeServiceImpl.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary)).get();
    }
    public Employee getMinimumSalaryInDepartment(int department) {

        return employeeServiceImpl.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary)).get();
    }


}
