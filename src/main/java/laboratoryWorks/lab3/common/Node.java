package laboratoryWorks.lab3.common;

import Practices.Parser;
import jade.core.Agent;
import laboratoryWorks.lab3.behs.*;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

/**
 * @author Artur Dzhanaev
 * @created 08.12.2022
 */
@Slf4j
@AutoRunnableAgent(name = "Node", copy = 12)
public class Node extends Agent {

    private final int copy = new Reflections(Node.class)
            .getTypesAnnotatedWith(AutoRunnableAgent.class)
            .stream()
            .iterator()
            .next()
            .getAnnotation(AutoRunnableAgent.class)
            .copy() - 2;

    @Override protected void setup() {
        String localName = this.getLocalName();
        String destination = Parser.choose(localName).keySet().stream().toList().get(0);

        if (localName.equals(Parser.start)) {
            addBehaviour(new Sender(this, destination, lengths(0)));
        }
        else if (!localName.equals(Parser.finish)) {
            for (int i = 0; i < copy; i++) {
                if (localName.equals(neighbours(i))) {
                    addBehaviour(new Receive(this));
                    addBehaviour(new Sender(this, destination, lengths(i)));
                    if (destination.equals(Parser.finish)) break;
                }
            }
        }
        else { addBehaviour(new Receive(this)); }
    }

    private String neighbours(int count) { return count == 0 ? Parser.start : count == 1 ? Parser.choose(Parser.start).keySet().stream().toList().get(0) : Parser.choose(neighbours(count - 1)).keySet().stream().toList().get(0); }

    private int lengths(int count) { return count == 0 ? Parser.choose(this.getLocalName()).values().stream().toList().get(0) : count == 1 ? Parser.choose(neighbours(0)).values().stream().toList().get(0) + lengths(0) : Parser.choose(neighbours(count - 1)).values().stream().toList().get(0) + lengths(count - 1); }
}
