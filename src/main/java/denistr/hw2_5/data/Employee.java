package denistr.hw2_5.data;
import java.util.Objects;
public class Employee {
    private final String firstName;
    private final String lastName;
    private int dep;
    private  int salary;
    public Employee(String firstName, String lastName, int dep, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dep = dep;
        this.salary = salary;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    public int getDep() {
        return dep;
    }
    public void setDep(int dep) {
        this.dep = dep;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return dep == employee.dep &&
                salary == employee.salary &&
                firstName.equals(employee.firstName) &&
                lastName.equals(employee.lastName);
    }
    @Override
    public int hashCode() {
        return Objects.hash(firstName + " " + lastName);
    }
    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dep=" + dep +
                ", salary=" + salary +
                '}';
    }
}
