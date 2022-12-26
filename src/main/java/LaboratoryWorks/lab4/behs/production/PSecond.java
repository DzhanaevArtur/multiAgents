package LaboratoryWorks.lab4.behs.production;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class PSecond extends Behaviour {

    private final Agent myAgent;

    public PSecond(Agent myAgent) {
        super(myAgent);
        this.myAgent = myAgent;
    }

    @Override public void action() {

    }

    @Override public boolean done() { return false; }
}
