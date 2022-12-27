package LaboratoryWorks.lab4.behs;

import LaboratoryWorks.lab4.common.LW4Info;
import LaboratoryWorks.lab4.common.Main;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * @author Artur Dzhanaev
 * @created 27.12.2022
 */
@Slf4j
public class DSearchP extends Behaviour {


    /** Триггер */
    private Boolean trigger = false;

    /** Участники чата */
    private final Set<AID> users;

    /** Текущий поставщик */
    private final Agent myAgent;


    public DSearchP(Agent myAgent, @NotNull LW4Info lw4Info) {
        super(myAgent);
        this.myAgent = myAgent;
        users = lw4Info.getUsers();
    }

    /** Предварительно ищем производителей */
    @Override public void onStart() {
        DFAgentDescription[] search;
        try { search = Main.search(myAgent); }
        catch (FIPAException e) { throw new RuntimeException(e); }
        for (DFAgentDescription d : search) users.add(d.getName());
    }

    /** Основная часть */
    @Override public void action() {
        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
        for (AID user : users) aclMessage.addReceiver(user);
        aclMessage.setProtocol("Chat");
        aclMessage.setContent("Chat");
        myAgent.send(aclMessage);
        trigger = true;
    }

    /** Условие останова */
    @Override public boolean done() { return trigger; }
}
