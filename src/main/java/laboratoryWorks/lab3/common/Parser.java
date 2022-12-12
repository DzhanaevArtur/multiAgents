package laboratoryWorks.lab3.common;

import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.*;
import org.w3c.dom.Node;
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
public class Parser {

    public static String start = "Node1", finish = "Node12";
    public static int counter = choose(start).values().stream().toList().get(0);

    public static Map<String, Integer> choose(String agentName) {
        Iterator<Map.Entry<String, Integer>> iterator;
        try { iterator = parse(agentName); }
        catch (ParserConfigurationException | IOException | SAXException e) { throw new RuntimeException(e); }
        Map.Entry<String, Integer> first = iterator.next(), second = iterator.next();

        String receiver; int length;
        if (!agentName.equals(first.getKey())) { receiver = first.getKey(); length = first.getValue(); }
        else { receiver = second.getKey(); length = second.getValue(); }
        return Map.of(receiver, length);
    }

    private static Iterator<Map.Entry<String, Integer>> parse(String agentName) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setValidating(false);
        documentBuilderFactory.setIgnoringElementContentWhitespace(true);
        NodeList nodeList = documentBuilderFactory
                .newDocumentBuilder()
                .parse(new File("config.xml"))
                .getChildNodes()
                .item(1)
                .getChildNodes();
        Map<String, Integer> map = new HashMap<>(); // карта, в которую добавляем отфильтрованные по расстояниям между агентами пары {имя агента: расстояние до него}
        for (int i = 0; i < nodeList.getLength(); i++) { // проходимся по всем тегам <Agent></Agent>
            Node agent = nodeList.item(i); // получаем всю начинку тега <Agent></Agent>
            NamedNodeMap namedNodeMap = agent.getAttributes(); // получаем группу параметров тега <Agent>: {final, id, initiator}
            if (agentName.equals(namedNodeMap.item(1).getNodeValue())) { // проверка на чтение агентом своей части конфига
                if (Boolean.parseBoolean(namedNodeMap.item(2).getNodeValue())) { // проверка на инициатора
                    start = namedNodeMap.item(1).getNodeValue(); // получаем лидера
                    finish = namedNodeMap.item(0).getNodeValue(); // получаем "пункт назначения"
                }
                NodeList neighbours = agent.getChildNodes(); // получаем список из агентов, с которыми есть связь
                for (int j = 0; j < neighbours.getLength(); j++) {
                    Node node = neighbours.item(j); // получаем всю начинку тега <Neighbor></Neighbor>
                    map.put(node.getAttributes().item(0).getNodeValue(), Integer.valueOf(node.getTextContent()));
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
