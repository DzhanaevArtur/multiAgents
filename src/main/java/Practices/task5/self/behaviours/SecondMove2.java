package Practices.task5.self.behaviours;

import Practices.task5.self.agents.Initiator1;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Random;

public class SecondMove2 extends OneShotBehaviour {

    public static double newCost2;

    @Override
    public void action() {
        ACLMessage costSecond2 = new ACLMessage(ACLMessage.INFORM);
        Random random = new Random();
        newCost2 = random.nextDouble(Initiator1.COST, 3 * Initiator1.COST);
        costSecond2.setContent(String.valueOf(newCost2));
        costSecond2.addReceiver(new AID("Initiator1", false));
        getAgent().send(costSecond2);
    }
}
