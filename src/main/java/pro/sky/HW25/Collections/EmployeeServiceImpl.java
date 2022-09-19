package pro.sky.HW25.Collections;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private ArrayList<Employee> employeeBookList = new ArrayList();

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
            if (employeeBookList.contains(employee)) {
                throw new EmployeeAlreadyAddedException();
            }
            employeeBookList.add(employee);
        } catch (EmptyDataException e) {
            return "Введены не все параметры!";
        } catch (EmployeeAlreadyAddedException e) {
            return "Ошибка, сотрудник уже добавлен";
        }
        return "Сотрудник " + employee + " добавлен! Номер сотрудника: " + employeeBookList.indexOf(employee);
    }

    @Override
    public String deleteEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        int numberInList = 0;
        try {
            if (firstName == "" || lastName == "") {
                throw new EmptyDataException();
            }
            if (employeeBookList.contains(employee)) {
                employeeBookList.remove(employee);
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
            if (!employeeBookList.contains(employee)) {
                throw new EmployeeNotFoundException();
            }
        } catch (EmptyDataException e) {
            return "Введены не все параметры!";
        } catch (EmployeeNotFoundException e) {
            return "Такого сотрудника нет";
        }
        return employee + ". Номер сотрудника: " + employeeBookList.indexOf(employee);
    }

    @Override
    public String hello(Integer e) {
        if (e == null) return "Введите все данные корректно";
        if (employeeBookList.get(e).equals(null)) {
            return "Ошибка, в массиве это поле пустое!";
        } else {
            return "Привет " + employeeBookList.get(e) + " !!";
        }
    }
}
