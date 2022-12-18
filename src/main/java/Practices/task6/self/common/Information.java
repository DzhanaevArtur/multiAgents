package Practices.task6.self.common;

import jade.core.AID;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Artur Dzhanaev
 * @created 18.12.2022
 */
@Slf4j
@Getter
@Setter
public class Information {

    private AID topic;
    private int studentsNumber;
    private List<StudentsAvailableTime> list = new ArrayList<>();
}
