package Practices.task6.example.agents;

import Practices.task6.example.behs.ConsumerFSM;
import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 07.12.2022
 */
@Slf4j
public class Consumer extends Agent { @Override protected void setup() { addBehaviour(new ConsumerFSM()); } }
