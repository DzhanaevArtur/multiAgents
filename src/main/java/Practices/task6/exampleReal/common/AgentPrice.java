package Practices.task6.exampleReal.common;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 18.12.2022
 */
@Slf4j
@Getter
@Setter
public class AgentPrice {

    private int price;
    private String agentName;

    public AgentPrice(int price, String agentName) {
        this.price = price;
        this.agentName = agentName;
    }
}

