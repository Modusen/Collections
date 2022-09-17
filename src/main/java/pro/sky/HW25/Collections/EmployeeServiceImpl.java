package pro.sky.HW25.Collections;

import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private Employee[] employeeBook = {
            new Employee("Константин", "Эрнст"),
            new Employee("Сергей", "Захаров"),
            new Employee("Илья", "Муромцев"),
            new Employee(null, null),
            new Employee("Добрыня", "Никитовцев"),
            new Employee("Тугарин", "Змей"),
            new Employee("Змей", "Горыновский"),
            new Employee(null, null),
            new Employee("Джек", "Воробьев"),
            new Employee("Элизабет", "Свонс"),
    };

    @Override
    public String addEmployee(String firstName, String lastName) {
        int counter;
        try {
            if (firstName == "" || lastName == "") {
                throw new EmptyDataException();
            }
            for (counter = 0; counter < employeeBook.length; counter++) {
                if (employeeBook[counter].getFirstName() == null) {
                    employeeBook[counter].setFirstName(firstName);
                    employeeBook[counter].setLastName(lastName);
                    break;
                } else if (employeeBook[counter].getFirstName().equals(firstName)
                        && employeeBook[counter].getLastName().equals(lastName)) {
                    throw new EmployeeAlreadyAddedException();
                }
            }
            if (counter >= employeeBook.length) {
                throw new StorageIsFullException();
            }
        } catch (EmptyDataException e) {
            return "Введены не все параметры!";
        } catch (StorageIsFullException e) {
            return "Ошибка, нет свободного места для добавления";
        } catch (EmployeeAlreadyAddedException e) {
            return "Ошибка, сотрудник уже добавлен";
        }
        return "Сотрудник " + employeeBook[counter] + " добавлен! Номер сотрудника: " + (counter + 1);
    }

    @Override
    public String deleteEmployee(String firstName, String lastName) {
        int counter;
        try {
            if (firstName == "" || lastName == "") {
                throw new EmptyDataException();
            }
            for (counter = 0; counter < employeeBook.length; counter++) {
                if (employeeBook[counter].getFirstName() != null && employeeBook[counter].getLastName() != null &&
                        employeeBook[counter].getFirstName().equals(firstName) &&
                        employeeBook[counter].getLastName().equals(lastName)) {
                    employeeBook[counter].setFirstName(null);
                    employeeBook[counter].setLastName(null);
                    break;
                }
            }
            if (counter >= employeeBook.length) {
                throw new EmployeeNotFoundException();
            }
        } catch (EmptyDataException e) {
            return "Введены не все параметры!";
        } catch (EmployeeNotFoundException e) {
            return "Сотрудник с таким именем и фамилией не найден";
        }
        return "Сотрудник" + employeeBook[counter] + " успешно удален с позиции " + (counter + 1);
    }

    @Override
    public String findEmployee(String firstName, String lastName) {
        int counter;
        try {
            if (firstName == "" || lastName == "") {
                throw new EmptyDataException();
            }
            for (counter = 0; counter < employeeBook.length; counter++) {
                if (employeeBook[counter].getFirstName() != null && employeeBook[counter].getLastName() != null &&
                        employeeBook[counter].getFirstName().equals(firstName) && employeeBook[counter].getLastName().equals(lastName)) {
                    break;
                }
            }
            if (counter >= employeeBook.length) {
                throw new EmployeeNotFoundException();
            }
        } catch (EmptyDataException e) {
            return "Введены не все параметры!";
        } catch (EmployeeNotFoundException e) {
            return "Такого сотрудника нет";
        }
        return employeeBook[counter] + ". Номер сотрудника: " + (counter + 1);
    }

    @Override
    public String hello(Integer e) {
        if (e == null) return "Введите все данные корректно";
        if (employeeBook[e].getFirstName() == null || employeeBook[e].getLastName() == null) {
            return "Ошибка, в массиве это поле пустое!";
        } else {
            return "Привет " + employeeBook[e] + " !!";
        }
    }
}
