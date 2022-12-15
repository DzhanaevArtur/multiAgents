package laboratoryWorks.lab3.common;

import Practices.AgentFounder;
import jade.core.Runtime;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 11.12.2022
 */
@Slf4j
public class Main {

    public static void main(String[] args) {
        Runtime runtime = Runtime.instance();
        runtime.setCloseVM(true);
        runtime.createMainContainer(AgentFounder.founder(Node.class));
    }
}
