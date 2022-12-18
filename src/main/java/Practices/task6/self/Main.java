package Practices.task6.self;

import Practices.AgentFounder;
import Practices.task6.self.agents.Professor;
import Practices.task6.self.agents.Student;
import jade.core.Runtime;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 15.12.2022
 */
@Slf4j
public class Main {

    public static void main(String[] args) {
//        Runtime runtime = Runtime.instance();
//        runtime.setCloseVM(false);
//        runtime.createMainContainer(AgentFounder.founder(Professor.class, Student.class));
        String[] a = new String[5];
        a[0] = "-gui";
        a[1] = "-agents";
        a[2] = "Professor:Practices.task6.self.agents.Professor;Student1:Practices.task6.self.agents.Student;Student2:Practices.task6.self.agents.Student;Student3:Practices.task6.self.agents.Student;Student4:Practices.task6.self.agents.Student;";
        a[3] = "-services";
        a[4] = "jade.core.event.NotificationService;jade.core.messaging.TopicManagementService;";
        jade.Boot.main(a);
    }
}
