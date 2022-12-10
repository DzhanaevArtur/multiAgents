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

/**
 * @author Artur Dzhanaev
 * @created 08.12.2022
 */
@Slf4j
public class ParseWeights extends OneShotBehaviour {

    private final Agent myAgent;

    public ParseWeights(Agent myAgent) { super(myAgent); this.myAgent = myAgent; }

    @Override public void action() {
        try { weightsParser(myAgent.getLocalName()); }
        catch (ParserConfigurationException | IOException | SAXException e) { throw new RuntimeException(e); }
    }

    private void weightsParser(String agentName) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setValidating(false);
        documentBuilderFactory.setIgnoringElementContentWhitespace(true);

        NodeList agents = documentBuilderFactory.newDocumentBuilder().parse(new File("config.xml")).getChildNodes().item(1).getChildNodes();
        for (int i = 0; i < agents.getLength(); i++) {
            Node agent = agents.item(i);
            String agentToParse = agent.getAttributes().item(0).getNodeValue();
            NodeList neighbours = agent.getChildNodes();

            for (int j = 0; j < neighbours.getLength(); j++) {
                Node neighbour = neighbours.item(j);
                String length = neighbour.getTextContent();
                String receiver = neighbour.getAttributes().item(0).getNodeValue();
                if (agentName.equals(agentToParse)) {
                    log.info("\tLength form {} to {} equals {}", agentName, receiver, length);
                }
            }
        }
    }
}
