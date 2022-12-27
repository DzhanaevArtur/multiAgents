package LaboratoryWorks.lab4.behs;

import LaboratoryWorks.lab4.common.LW4Info;
import Practices.TopicHelper;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class DToC extends Behaviour {


    /** Триггер */
    private Boolean trigger = false;

    /** Общие данные */
    private final LW4Info lw4Info;

    /** Текущий поставщик */
    private final Agent myAgent;


    public DToC(Agent myAgent, LW4Info lw4Info) {
        super(myAgent);
        this.myAgent = myAgent;
        this.lw4Info = lw4Info;
    }

    /** Создание чата */
    @Override public void onStart() { TopicHelper.createTopic(myAgent, "Auction"); }

    /** Основная часть */
    @Override public void action() {
        trigger = true;
    }

    /**  */
    @Override public boolean done() { return trigger; }
}
