package laboratoryWorks.lab3.behs;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Artur Dzhanaev
 * @created 08.12.2022
 */
@Slf4j
public class Example extends OneShotBehaviour {

    private final Agent myAgent;

    public Example(Agent myAgent) {
        super(myAgent);
        this.myAgent = myAgent;
    }

    @Override public void action() {
        try { weightsParser(); }
        catch (ParserConfigurationException | SAXException | IOException e) { throw new RuntimeException(e); }
    }

    private void weightsParser() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setValidating(true);
        documentBuilderFactory.setIgnoringElementContentWhitespace(true);
        List<Integer> weights = new ArrayList<>();
        String localName = myAgent.getLocalName();
//        NodeList nodeList = documentBuilderFactory.newDocumentBuilder().parse(new File("src/main/java/laboratoryWorks/lab3/configs/1.xml")).getElementsByTagName("cfg").item(0).getChildNodes();
        NodeList nodeList = documentBuilderFactory.newDocumentBuilder().parse(new File(String.format("src/main/java/laboratoryWorks/lab3/configs/%d.xml", Integer.parseInt(localName.substring(localName.indexOf('e') + 1))))).getElementsByTagName("weights").item(0).getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            String textContent = nodeList.item(i).getTextContent();
            if (!textContent.equals(nodeList.item(0).getTextContent()) && !textContent.equals(nodeList.item(nodeList.getLength() - 1).getTextContent())) {
                weights.add(Integer.parseInt(textContent));
            }
        }
        log.info("\t{}", weights);
//        return weights; List<Integer>
    }
}
