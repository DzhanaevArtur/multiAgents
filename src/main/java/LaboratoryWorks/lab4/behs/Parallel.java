package LaboratoryWorks.lab4.behs;

import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.WakerBehaviour;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 29.12.2022
 */
@Slf4j
public class Parallel extends ParallelBehaviour {


    private final Agent myAgent;

    public Parallel(Agent myAgent) {
        super(myAgent, WHEN_ANY);
        this.myAgent = myAgent;

        addSubBehaviour(new WakerBehaviour(myAgent, 2_000L) { @Override protected void onWake() { log.info("TimeOut"); } });
        addSubBehaviour(new WakerBehaviour(myAgent, 2_000L) { @Override protected void onWake() { log.info("TimeOut"); } });
    }
}
