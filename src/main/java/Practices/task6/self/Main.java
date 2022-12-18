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
        Runtime runtime = Runtime.instance();
        runtime.setCloseVM(false);
        runtime.createMainContainer(AgentFounder.founder(Professor.class, Student.class));
    }
}
