package pro.sky.HW25.Collections;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employeeBookMap = new HashMap<>();


    @Override
    public Map<String, Employee> getEmployeeBookMap() {
        return employeeBookMap;
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
        } else {
            try {
                if (StringUtils.isEmpty(firstName) || StringUtils.isEmpty(lastName)) {
                    throw new IllegalInputException();
                }
                if (employeeBookMap.containsKey(employee.getFullName())) {
                    throw new EmployeeAlreadyAddedException();
                }
                employeeBookMap.put(employee.getFullName(), employee);

//            } catch (EmptyDataException e) {
//                return "Введены не все параметры!";
            } catch (EmployeeAlreadyAddedException e) {
                return "Ошибка, сотрудник уже добавлен";
            }
            return "Employee " + employee + " добавлен!";
        }
    }

    @Override
    public String deleteEmployee(String firstName, String lastName) {
        Employee employee = new Employee(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName));
        if (!(StringUtils.isAlpha(firstName) && StringUtils.isAlpha(lastName))) {
            throw new IllegalInputException();
        } else {
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
        Employee employee = new Employee(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName));
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
                return "There is no such employee";
            }
            return employee.getFullName();
        }
    }

}
