package LaboratoryWorks.lab4.behs;

import LaboratoryWorks.lab4.common.LW;
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
    private final LW lw;
    private final Agent myAgent;

    public CEnergyBuy(Agent myAgent, LW lw) {
        super(myAgent);
        this.myAgent = myAgent;
        this.lw = lw;
    }

    @Override public void action() {
        switch (myAgent.getLocalName()) {
            case "Consumer_1" -> aclMessageSending(lw.getCPowerPerHour1());
            case "Consumer_2" -> aclMessageSending(lw.getCPowerPerHour2());
            case "Consumer_3" -> aclMessageSending(lw.getCPowerPerHour3());
        }
        trigger = true;
    }

    private synchronized void aclMessageSending(@NotNull List<Double> list) {
        for (Double d : list) {
            ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
            aclMessage.addReceiver(lw.getChat());
            aclMessage.setProtocol("EnergyBuy");
            aclMessage.setContent(String.format(Locale.US, "%.3f", d));
            myAgent.doWait(50L);
            myAgent.send(aclMessage);
        }
    }

    @Override public boolean done() { return trigger; }
}
