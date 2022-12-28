package Practices.task6.example.common;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;

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

    @Contract(pure = true) public AgentPrice(int price, String agentName) {
        this.price = price;
        this.agentName = agentName;
    }
}

