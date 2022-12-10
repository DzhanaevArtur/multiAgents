package laboratoryWorks.lab3.behs;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 10.12.2022
 */
@Slf4j
public class FirstReceive extends OneShotBehaviour {

    private final Agent myAgent;

    public FirstReceive(Agent myAgent) {
        super(myAgent);
        this.myAgent = myAgent;
    }

    @Override public void action() {
        ACLMessage aclMessage = myAgent.receive();
        if (aclMessage != null) log.info("\"{}\" was received", aclMessage.getContent());
        else block();
    }
}
