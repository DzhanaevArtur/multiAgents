package Practices.task6.example.behs;

import Practices.task6.example.common.Data;
import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.WakerBehaviour;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 18.12.2022
 */
@Slf4j
public class ReceiveParallel extends ParallelBehaviour {

    Data data;

    public ReceiveParallel(Agent myAgent, Data data) {
        super(myAgent, WHEN_ANY);
        this.data = data;
        addSubBehaviour(new ReceiveAnswers(myAgent, data));
        addSubBehaviour(new WakerBehaviour(myAgent, 2_000L) { @Override protected void onWake() { log.info("TimeOut"); } });
    }
}
