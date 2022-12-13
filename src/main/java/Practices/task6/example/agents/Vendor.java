package Practices.task6.example.agents;

import Practices.task6.example.behs.MessageReceiver;
import Practices.task6.example.behs.VendorServer;
import jade.core.Agent;
import laboratoryWorks.lab3.common.AutoRunnableAgent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 13.12.2022
 */
@Slf4j
@AutoRunnableAgent(name = "Vendor", copy = 2)
public class Vendor extends Agent {

    protected void setup() {
        log.info("Born");
        addBehaviour(new VendorServer(this));
        addBehaviour(new MessageReceiver(this));
    }
}
