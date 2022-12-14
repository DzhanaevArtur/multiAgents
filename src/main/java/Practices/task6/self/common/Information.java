package Practices.task6.self.common;

import jade.core.AID;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author Artur Dzhanaev
 * @created 18.12.2022
 */
@Slf4j
public class Information {

    @Getter @Setter private AID topic;
    public List<String> students = new ArrayList<>();
    public Map<Integer, String> availableTime = new TreeMap<>();
    private static Information information;

    public static synchronized Information getInformation() {
        if (information == null) information = new Information();
        return information;
    }
}
