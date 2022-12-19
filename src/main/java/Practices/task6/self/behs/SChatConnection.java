package Practices.task6.self.behs;

import Practices.task6.self.common.CfgTimes;
import Practices.task6.self.common.Information;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 15.12.2022
 */
@Slf4j
public class SChatConnection extends Behaviour {

    private final CfgTimes cfgTimes;
    private final Information information;
    private final Agent myAgent;

    public SChatConnection(Agent myAgent, Information information, CfgTimes cfgTimes) {
        super(myAgent);
        this.myAgent = myAgent;
        this.information = information;
        this.cfgTimes = cfgTimes;
    }

    /**
     * Регистрируем агентов-студентов с соответствующим описанием
     */
    @Override public void onStart() {
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType("Professor's schedule");
        serviceDescription.setName("Professor's schedule");

        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.addServices(serviceDescription);
        try { DFService.register(myAgent, dfAgentDescription); }
        catch (FIPAException e) { e.printStackTrace(); }
    }

    /**
     * Принимаем название чата от профессора
     */
    @Override public void action() {
        ACLMessage aclMessage = myAgent.receive(MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                MessageTemplate.MatchProtocol("Professor's schedule")));
        if (aclMessage != null) {
            String content = aclMessage.getContent();
            log.info("\t\"{}\" received", content);
            myAgent.addBehaviour(new SFirstMessageReceive(myAgent, information, cfgTimes));
        } else block();
    }

    @Override public boolean done() { return false; }
}
