package pro.sky.HW25.Collections;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private List<Employee> employeeBookList = List.of(
            new Employee("Константин", "Эрнст"),
            new Employee("Сергей", "Захаров"),
            new Employee("Илья", "Муромцев"),
            new Employee(null, null),
            new Employee("Добрыня", "Никитовцев"),
            new Employee("Тугарин", "Змей"),
            new Employee("Змей", "Горыновский"),
            new Employee(null, null),
            new Employee("Джек", "Воробьев"),
            new Employee("Элизабет", "Свонс"));
    @Override
    public String printAllList(Integer i) {
        return this.employeeBookList.toString();
    }

    @Override
    public String addEmployee(String firstName, String lastName) {
        int counter;
        try {
            if (firstName == "" || lastName == "") {
                throw new EmptyDataException();
            }
            for (counter = 0; counter < employeeBookList.size(); counter++) {
                if (employeeBookList.get(counter).getFirstName() == null && employeeBookList.get(counter).getLastName() == null) {
                    employeeBookList.get(counter).setFirstName(firstName);
                    employeeBookList.get(counter).setLastName(lastName);
                    break;
                } else if (employeeBookList.get(counter).getFirstName().equals(firstName)
                        && employeeBookList.get(counter).getLastName().equals(lastName)) {
                    throw new EmployeeAlreadyAddedException();
                }
            }
            if (counter >= employeeBookList.size()) {
                throw new StorageIsFullException();
            }
        } catch (EmptyDataException e) {
            return "Введены не все параметры!";
        } catch (StorageIsFullException e) {
            return "Ошибка, нет свободного места для добавления";
        } catch (EmployeeAlreadyAddedException e) {
            return "Ошибка, сотрудник уже добавлен";
        }
        return "Сотрудник " + employeeBookList.get(counter) + " добавлен! Номер сотрудника: " + (counter + 1);
    }

    @Override
    public String deleteEmployee(String firstName, String lastName) {
        int counter;
        try {
            if (firstName == "" || lastName == "") {
                throw new EmptyDataException();
            }
            for (counter = 0; counter < employeeBookList.size(); counter++) {
                if (employeeBookList.get(counter).getFirstName() != null && employeeBookList.get(counter).getLastName() != null &&
                        employeeBookList.get(counter).getFirstName().equals(firstName) &&
                        employeeBookList.get(counter).getLastName().equals(lastName)) {
                    employeeBookList.get(counter).setFirstName(null);
                    employeeBookList.get(counter).setLastName(null);
                    break;
                }
            }
            if (counter >= employeeBookList.size()) {
                throw new EmployeeNotFoundException();
            }
        } catch (EmptyDataException e) {
            return "Введены не все параметры!";
        } catch (EmployeeNotFoundException e) {
            return "Сотрудник с таким именем и фамилией не найден";
        }
        return "Сотрудник " + firstName + " " + lastName + " успешно удален с позиции " + (counter + 1) +" " + employeeBookList.get(counter);
    }

    @Override
    public String findEmployee(String firstName, String lastName) {
        int counter;
        try {
            if (firstName == "" || lastName == "") {
                throw new EmptyDataException();
            }
            for (counter = 0; counter < employeeBookList.size(); counter++) {
                if (employeeBookList.get(counter).getFirstName() != null && employeeBookList.get(counter).getLastName() != null &&
                        employeeBookList.get(counter).getFirstName().equals(firstName) && employeeBookList.get(counter).getLastName().equals(lastName)) {
                    break;
                }
            }
            if (counter >= employeeBookList.size()) {
                throw new EmployeeNotFoundException();
            }
        } catch (EmptyDataException e) {
            return "Введены не все параметры!";
        } catch (EmployeeNotFoundException e) {
            return "Такого сотрудника нет";
        }
        return employeeBookList.get(counter) + ". Номер сотрудника: " + (counter + 1);
    }

    @Override
    public String hello(Integer e) {
        if (e == null) return "Введите все данные корректно";
        if (employeeBookList.get(e).getFirstName() == null || employeeBookList.get(e).getLastName() == null) {
            return "Ошибка, в массиве это поле пустое!";
        } else {
            return "Привет " + employeeBookList.get(e) + " !!";
        }
    }
}
