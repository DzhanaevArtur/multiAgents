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
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Artur Dzhanaev
 * @created 08.12.2022
 */
@Slf4j
public class ParseWeights extends OneShotBehaviour {

    private final Agent myAgent;
    private String nextAgent = "Agent4";

    public ParseWeights(Agent myAgent) { super(myAgent); this.myAgent = myAgent; }

    @Override public void action() {
        String agentName = myAgent.getLocalName();
        Map<String, Integer> map;
        try { map = weightsParser(agentName); }
        catch (ParserConfigurationException | IOException | SAXException e) { throw new RuntimeException(e); }
        Iterator<Map.Entry<String, Integer>> iterator = sortMap(map).entrySet().iterator();
        Map.Entry<String, Integer> next = iterator.next();

        String r; int l;
        if (!agentName.equals(next.getKey())) { r = next.getKey(); l = next.getValue(); }
        else { r = iterator.next().getKey(); l = iterator.next().getValue(); }
        if (agentName.equals("Agent1")) { myAgent.addBehaviour(new FirstSend(myAgent, r, l)); nextAgent = r; }

        if (agentName.equals(nextAgent)) {
            log.info("INSIDE");
            myAgent.addBehaviour(new FirstReceive(myAgent));
        }
    }

    private Map<String, Integer> weightsParser(String agentName) throws ParserConfigurationException, IOException, SAXException {
        Map<String, Integer> map = new HashMap<>();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setValidating(false);
        documentBuilderFactory.setIgnoringElementContentWhitespace(true);

        NodeList agents = documentBuilderFactory
                .newDocumentBuilder()
                .parse(new File("config.xml"))
                .getChildNodes()
                .item(1)
                .getChildNodes();
        for (int i = 0; i < agents.getLength(); i++) {
            Node agent = agents.item(i);
            String agentToParse = agent.getAttributes().item(0).getNodeValue();
            NodeList neighbours = agent.getChildNodes();

            for (int j = 0; j < neighbours.getLength(); j++) {
                Node neighbour = neighbours.item(j);
                Integer length = Integer.valueOf(neighbour.getTextContent());
                String receiver = neighbour.getAttributes().item(0).getNodeValue();
                if (agentName.equals(agentToParse)) {
                    map.put(receiver, length);
//                    log.info("\tLength form {} to {} equals {}", agentName, receiver, length);
                }
            }
        }
        return map;
    }

    private Map<String, Integer> sortMap(Map<String, Integer> mapToSort) {
         return mapToSort
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    /*
    Для решения задачи надо:
    1) Создать counter, считающий расстояния.
    2) Выбрать инициатора (агент1).
    3) Отправить одному из агентов (агент2), с которым у него есть связь, расстояние от агент1 до агент2.
    4) Получить агентом2 информацию, отправленную от агент1.
    5) просмотреть агентов, с которыми у агент2 есть связь, исключая отправителя и скинуть одному из агентов (агент3) расстояние от агент2 до агент3, учитывая шаг 3.
    6) зациклить шаги 2-5, пока receiver != агент12.

    Чтобы проверять на то, чтобы не было зацикливаний, надо создать аррайлист из названий агентов и добавлять туда каждый раз имя
    */
}
