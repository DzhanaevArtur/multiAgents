package Practices.task6.example.behs;

import Practices.task6.example.common.Data;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 18.12.2022
 */
@Slf4j
public class SendQuantity extends WakerBehaviour {

    Data data;
    private final Agent myAgent;

    public SendQuantity(Agent myAgent, Data data, long timeout) {
        super(myAgent, timeout);
        this.myAgent = myAgent;
        this.data = data;
    }

    @Override protected void onWake() {
        ACLMessage aclMessage = new ACLMessage(ACLMessage.PROPOSE);
        aclMessage.setContent("20");
        aclMessage.setProtocol("quantity");
        aclMessage.addReceiver(data.getTopic());
        myAgent.send(aclMessage);
    }
}
