package Practices.task2.example;

import lombok.Getter;
import lombok.Setter;

public class WorkerProfile {

    @Getter @Setter
    protected String name;
    @Getter @Setter
    protected int age;
    @Getter @Setter
    protected int salary;

    public WorkerProfile(String name, int age, int salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String profileInfo() {
        return "{ Name: " + getName() + ", age: " + getAge() + ", salary: " + getSalary() + " }";
    }
}
