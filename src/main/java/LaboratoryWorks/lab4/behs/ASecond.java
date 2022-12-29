package LaboratoryWorks.lab4.behs;

import LaboratoryWorks.lab4.common.LW4Info;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

/**
 * @author Artur Dzhanaev
 * @created 27.12.2022
 */
@Slf4j
public class ASecond extends Behaviour {


//    /** Текущий поставщик */
//    private final Integer timeCounter;
//
//    /** Текущий поставщик */
//    private final Integer maxPrice;
//
//    /** Текущий поставщик */
//    private final Double value;
//
//
//    /** Общие данные */
//    private final LW4Info lw4Info;
//
//    /** Текущий поставщик */
//    private final Agent myAgent;
//
//
//    public ASecond(Agent myAgent, @NotNull LW4Info lw4Info, @NotNull String content) {
//        super(myAgent);
//        this.myAgent = myAgent;
//        this.lw4Info = lw4Info;
//
//        String[] split = content.split(";");
//        this.value       = Double.parseDouble(split[0]);
//        this.maxPrice    = Integer.parseInt  (split[1]);
//        this.timeCounter = Integer.parseInt  (split[2]);
//    }

    /** Основная часть */
    @Override public void action() {
//        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
//        aclMessage.addReceiver(lw4Info.getChat());
//        aclMessage.setProtocol("PROPOSE");
//        aclMessage.setContent(String.format(Locale.US, "%.3f;%d;%d", value, maxPrice, timeCounter));
//        myAgent.send(aclMessage);
//        myAgent.send(aclMessage);
    }

    /** Условие останова */
    @Override public boolean done() { return false; }
}
