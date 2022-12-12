package laboratoryWorks.lab3.common;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import jade.util.leap.Properties;

/**
 * @author Artur Dzhanaev
 * @created 11.12.2022
 */
@Slf4j
public class Main {

    public static void main(String[] args) {
        Runtime runtime = Runtime.instance();
        runtime.setCloseVM(true);
        runtime.createMainContainer(new ProfileImpl(createProperties(findAgents())));
    }

    private static Properties createProperties(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            stringBuilder.append(entry.getKey()).append(':').append(entry.getValue()).append(';');
        }
        Properties properties = new Properties();
        properties.setProperty("gui", "true");
        properties.setProperty("agents", stringBuilder.toString());
        return properties;
    }

    private static Map<String, String> findAgents() {
        Map<String, String> map = new HashMap<>();
        for (Class<?> aClass : new Reflections(Node.class).getTypesAnnotatedWith(AutoRunnableAgent.class)) {
            AutoRunnableAgent autoRunnableAgent = aClass.getAnnotation(AutoRunnableAgent.class);
            for (int i = 0; i < autoRunnableAgent.copy(); i++) {
                int count = i + 1;
                map.put(autoRunnableAgent.name() + count, aClass.getName());
            }
        }
        return map;
    }
}
