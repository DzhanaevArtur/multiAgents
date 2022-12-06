package Practices.task5.self.behaviours;

import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 06.12.2022
 */
@Slf4j
public class NewCostsRec extends WakerBehaviour {

    public String finalAgent;
    private final Agent myAgent;

    public NewCostsRec(Agent myAgent, long timeOut) {
        super(myAgent, timeOut);
        this.myAgent = myAgent;
    }

    @Override public void onStart() { log.info("Started"); }

    @Override protected void onWake() {
        int receivedCost = 999, count = 0;
        ACLMessage aclMessage = myAgent.receive();
        if (aclMessage != null) {
            while (count < 3) {
                if (Integer.parseInt(aclMessage.getContent()) > receivedCost) {
                    receivedCost = Integer.parseInt(aclMessage.getContent());
                    finalAgent = aclMessage.getSender().getLocalName();
                }
                count++;
            }
            log.info("\"{}\" received", aclMessage.getContent());
            myAgent.addBehaviour(new ToWinner(myAgent, finalAgent, receivedCost));
        } else block();
    }

    @Override public int onEnd() { log.info("Finished"); return super.onEnd(); }
}
