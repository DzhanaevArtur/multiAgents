package Practices.task5.self.agents;

import Practices.task5.self.behaviours.CostSend;
import Practices.task5.self.behaviours.NewCostsRec;
import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 06.12.2022
 */
@Slf4j
public class Boss extends Agent {

    public final static int FIRST_PRICE = 1000;

    @Override protected void setup() {
        addBehaviour(new CostSend(this, Member1.class, Member2.class, Member3.class, FIRST_PRICE));
        addBehaviour(new NewCostsRec(this, 10L));
    }
}
