package Practices.task6.example.common;

import jade.core.AID;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Artur Dzhanaev
 * @created 18.12.2022
 */
@Slf4j
@Getter
@Setter
public class Data {

    private AID topic;
    private int numberOfProducers;
    private List<AgentPrice> agentPrices = new ArrayList<>();
}
