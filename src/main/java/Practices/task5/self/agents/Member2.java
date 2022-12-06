package Practices.task5.self.agents;

import Practices.task5.self.behaviours.CostRec;
import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 06.12.2022
 */
@Slf4j
public class Member2 extends Agent { @Override protected void setup() { addBehaviour(new CostRec(this, 10L)); } }
