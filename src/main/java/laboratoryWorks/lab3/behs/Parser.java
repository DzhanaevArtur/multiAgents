package laboratoryWorks.lab3.behs;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Artur Dzhanaev
 * @created 08.12.2022
 */
@Slf4j
public class Parser extends OneShotBehaviour {

    private final Agent myAgent;

    public static int counter = 1;

    public Parser(Agent myAgent) { super(myAgent); this.myAgent = myAgent; }

    @Override public void action() {
        String name = myAgent.getLocalName();
        if (name.equals("Agent1")) myAgent.addBehaviour(new Sender(myAgent, choose(name).get(0), counter));
        if (name.equals("Agent4")) myAgent.addBehaviour(new Receiver(myAgent, 100L));
    }

    public static List<String> choose(String agentName) {
        Iterator<Map.Entry<String, String>> iterator;
        try { iterator = parse(agentName); }
        catch (ParserConfigurationException | IOException | SAXException e) { throw new RuntimeException(e); }
        Map.Entry<String, String> first = iterator.next(), second = iterator.next();

        String receiver, length;
        if (!agentName.equals(first.getKey())) { receiver = first.getKey(); length = first.getValue(); }
        else { receiver = second.getKey(); length = second.getValue(); }
        return List.of(receiver, length);
    }

    private static Iterator<Map.Entry<String, String>> parse(String agentName) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setValidating(false);
        documentBuilderFactory.setIgnoringElementContentWhitespace(true);
        NodeList nodeList = documentBuilderFactory
                .newDocumentBuilder()
                .parse(new File("config.xml"))
                .getChildNodes()
                .item(1)
                .getChildNodes();
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node agent = nodeList.item(i);
            NodeList neighbours = agent.getChildNodes();
            for (int j = 0; j < neighbours.getLength(); j++) {
                Node node = neighbours.item(j);
                if (agentName.equals(agent.getAttributes().item(0).getNodeValue())) {
                    map.put(node.getAttributes().item(0).getNodeValue(), node.getTextContent());
                }
            }
        }
        return map
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new))
                .entrySet()
                .iterator();
    }
}
