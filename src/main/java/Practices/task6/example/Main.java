package Practices.task6.example;

import Practices.AgentFounder;
import Practices.task6.example.agents.Client;
import Practices.task6.example.agents.Vendor;
import jade.core.Runtime;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 13.12.2022
 */
@Slf4j
public class Main {

    public static void main(String[] args) {
        Runtime runtime = Runtime.instance();
        runtime.setCloseVM(true);
        runtime.createMainContainer(AgentFounder.founder(Client.class, Vendor.class));
    }
}
