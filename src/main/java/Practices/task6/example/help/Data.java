package Practices.task6.example.help;

import jade.core.AID;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Artur Dzhanaev
 * @created 07.12.2022
 */
@Slf4j
public class Data  {

    @Getter @Setter private AID topic;
    @Getter @Setter private int numberOfProducers;
    @Getter @Setter private List<AgentPrice> agentPrices = new ArrayList<>();
}
