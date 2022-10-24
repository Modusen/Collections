package pro.sky.HW25.Collections;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class EmployeeServiceTest {
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @AfterEach
    public void afterEach() {
        employeeService.findAll().forEach(employee ->
                employeeService.deleteEmployee(employee.getFirstName(), employee.getLastName()));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void addNegativeTest(String firstName, String lastName, int department, int salary) {
        Employee expected = new Employee(firstName, lastName, department, salary);

        Assertions.assertThat(employeeService.findAll().isEmpty());
        employeeService.addEmployee(firstName, lastName, department, salary);
        Assertions.assertThat(employeeService.findAll())
                .hasSize(1)
                .containsExactly(expected);
        Assertions.assertThat(employeeService.findEmployee(expected.getFirstName(), expected.getLastName()))
                .isNotNull()
                .isEqualTo(expected.getFullName());
    }

    @Test
    public void addNegativeTest2() {
        String firstName = null;
        String lastName = "Ivanov";
        int department = 2;
        int salary = 50000;
        Assertions.assertThatExceptionOfType(IllegalInputException.class)
                .isThrownBy(()->employeeService.addEmployee(firstName, lastName, department, salary));
    }
    @ParameterizedTest
    @MethodSource("params")
    public void addPositiveTest(String firstName, String lastName, int department, int salary) {
        Assertions.assertThat(employeeService.findAll().isEmpty());
        Employee expected = new Employee(firstName, lastName, department, salary);
        employeeService.addEmployee(firstName, lastName, department, salary);
        Assertions.assertThat(employeeService.findAll())
                .hasSize(1)
                .containsExactly(expected);
        Assertions.assertThat(employeeService.findAll().equals(expected));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void deleteNegativeTest(String firstName, String lastName, int department, int salary) {
        Assertions.assertThat(employeeService.findAll().isEmpty());
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()-> employeeService.deleteEmployee("check", "check"));

        Employee expected = new Employee(firstName, lastName, department, salary);
        employeeService.addEmployee(firstName, lastName, department, salary);
        Assertions.assertThat(employeeService.findAll())
                .hasSize(1)
                .containsExactly(expected);

        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()->employeeService.deleteEmployee("check", "check"));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void deletePositiveTest(String firstName, String lastName, int department, int salary) {
        Assertions.assertThat(employeeService.findAll().isEmpty());
        Employee expected = new Employee(firstName, lastName, department, salary);
        employeeService.addEmployee(firstName, lastName, department, salary);
        Assertions.assertThat(employeeService.findAll())
                .hasSize(1)
                .containsExactly(expected);
        employeeService.deleteEmployee(firstName, lastName);
        Assertions.assertThat(employeeService.findAll().isEmpty());
    }
    @ParameterizedTest
    @MethodSource("params")
    public void findPositiveTest(String firstName, String lastName, int department, int salary) {
        Assertions.assertThat(employeeService.findAll().isEmpty());
        Employee expected = new Employee(firstName, lastName, department, salary);
        employeeService.addEmployee(firstName, lastName, department, salary);
        Assertions.assertThat(employeeService.findAll())
                .hasSize(1)
                .containsExactly(expected);

        Assertions.assertThat(employeeService.findEmployee(firstName, lastName)).isEqualTo(expected.getFullName());
        Assertions.assertThat(employeeService.findAll()).hasSize(1);
    }

    public static Stream<Arguments> params() {
        return Stream.of(
                Arguments.of("Ivan", "Ivanov", 1, 55000),
                Arguments.of("Sergey", "Sergeev", 2, 78000),
                Arguments.of("Dmitriy", "Petrov", 1, 64000)
        );
    }
}