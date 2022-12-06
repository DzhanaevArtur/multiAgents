package Practices.task5.self.behaviours;

import Practices.task5.self.agents.Boss;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 06.12.2022
 */
@Slf4j
public class CostRec extends WakerBehaviour {

    private final Agent myAgent;

    public CostRec(Agent myAgent, long timeOut) {
        super(myAgent, timeOut);
        this.myAgent = myAgent;
    }

    @Override public void onStart() { log.info("Started"); }

    @Override public void onWake() {
        ACLMessage aclMessage = myAgent.receive();
        if (aclMessage != null) {
            log.info("\"{}\" received", aclMessage.getContent());
            myAgent.addBehaviour(new NewCostGen(myAgent, Boss.class));
        } else block();
    }

    @Override public int onEnd() { log.info("Finished"); return super.onEnd(); }
}
