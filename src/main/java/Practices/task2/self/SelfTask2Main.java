package Practices.task2.self;

import Practices.task2.example.WorkerProfile;

public class SelfTask2Main {

    public static void main(String[] args) {

        WorkerProfile workerProfile = new WorkerProfile("Artur", 22, 15000);
        System.out.println(workerProfile.profileInfo());
    }
}
