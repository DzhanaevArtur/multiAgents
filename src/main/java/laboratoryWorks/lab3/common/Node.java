package laboratoryWorks.lab3.common;

import jade.core.Agent;
import laboratoryWorks.lab3.behs.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 08.12.2022
 */
@Slf4j
@AutoRunnableAgent(name = "Node", copy = 12)
public class Node extends Agent {

    @Override protected void setup() {
        String localName = this.getLocalName();
        String destination = Parser.choose(localName).keySet().stream().toList().get(0);

        if (localName.equals(Parser.start)) {
            addBehaviour(new Sender     (this, 0, destination, lengths(0)));
        }
        else if (!localName.equals(Parser.finish)) {
            for (int i = 0; i < 10; i++) {
                if (localName.equals(neighbours(i))) {
                    addBehaviour(new Receive(this, 1000L * i + 500L));
                    addBehaviour(new Sender (this, 1000L * i + 1_000L, destination, lengths(i)));
                    if (destination.equals(Parser.finish)) break;
                }
            }
        }
        else { addBehaviour(new Receive(this, 5_500L)); }
    }

    private String neighbours(int count) {
        return count == 0 ? Parser.start : count == 1 ? Parser.choose(Parser.start).keySet().stream().toList().get(0) : Parser.choose(neighbours(count - 1)).keySet().stream().toList().get(0);
    }

    private int lengths(int count) {
        return count == 0 ? Parser.choose(this.getLocalName()).values().stream().toList().get(0) : count == 1 ? Parser.choose(neighbours(0)).values().stream().toList().get(0) + lengths(0) : Parser.choose(neighbours(count - 1)).values().stream().toList().get(0) + lengths(count - 1);
    }
}
