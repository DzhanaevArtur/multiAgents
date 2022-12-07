package Practices.task6.example.agents;

import Practices.task6.example.behs.ConsumerFSM;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 07.12.2022
 */
@Slf4j
public class Consumer extends Agent {

    @Override protected void setup() {
        addBehaviour(new WakerBehaviour(this, 5_000L) {
            @Override protected void onWake() { getAgent().addBehaviour(new ConsumerFSM());
            }
        });
    }
}
