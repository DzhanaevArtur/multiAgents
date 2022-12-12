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

    public static String start, finish;

    static {
        try {
            start = firstInfoGet(nodeListCreation()).get(0);
            finish = firstInfoGet(nodeListCreation()).get(1);
        }
        catch (ParserConfigurationException | SAXException | IOException e) { throw new RuntimeException(e); }
    }

    /**
     * Получение исходных данных
     * @param nodeList Объектная модель конфигурационного файла
     * @return список агента-инициатора и целевого агента
     */
    private static List<String> firstInfoGet(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            NamedNodeMap namedNodeMap = nodeList.item(i).getAttributes();
            if (Boolean.parseBoolean(namedNodeMap.item(2).getNodeValue())) {
                return List.of(namedNodeMap.item(1).getNodeValue(), namedNodeMap.item(0).getNodeValue());
            }
        }
        return new ArrayList<>();
    }

    /**
     * Определение пары: агент-получатель: длинна до него
     * @param agentName Название обрабатываемого агента
     * @return Карта агент: длинна
     */
    public static Map<String, Integer> choose(String agentName) {
        Iterator<Map.Entry<String, Integer>> iterator = mapSort(agentName);
        Map.Entry<String, Integer> first = iterator.next(), second = iterator.next();

        String receiver; int length;
        if (!agentName.equals("Node4") && !agentName.equals("Node9") && !agentName.equals(first.getKey())) {
            receiver = first.getKey();
            length = first.getValue();
        }
        else {
            receiver = second.getKey();
            length = second.getValue();
        }
        return Map.of(receiver, length);
    }

    /**
     * Сортировка данных по возрастанию расстояний между агентами
     * @param agentName Название обрабатываемого агента
     * @return Преобразованная в итератор отсортированная карта
     */
    private static Iterator<Map.Entry<String, Integer>> mapSort(String agentName) {
        return unmarshalCfgFile(agentName)
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new))
                .entrySet()
                .iterator();
    }

    /**
     * Обработка содержимого конфигурационного файла
     * @param agentName Название обрабатываемого агента
     * @return Карта, где ключ - название обрабатываемого агента, значение - расстояние до него от текущего агента
     */
    private static Map<String, Integer> unmarshalCfgFile(String agentName) {
        NodeList nodeList;
        try { nodeList = nodeListCreation(); }
        catch (ParserConfigurationException | IOException | SAXException e) { throw new RuntimeException(e); }

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < nodeList.getLength(); i++) {                      // проходимся по всем тегам <Agent></Agent>
            Node agent = nodeList.item(i);                                    // получаем всю начинку тега <Agent></Agent>
            NamedNodeMap namedNodeMap = agent.getAttributes();                // получаем группу параметров тега <Agent>: {final, id, initiator}
            if (agentName.equals(namedNodeMap.item(1).getNodeValue())) { // проверка на чтение агентом своей части конфига
                NodeList neighbours = agent.getChildNodes();                  // получаем список из агентов, с которыми есть связь
                for (int j = 0; j < neighbours.getLength(); j++) {
                    Node node = neighbours.item(j);                           // получаем всю начинку тега <Neighbor></Neighbor>
                    map.put(node.getAttributes().item(0).getNodeValue(), Integer.valueOf(node.getTextContent()));
                }
            }
        }
        return map;
    }

    /**
     * Преобразование конфигурационного файла в Java-модель
     * @return Объектная модель содержимого файла
     * @throws ParserConfigurationException Если невозможно создать DocumentBuilder, удовлетворяющий запрошенной конфигурации
     * @throws IOException Если возникают какие-либо ошибки ввода-вывода
     * @throws SAXException Если возникают какие-либо ошибки синтаксического анализа
     */
    private static NodeList nodeListCreation() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setValidating(false);
        documentBuilderFactory.setIgnoringElementContentWhitespace(true);
        return documentBuilderFactory
                .newDocumentBuilder()
                .parse(new File("config.xml"))
                .getChildNodes()
                .item(1)
                .getChildNodes();
    }
}
