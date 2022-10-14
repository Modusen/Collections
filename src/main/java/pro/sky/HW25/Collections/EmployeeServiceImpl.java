package pro.sky.HW25.Collections;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private Map<String, Employee> employeeBookMap = new HashMap<>();

    @Override
    public List<Employee> employeeByDepartment(int department) {
        final List<Employee> result = new ArrayList<>();
        for (Employee employee : employeeBookMap.values()) {
            if (employee.getDepartment() == department) {
                result.add(employee);
            }
        }
        return result;
    }

    @Override
    public List<Integer> getAllDepartments() {
        final HashSet<Integer> result = new HashSet<>();
        for (Employee e : employeeBookMap.values()) {
            result.add(e.getDepartment());
        }
        return result.stream().sorted().collect(Collectors.toList());
    }


    @Override
    public List<Employee> findAll() {
        return List.copyOf(employeeBookMap.values());
    }

    @Override
    public String addEmployee(String firstName, String lastName, int salary, int department) {
        Employee employee = new Employee(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName), salary, department);
        if (!(StringUtils.isAlpha(firstName) && StringUtils.isAlpha(lastName))) {
            throw new IllegalInputException();
        }else {
            try {
                if (StringUtils.isEmpty(firstName) || StringUtils.isEmpty(lastName)) {
                    throw new EmptyDataException();
                }
                if (employeeBookMap.containsKey(employee.getFullName())) {
                    throw new EmployeeAlreadyAddedException();
                }
                employeeBookMap.put(employee.getFullName(), employee);

            } catch (EmptyDataException e) {
                return "Введены не все параметры!";
            } catch (EmployeeAlreadyAddedException e) {
                return "Ошибка, сотрудник уже добавлен";
            }
            return "Сотрудник " + employee + " добавлен!";
        }
    }

    @Override
    public String deleteEmployee(String firstName, String lastName) {
        Employee employee = new Employee(StringUtils.capitalize(firstName), StringUtils.capitalize(firstName));
        if (!(StringUtils.isAlpha(firstName) && StringUtils.isAlpha(lastName))) {
            throw new IllegalInputException();
        }else {
            try {
                if (StringUtils.isEmpty(firstName) || StringUtils.isEmpty(lastName)) {
                    throw new EmptyDataException();
                }
                if (employeeBookMap.containsKey(employee.getFullName())) {
                    employeeBookMap.remove(employee.getFullName());
                } else {
                    throw new EmployeeNotFoundException();
                }
            } catch (EmptyDataException e) {
                return "Введены не все параметры!";
            } catch (EmployeeNotFoundException e) {
                return "Сотрудник с таким именем и фамилией не найден";
            }
            return "Сотрудник " + employee + " успешно удален";
        }
    }

    @Override
    public String findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(StringUtils.capitalize(firstName), StringUtils.capitalize(firstName));
        if (!(StringUtils.isAlpha(firstName) && StringUtils.isAlpha(lastName))) {
            throw new IllegalInputException();
        } else {
            try {
                if (StringUtils.isEmpty(firstName) || StringUtils.isEmpty(lastName)) {
                    throw new EmptyDataException();
                }
                if (!employeeBookMap.containsKey(employee.getFullName())) {
                    throw new EmployeeNotFoundException();
                }
            } catch (EmptyDataException e) {
                return "Введены не все параметры!";
            } catch (EmployeeNotFoundException e) {
                return "Такого сотрудника нет";
            }
            return employee.getFullName();
        }
    }

}
