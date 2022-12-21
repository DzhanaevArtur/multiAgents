package LaboratoryWorks.Lab2.common;

import Practices.AgentFounder;
import jade.core.Runtime;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 21.12.2022
 */
@Slf4j
public class Main {

    public static void main(String[] args) {
        Runtime runtime = Runtime.instance();
        runtime.setCloseVM(false);
        runtime.createMainContainer(AgentFounder.founder(FA.class));
    }
}
