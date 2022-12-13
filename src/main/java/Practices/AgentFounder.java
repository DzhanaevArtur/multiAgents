package Practices;

import jade.core.Agent;
import jade.core.ProfileImpl;
import jade.util.leap.Properties;
import laboratoryWorks.lab3.common.AutoRunnableAgent;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Artur Dzhanaev
 * @created 13.12.2022
 */
@Slf4j
public class AgentFounder {

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

    @SafeVarargs
    private static Map<String, String> findAgents(Class<? extends Agent> ... classes) {
        Map<String, String> map = new HashMap<>();
        for (Class<? extends Agent> value : classes) {
            for (Class<?> aClass : new Reflections(value).getTypesAnnotatedWith(AutoRunnableAgent.class)) {
                AutoRunnableAgent autoRunnableAgent = aClass.getAnnotation(AutoRunnableAgent.class);
                for (int j = 0; j < autoRunnableAgent.copy(); j++) {
                    int count = j + 1;
                    map.put(autoRunnableAgent.name() + count, aClass.getName());
                }
            }
        }
        return map;
    }

    @SafeVarargs
    public static ProfileImpl founder(Class<? extends Agent> ... classes){
        return new ProfileImpl(createProperties(findAgents(classes)));
    }
}
