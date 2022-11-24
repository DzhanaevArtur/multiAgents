package Practices.task5.self.behaviours;

import Practices.task5.self.agents.Initiator1;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class FirstMove extends OneShotBehaviour {
    @Override
    public void action() {
        ACLMessage costFirst = new ACLMessage(ACLMessage.INFORM);
        costFirst.setContent(String.valueOf(Initiator1.COST));
        costFirst.addReceiver(new AID("Participant2", false));
        costFirst.addReceiver(new AID("Participant3", false));
        costFirst.addReceiver(new AID("Participant4", false));
        getAgent().send(costFirst);
    }
}
