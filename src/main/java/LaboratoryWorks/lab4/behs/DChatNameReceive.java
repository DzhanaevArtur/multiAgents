package LaboratoryWorks.lab4.behs;

import LaboratoryWorks.lab4.common.Main;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class DChatNameReceive extends Behaviour {

    private Boolean trigger = false;
    private final Agent myAgent;

    public DChatNameReceive(Agent myAgent) {
        super(myAgent);
        this.myAgent = myAgent;
    }

    @Override public void action() {
        ACLMessage chatNameReceive = myAgent.receive(MessageTemplate.and(
                MessageTemplate.MatchProtocol(Main.CHAT),
                MessageTemplate.MatchPerformative(ACLMessage.INFORM)));
        if (chatNameReceive != null) {
            log.info("Chat name received");
            myAgent.addBehaviour(new DSecond(myAgent));
            trigger = true;
        } else block();
    }

    @Override public boolean done() { return trigger; }
}
