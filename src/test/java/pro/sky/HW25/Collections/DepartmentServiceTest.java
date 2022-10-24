package pro.sky.HW25.Collections;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeServiceImpl employeeServiceImpl;

    @InjectMocks
    private DepartmentServiceImpl departmentServiceImpl;

    @BeforeEach
    public void beforeEach() {
        List<Employee> employees = List.of(
                new Employee("Василий", "Васильев", 55_000, 1),
                new Employee("Andrey", "Andreev", 65_000, 1),
                new Employee("Иван", "Иванов", 45_000, 2),
                new Employee("Mary", "Ivanova", 50_000, 2),
                new Employee("Ирина", "Андреева", 47_000, 2)
        );
        when(employeeServiceImpl.findAll()).thenReturn(employees);
    }

    @ParameterizedTest
    @MethodSource("employeeWithMaxSalaryParams")
    public void employeeWithMaxSalaryPositiveTest(int department, Employee expected) {
        Assertions.assertThat(departmentServiceImpl.getMaximumSalaryInDepartment(department)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("employeeWithMinSalaryParams")
    public void employeeWithMinSalaryPositiveTest(int department, Employee expected) {
        Assertions.assertThat(departmentServiceImpl.getMinimumSalaryInDepartment(department)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("employeesFromDepartmentParams")
    public void employeesFromDepartmentPositiveTest(int department, List<Employee> expexted) {
        Assertions.assertThat(departmentServiceImpl.employeeByDepartment(department)).isEqualTo(expexted);
    }

    @Test
    public void employeeByEveryDepartmentTest() {
        Assertions.assertThat(departmentServiceImpl.printEmployeeByEveryDepartment()).containsAllEntriesOf(
                Map.of(
                        1, List.of(new Employee ("Василий", "Васильев", 55_000, 1),
                                new Employee ("Andrey", "Andreev", 65_000, 1)),
                        2, List.of(new Employee("Иван", "Иванов", 45_000, 2),
                                new Employee("Mary", "Ivanova", 50_000, 2),
                                new Employee("Ирина", "Андреева", 47_000, 2))
                )
        );

    }

    public static Stream<Arguments> employeeWithMaxSalaryParams() {
        return Stream.of(
                Arguments.of(1, new Employee ("Andrey", "Andreev", 65_000, 1)),
                Arguments.of(2, new Employee ("Mary", "Ivanova", 50_000, 2))
        );
    }

    public static Stream<Arguments> employeeWithMinSalaryParams() {
        return Stream.of(
                Arguments.of(1, new Employee ("Василий", "Васильев", 55_000, 1)),
                Arguments.of(2, new Employee ("Иван", "Иванов", 45_000, 2))
        );
    }

    public static Stream<Arguments> employeesFromDepartmentParams() {
        return Stream.of(
                Arguments.of(1, List.of(new Employee ("Василий", "Васильев", 55_000, 1),
                                                  new Employee ("Andrey", "Andreev", 65_000, 1)),
                Arguments.of(2, List.of(new Employee("Иван", "Иванов", 45_000, 2),
                                                  new Employee("Mary", "Ivanova", 50_000, 2),
                                                  new Employee("Ирина", "Андреева", 47_000, 2))),
                Arguments.of(3, Collections.emptyList())
        ));
    }
}
