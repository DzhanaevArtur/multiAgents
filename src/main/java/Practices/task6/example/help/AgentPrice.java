package Practices.task6.example.help;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 07.12.2022
 */
@Slf4j
public class AgentPrice  {

    @Getter @Setter private int price;

    @Getter @Setter private String agentName;

    public AgentPrice(int price, String agentName) { this.price = price; this.agentName = agentName; }
}
