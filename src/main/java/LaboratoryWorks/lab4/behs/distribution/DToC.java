package LaboratoryWorks.lab4.behs.distribution;

import LaboratoryWorks.lab4.common.LW4Info;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class DToC extends Behaviour {

    private Boolean trigger = false;
    private final LW4Info lw4Info;
    private final Agent myAgent;

    public DToC(Agent myAgent, LW4Info lw4Info) {
        super(myAgent);
        this.myAgent = myAgent;
        this.lw4Info = lw4Info;
    }

    /**  */
    @Override public void action() {

    }

    /**  */
    @Override public boolean done() { return trigger; }
}
