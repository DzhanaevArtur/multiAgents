package LaboratoryWorks.lab3.common;

import Practices.AutoRunnableAgent;
import jade.core.Agent;
import LaboratoryWorks.lab3.behs.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 08.12.2022
 */
@Slf4j
@AutoRunnableAgent(name = "Node", copy = 12)
public class Node extends Agent {

    @Override protected void setup() {
        String localName = this.getLocalName();
        String destination = XmlInfo.choose(localName).keySet().stream().toList().get(0);

        if (localName.equals(XmlInfo.start)) {
            addBehaviour(new Send(this, destination, lengths(0)));
        }
        else if (!localName.equals(XmlInfo.finish)) {
            for (int i = 0; i < 10; i++) {
                if (localName.equals(neighbours(i))) {
                    addBehaviour(new Receive(this));
                    addBehaviour(new Send(this, destination, lengths(i)));
                    if (destination.equals(XmlInfo.finish)) break;
                }
            }
        }
        else addBehaviour(new Receive(this));
    }

    private String neighbours(int i) {
        return i == 0 ? XmlInfo.start :
               i == 1 ? XmlInfo.choose(XmlInfo.start).keySet().stream().toList().get(0) :
                        XmlInfo.choose(neighbours(i - 1)).keySet().stream().toList().get(0);
    }

    private int lengths(int i) {
        return i == 0 ? XmlInfo.choose(this.getLocalName()).values().stream().toList().get(0) :
               i == 1 ? XmlInfo.choose(neighbours(0)).values().stream().toList().get(0) + lengths(0) :
                        XmlInfo.choose(neighbours(i - 1)).values().stream().toList().get(0) + lengths(i - 1);
    }
}
