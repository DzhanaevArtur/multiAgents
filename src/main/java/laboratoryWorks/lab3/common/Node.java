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
            if (localName.equals(neighbours(1))) {
                addBehaviour(new Receive(this, 500L));
                addBehaviour(new Sender (this, 1_000L, destination, lengths(1)));
            }
            if (localName.equals(neighbours(2))) {
                addBehaviour(new Receive(this, 1_500L));
                addBehaviour(new Sender (this, 2_000L, destination, lengths(2)));
            }
            if (localName.equals(neighbours(3))) {
                addBehaviour(new Receive(this, 2_500L));
                addBehaviour(new Sender (this, 3_000L, destination, lengths(3)));
            }
            if (localName.equals(neighbours(4))) {
                addBehaviour(new Receive(this, 3_500L));
                addBehaviour(new Sender (this, 4_000L, destination, lengths(4)));
            }
        }
        else { addBehaviour(new Receive(this, 4_500L)); }
    }

    private String neighbours(int count) {
        return count == 0 ? Parser.start : count == 1 ? Parser.choose(Parser.start).keySet().stream().toList().get(0) : Parser.choose(neighbours(count - 1)).keySet().stream().toList().get(0);
    }

    private int lengths(int count) {
        return count == 0 ? Parser.choose(this.getLocalName()).values().stream().toList().get(0) : count == 1 ? Parser.choose(neighbours(0)).values().stream().toList().get(0) + lengths(0) : Parser.choose(neighbours(count - 1)).values().stream().toList().get(0) + lengths(count - 1);
    }
}
