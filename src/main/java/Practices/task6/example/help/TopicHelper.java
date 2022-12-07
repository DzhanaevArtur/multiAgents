package Practices.task6.example.help;

import jade.core.AID;
import jade.core.Agent;
import jade.core.ServiceException;
import jade.core.messaging.TopicManagementHelper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 07.12.2022
 */
@Slf4j
public class TopicHelper {

    public static AID createTopic(Agent agent, String topicName) {
        try {
            TopicManagementHelper topicManagementHelper = (TopicManagementHelper) agent.getHelper(TopicManagementHelper.SERVICE_NAME);
            AID aid = topicManagementHelper.createTopic(topicName);
            topicManagementHelper.register(aid);
            return aid;
        } catch (ServiceException e) { return null; }
    }
}