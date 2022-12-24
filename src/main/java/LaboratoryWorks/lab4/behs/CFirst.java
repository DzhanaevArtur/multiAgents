package LaboratoryWorks.lab4.behs;

import LaboratoryWorks.lab4.common.CParser;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class CFirst extends Behaviour {

    private final CParser cParser;
    private final Agent myAgent;

    public CFirst(Agent myAgent, CParser cParser) {
        super(myAgent);
        this.myAgent = myAgent;
        this.cParser = cParser;
    }

    @Override public void action() {

    }

    @Override public boolean done() { return false; }
}
