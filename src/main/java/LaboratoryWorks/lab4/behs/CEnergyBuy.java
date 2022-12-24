package LaboratoryWorks.lab4.behs;

import LaboratoryWorks.lab4.common.CParser;
import LaboratoryWorks.lab4.common.LW;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class CEnergyBuy extends Behaviour {

    private Boolean trigger = false;
    private final CParser cParser;
    private final LW lw;
    private final Agent myAgent;

    public CEnergyBuy(Agent myAgent, CParser cParser, LW lw) {
        super(myAgent);
        this.myAgent = myAgent;
        this.cParser = cParser;
        this.lw = lw;
    }

    @Override public void action() {
        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
        aclMessage.addReceiver(lw.getChat());
        aclMessage.setProtocol("EnergyBuy");
        aclMessage.setContent(String.format("%d", cParser.getP0()));
        myAgent.send(aclMessage);
        trigger = true;
    }

    @Override public boolean done() { return trigger; }
}
