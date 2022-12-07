package Practices.task6.example.behs;

import Practices.task6.example.help.Data;
import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.WakerBehaviour;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 07.12.2022
 */
@Slf4j
public class ReceiveParallelBeh extends ParallelBehaviour {

    Data data;

    public ReceiveParallelBeh (Agent a, Data data) {
        super(a, WHEN_ANY);
        this.data = data;
        addSubBehaviour(new ReceiveAnswers(data));
        addSubBehaviour(new WakerBehaviour(getAgent(), 2000) {
            @Override protected void onWake() { log.info("Replies timed out"); }
        });
    }
}

