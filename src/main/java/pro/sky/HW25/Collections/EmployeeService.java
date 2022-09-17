package pro.sky.HW25.Collections;

public interface EmployeeService {
    public String addEmployee(String firstName, String lastName);
    public String deleteEmployee(String firstName, String lastName);
    public String findEmployee(String firstName, String lastName);
    public String hello(Integer e);

}
