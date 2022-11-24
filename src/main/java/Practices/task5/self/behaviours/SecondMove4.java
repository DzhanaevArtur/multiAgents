package Practices.task5.self.behaviours;

import Practices.task5.self.agents.Initiator1;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Random;

public class SecondMove4 extends OneShotBehaviour {

    public static double newCost4;

    @Override
    public void action() {
        ACLMessage costSecond4 = new ACLMessage(ACLMessage.INFORM);
        Random random = new Random();
        newCost4 = random.nextDouble(Initiator1.COST, 3 * Initiator1.COST);
        costSecond4.setContent(String.valueOf(newCost4));
        costSecond4.addReceiver(new AID("Initiator1", false));
        getAgent().send(costSecond4);
    }
}
