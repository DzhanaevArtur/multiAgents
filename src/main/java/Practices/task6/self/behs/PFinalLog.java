package Practices.task6.self.behs;

import Practices.task6.self.common.Information;
import jade.core.behaviours.OneShotBehaviour;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class PFinalLog extends OneShotBehaviour {

    private final Information information;

    public PFinalLog(Information information) { this.information = information; }

    @Override
    public void action() {
        Iterator<Map.Entry<Integer, String>> iterator = information.availableTime.entrySet().iterator();
        List<String> students = information.students;
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> next = iterator.next();
            String name = next.getValue();
            Integer key = next.getKey();
            if (name != null) { students.remove(name); log.info("\t\t{} - {}", key, name); }
            else log.info("\t\t{} - EMPTY", key);
        }
        for (String student : students) log.info("\t\tIs busy: {}", student);
    }
}
