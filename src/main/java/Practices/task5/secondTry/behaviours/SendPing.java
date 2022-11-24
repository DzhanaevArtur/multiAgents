package Practices.task5.secondTry.behaviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * @author Artur Dzhanaev
 * @created 24.11.2022
 */
public class SendPing extends WakerBehaviour {

    private AID receiver;

    public SendPing(Agent a, long timeout, AID receiver) {
        super(a, timeout);
        this.receiver = receiver;
    }

    @Override
    protected void onWake() {
        ACLMessage aclMessage = new ACLMessage(ACLMessage.CFP);
        aclMessage.setContent("Ping");
        aclMessage.setProtocol("Ping");
        aclMessage.addReceiver(receiver);
        myAgent.send(aclMessage);
    }
}
