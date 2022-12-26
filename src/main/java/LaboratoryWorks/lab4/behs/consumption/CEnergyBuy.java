package LaboratoryWorks.lab4.behs.consumption;

import LaboratoryWorks.lab4.common.LW4Info;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Locale;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class CEnergyBuy extends Behaviour {

    private Boolean trigger = false;
    private final LW4Info lw4Info;
    private final Agent myAgent;

    public CEnergyBuy(Agent myAgent, LW4Info lw4Info) {
        super(myAgent);
        this.myAgent = myAgent;
        this.lw4Info = lw4Info;
    }

    @Override public void action() {
        switch (myAgent.getLocalName()) {
            case "Consumer_1" -> aclMessageSending(lw4Info.getMPEI());
            case "Consumer_2" -> aclMessageSending(lw4Info.getFoodIndustryFactory());
            case "Consumer_3" -> aclMessageSending(lw4Info.getShoeFactory());
        }
        trigger = true;
    }

    private synchronized void aclMessageSending(@NotNull List<Double> list) {
        for (Double d : list) {
            ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
            aclMessage.addReceiver(lw4Info.getChat());
            aclMessage.setProtocol("EnergyBuy");
            aclMessage.setContent(String.format(Locale.US, "%.3f", d));
            myAgent.doWait(50L);
            myAgent.send(aclMessage);
        }
    }

    @Override public boolean done() { return trigger; }
}
