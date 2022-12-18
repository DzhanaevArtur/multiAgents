package Practices.task6.exampleSelf.behs.seller;

import LaboratoryWorks.lab3.common.XmlInfo;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Artur Dzhanaev
 * @created 14.12.2022
 */
@Slf4j
public class Middle extends OneShotBehaviour {

    private final Agent myAgent;
    private final AID aid;
    private final Integer bookNumber;

    public Middle(Agent myAgent, AID aid, Integer bookNumber) {
        super(myAgent);
        this.myAgent = myAgent;
        this.aid = aid;
        this.bookNumber = bookNumber;
    }

    @Override public void action() {
        ACLMessage aclMessage = new ACLMessage(ACLMessage.CFP);
        if (myAgent.getLocalName().equals("Seller1")) aclMessage.setContent(String.valueOf(bookNumber * parser().get(0) + parser().get(1)));
        else aclMessage.setContent(String.valueOf(bookNumber * parser().get(2) + parser().get(3)));
        aclMessage.addReceiver(aid);
        myAgent.send(aclMessage);
        log.info("\t\"{}\" sent to {}", aclMessage.getContent(), aid.getLocalName());
        myAgent.addBehaviour(new Final(myAgent));
    }

    public static List<Integer> parser() {
        List<Integer> values = new ArrayList<>();
        NodeList nodeList;
        try { nodeList = XmlInfo.nodeListCreation(new File("cfg.xml")); }
        catch (ParserConfigurationException | SAXException | IOException e) { throw new RuntimeException(e); }
        for (int i = 0; i < nodeList.getLength(); i++) { // проходимся по каждому продавцу
            NodeList childNodes = nodeList.item(i).getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) values.add(Integer.valueOf(childNodes.item(j).getTextContent()));
        }
        return values;
    }
}
