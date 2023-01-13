package Practices.task6.example.common;

import Practices.task6.example.behs.ConsumerFSM;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class Script2Test extends TestUtil {
    @Test
    public void script_difPrice() {
        List<String> services = new ArrayList<>();
        services.add("jade.core.messaging.TopicManagementService");
        services.add("jade.core.event.NotificationService");
        startJade(services);
        createProducerAgent("script2Producer1");
        createProducerAgent("script2Producer2");
        try { Thread.sleep(1_000); }
        catch (InterruptedException e) { e.printStackTrace(); }
        ConsumerFSM fsm = new ConsumerFSM();
        createConsumerAgent("consumer", fsm);
        try { Thread.sleep(1500); }
        catch (InterruptedException e) { e.printStackTrace(); }
        Assert.assertEquals(2, fsm.winnerBeh.onEnd());
    }
}

