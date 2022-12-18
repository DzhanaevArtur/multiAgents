package Practices.task6.example.behs;

import Practices.task6.example.common.AgentPrice;
import Practices.task6.example.common.Data;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 18.12.2022
 */
@Slf4j
public class ReceiveAnswers extends Behaviour {

    Data data;
    int count = 0;
    private final Agent myAgent;
    
    public ReceiveAnswers(Agent myAgent, Data data) {
        super(myAgent);
        this.myAgent = myAgent;
        this.data = data;
    }

    @Override public void action() {
        ACLMessage aclMessage = myAgent.receive(MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                MessageTemplate.MatchProtocol("price")));
        if (aclMessage != null){
            data.getAgentPrices().add(new AgentPrice(Integer.parseInt(aclMessage.getContent()), aclMessage.getSender().getLocalName()));
            count++;
        } else block();
    }
    
    @Override public boolean done() { return count == data.getNumberOfProducers(); }
}
