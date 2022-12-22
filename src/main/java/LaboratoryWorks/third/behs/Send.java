package LaboratoryWorks.third.behs;

import LaboratoryWorks.third.common.Main;
import LaboratoryWorks.third.common.Neighbor;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 22.12.2022
 */
@Slf4j
public class Send extends Behaviour {

    private Boolean trigger = false;
    private final Integer add;
    private final Neighbor neighbor;
    private final Agent myAgent;

    public Send(Agent myAgent, Neighbor neighbor, Integer add) {
        super(myAgent);
        this.myAgent = myAgent;
        this.neighbor = neighbor;
        this.add = add;
    }

    @Override public void action() {
        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
        aclMessage.addReceiver(new AID(neighbor.getId(), false));
        aclMessage.setContent(String.valueOf(neighbor.getLength() + add));
        aclMessage.setProtocol("RoadMap");
        myAgent.send(aclMessage);
        Main.PARTICIPANTS.add(myAgent.getLocalName());
        log.info("\t\"{}\" sent to {}", aclMessage.getContent(), ((AID) aclMessage.getAllReceiver().next()).getLocalName());
        trigger = true;
    }

    @Override public boolean done() { return trigger; }
}
