package pro.sky.HW25.Collections;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private ArrayList<Employee> employeeBookList = new ArrayList();
    private Map<String, Employee> employeeBookMap = new HashMap<>();

    @Override
    public List<Employee> findAll() {
        return Collections.unmodifiableList(employeeBookList);
    }

    @Override
    public String addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        try {
            if (firstName == "" || lastName == "") {
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

    @Override
    public String deleteEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        int numberInList = 0;
        try {
            if (firstName == "" || lastName == "") {
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

    @Override
    public String findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        try {
            if (firstName == "" || lastName == "") {
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
