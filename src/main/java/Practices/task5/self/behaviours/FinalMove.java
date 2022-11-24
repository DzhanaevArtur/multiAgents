package Practices.task5.self.behaviours;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class FinalMove extends OneShotBehaviour {

    @Override
    public void action() {
        ACLMessage costFinal = new ACLMessage(ACLMessage.INFORM);
        if (SecondMove2.newCost2 > SecondMove3.newCost3 && SecondMove2.newCost2 > SecondMove4.newCost4) {
            costFinal.setContent(String.valueOf(SecondMove2.newCost2));
            costFinal.addReceiver(new AID("Participant2", false));
        } else if (SecondMove3.newCost3 > SecondMove4.newCost4 && SecondMove3.newCost3 > SecondMove2.newCost2) {
            costFinal.setContent(String.valueOf(SecondMove3.newCost3));
            costFinal.addReceiver(new AID("Participant3", false));
        } else if (SecondMove4.newCost4 > SecondMove2.newCost2 && SecondMove4.newCost4 > SecondMove3.newCost3) {
            costFinal.setContent(String.valueOf(SecondMove4.newCost4));
            costFinal.addReceiver(new AID("Participant4", false));
        }
        getAgent().send(costFinal);
    }
}
