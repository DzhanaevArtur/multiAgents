package Practices.task6.exampleSelf;

import Practices.AgentFounder;
import Practices.task6.exampleSelf.agents.Buyer;
import Practices.task6.exampleSelf.agents.Seller;
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
        runtime.createMainContainer(AgentFounder.founder(Buyer.class, Seller.class));
    }
}
