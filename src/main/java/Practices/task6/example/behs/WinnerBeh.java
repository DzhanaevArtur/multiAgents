package Practices.task6.example.behs;

import Practices.task6.example.help.Data;
import jade.core.behaviours.OneShotBehaviour;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 07.12.2022
 */
@Slf4j
public class WinnerBeh extends OneShotBehaviour {

    Data data;

    public WinnerBeh(Data data) { this.data = data; }

    @Override public void action() {
        int price1 = data.getAgentPrices().get(0).getPrice();
        int price2 = data.getAgentPrices().get(1).getPrice();
        if (price1 == price2) log.info("The prices are the same: {}", price1);
        else if (price1 < price2) log.info("Winner: {}", data.getAgentPrices().get(0).getAgentName());
        else log.info("Winner: {}", data.getAgentPrices().get(1).getAgentName());
    }
}
