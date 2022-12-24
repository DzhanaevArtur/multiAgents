package LaboratoryWorks.lab3.common;

import LaboratoryWorks.lab3.behs.Receive;
import LaboratoryWorks.lab3.behs.Send;
import Practices.AutoRunnableAgent;
import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 22.12.2022
 */
@Slf4j
@AutoRunnableAgent(name = "Node", copy = 12)
public class Node extends Agent {

    @Override protected void setup() {
        if (getLocalName().equals("Node_1")) addBehaviour(new Send(this, Main.res(this.getLocalName()), 0));
        else addBehaviour(new Receive(this));
    }
}
