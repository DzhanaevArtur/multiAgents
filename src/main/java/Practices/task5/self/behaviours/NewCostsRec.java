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

    @Override protected void onWake() {
        int max = 999, count = 0;
        ACLMessage aclMessage;
        while (count < 2) {
            aclMessage = myAgent.receive();
            if (aclMessage != null) {
                int actual = Integer.parseInt(aclMessage.getContent());
                log.info("\"{}\" received", actual);
                if (actual > max) {
                    max = actual;
                    finalAgent = aclMessage.getSender().getLocalName();
                    count++;
                }
            } else block();
        }
        myAgent.addBehaviour(new ToWinner(myAgent, finalAgent, max));
    }
}
