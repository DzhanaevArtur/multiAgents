package Practices.task5.self.behaviours;

import Practices.task5.self.agents.Initiator1;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Random;

public class SecondMove3 extends OneShotBehaviour {

    public static double newCost3;

    @Override
    public void action() {
        ACLMessage costSecond3 = new ACLMessage(ACLMessage.INFORM);
        Random random = new Random();
        newCost3 = random.nextDouble(Initiator1.COST, 3 * Initiator1.COST);
        costSecond3.setContent(String.valueOf(newCost3));
        costSecond3.addReceiver(new AID("Initiator1", false));
        getAgent().send(costSecond3);
    }
}
