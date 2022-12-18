package Practices.task6.self.common;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 18.12.2022
 */
@Slf4j
@Getter
@Setter
public class StudentsAvailableTime {

    private String agentName;
    private final int start, finish;
    private int[] range;

    public StudentsAvailableTime(String agentName, int start, int finish) {
        this.agentName = agentName;
        this.start = start;
        this.finish = finish;
        range = new int[] {start, finish};
    }
}
