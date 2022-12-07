package Practices.task6.example.behs;

import Practices.task6.example.help.Data;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 07.12.2022
 */
@Slf4j
public class SendQuantity extends WakerBehaviour {

    Data data;

    public SendQuantity(Agent a, long timeout, Data data) { super(a, timeout); this.data = data; }

    @Override protected void onWake() {
        ACLMessage aclMessage = new ACLMessage(ACLMessage.PROPOSE);
        aclMessage.setContent("69");
        aclMessage.setProtocol("quantity");
        aclMessage.addReceiver(data.getTopic());
        getAgent().send(aclMessage);
    }
}
