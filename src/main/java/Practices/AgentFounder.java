package Practices;

import jade.core.Agent;
import jade.core.ProfileImpl;
import jade.util.leap.Properties;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Artur Dzhanaev
 * @created 13.12.2022
 */
@Slf4j
public class AgentFounder {

    private static @NotNull Properties createProps(@NotNull Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            stringBuilder.append(entry.getKey()).append(':').append(entry.getValue()).append(';');
        }
        Properties properties = new Properties();
        properties.setProperty("gui", "true");
        properties.setProperty("agents", stringBuilder.toString());
        properties.setProperty("services",
                "jade.core.event.NotificationService;jade.core.messaging.TopicManagementService");
        return properties;
    }

    @SafeVarargs private static @NotNull Map<String, String> findAgents(Class<? extends Agent> @NotNull ... classes) {
        Map<String, String> map = new HashMap<>();
        for (Class<? extends Agent> value : classes) {
            for (Class<?> aClass : new Reflections(value).getTypesAnnotatedWith(AutoRunnableAgent.class)) {
                AutoRunnableAgent a = aClass.getAnnotation(AutoRunnableAgent.class);
                int copy = a.copy();
                for (int j = 0; j < copy; j++) map.put(String.format("%s_%d", a.name(), j + 1), aClass.getName());
            }
        }
        return map;
    }

    @Contract("_ -> new") @SafeVarargs public static @NotNull ProfileImpl founder(Class<? extends Agent> ... c) {
        return new ProfileImpl(createProps(findAgents(c)));
    }
}
