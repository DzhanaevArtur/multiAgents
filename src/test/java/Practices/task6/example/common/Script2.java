package Practices.task6.example.common;

import Practices.task6.example.behs.ConsumerFSM;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class Script2 extends TestUtil {
    @Test
    public void script_samePrice() {
        startJade(List.of("jade.core.messaging.TopicManagementService", "jade.core.event.NotificationService"));
        createProducerAgent("script2Producer1");
        createProducerAgent("script2Producer1");
        ConsumerFSM consumerFSM = new ConsumerFSM();
        createConsumerAgent("consumer", consumerFSM);
        try { Thread.sleep(1500); }
        catch (InterruptedException e) { e.printStackTrace(); }
        Assert.assertEquals(2, consumerFSM.winnerBeh.onEnd());
    }
}
