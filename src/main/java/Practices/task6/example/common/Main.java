package Practices.task6.example.common;

import Practices.AgentFounder;
import Practices.task6.example.agents.Consumer;
import Practices.task6.example.agents.Producer;

import lombok.extern.slf4j.Slf4j;
import jade.core.Runtime;

/**
 * @author Artur Dzhanaev
 * @created 18.12.2022
 */
@Slf4j
public class Main {

    public static void main(String[] args) {
        Runtime runtime = Runtime.instance();
        runtime.setCloseVM(false);
        runtime.createMainContainer(AgentFounder.founder(Consumer.class, Producer.class));
    }
}
