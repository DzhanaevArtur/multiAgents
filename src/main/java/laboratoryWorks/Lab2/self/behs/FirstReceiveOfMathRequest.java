package laboratoryWorks.Lab2.self.behs;

import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 07.12.2022
 */
@Slf4j
public class FirstReceiveOfMathRequest extends WakerBehaviour {

    private final Agent myAgent;

    public FirstReceiveOfMathRequest(Agent myAgent, long timeOut) { super(myAgent, timeOut); this.myAgent = myAgent; }

    @Override public void onWake() {
        ACLMessage aclMessage = myAgent.receive();
        if (aclMessage != null) {
            String content = aclMessage.getContent();
            int sub = content.indexOf(';');
            double x = Double.parseDouble(content.substring(0, sub));
            double delta = Double.parseDouble(content.substring(sub + 1));
            log.info("X received; it's equals \"{}\"", x);
            log.info("DELTA received; it's equals \"{}\"", delta);
            myAgent.addBehaviour(new SecondSendOfMathRequest());
        } else block();
    }
}
