package Practices;

import jade.core.AID;
import jade.core.Agent;
import jade.core.ServiceException;
import jade.core.messaging.TopicManagementHelper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

/**
 * @author Artur Dzhanaev
 * @created 18.12.2022
 */
@Slf4j
public class TopicHelper {

    public static AID createTopic(@NotNull Agent a, @NotNull String topicName) {
        AID topic = null;
        try {
            TopicManagementHelper topicHelper = (TopicManagementHelper) a.getHelper(TopicManagementHelper.SERVICE_NAME);
            topic = topicHelper.createTopic(topicName);
            topicHelper.register(topic);
        } catch (ServiceException e) { e.printStackTrace(); }
        return topic;
    }
}
