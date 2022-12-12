package laboratoryWorks.lab3.behs;

import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 12.12.2022
 */
@Slf4j
public class FourthR  extends WakerBehaviour {

    private final Agent myAgent;

    public FourthR(Agent myAgent, long timeout) { super(myAgent, timeout); this.myAgent = myAgent; }

    @Override protected void onWake() {
        ACLMessage aclMessage = myAgent.receive();
        if (aclMessage != null) {
            log.info("\t\"{}\" was received", aclMessage.getContent());
            log.warn("\tProgram finished! Total length is {}", aclMessage.getContent());
        } else block();
    }
}
