package Practices.task6.example.behs;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 14.12.2022
 */
@Slf4j
public class SellerFinalReceive extends OneShotBehaviour {

    private final Agent myAgent;

    public SellerFinalReceive(Agent myAgent) { super(myAgent); this.myAgent = myAgent; }

    @Override public void action() {
        ACLMessage aclMessage = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.AGREE));
        if (aclMessage != null) log.info("Congratulations message received. Price of winner equals {}", aclMessage.getContent());
        else block();
    }
}
