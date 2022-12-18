package Practices.task6.exampleSelf.behs.buyer;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author Artur Dzhanaev
 * @created 14.12.2022
 */
@Slf4j
public class Final extends OneShotBehaviour {

    private final Agent myAgent;
    private final Map.Entry<AID, Integer> entrySet;

    public Final(Agent myAgent, Map.Entry <AID, Integer> entrySet) {
        super(myAgent);
        this.myAgent = myAgent;
        this.entrySet = entrySet;
    }


    @Override public void action() {
        ACLMessage aclMessage = new ACLMessage(ACLMessage.CONFIRM);
        AID winner = entrySet.getKey();
        aclMessage.addReceiver(winner);
        aclMessage.setContent(String.valueOf(entrySet.getValue()));
        myAgent.send(aclMessage);
        log.info("\t\tCongratulations to {}", winner.getLocalName());
    }
}
