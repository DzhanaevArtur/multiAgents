package Practices.SecondTask;

import lombok.Getter;
import lombok.Setter;

public class DriverProfile extends WorkerProfile{

    @Getter @Setter
    String drivingLicense;

    public DriverProfile(String name, int age, int salary, String drivingLicense) {
        super(name, age, salary);
        this.drivingLicense = drivingLicense;
    }
}
