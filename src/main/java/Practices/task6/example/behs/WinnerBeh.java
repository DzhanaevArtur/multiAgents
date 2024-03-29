package Practices.task6.example.behs;

import Practices.task6.example.common.Data;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 18.12.2022
 */
@Slf4j
public class WinnerBeh extends OneShotBehaviour {

    Data data;

    public WinnerBeh(Agent myAgent, Data data) {
        super(myAgent);
        this.data = data;
    }

    @Override public void action() {
        int price1 = data.getAgentPrices().get(0).getPrice();
        int price2 = data.getAgentPrices().get(1).getPrice();
        if (price1 == price2) log.info("Prices are equals: {}", price1);
        else if (price1 < price2) log.info("Winner: {}", data.getAgentPrices().get(0).getAgentName());
        else log.info("Winner: {}", data.getAgentPrices().get(1).getAgentName());
    }

    @Override public int onEnd() { return data.isSamePrice() ? 1 : 2; }
}

