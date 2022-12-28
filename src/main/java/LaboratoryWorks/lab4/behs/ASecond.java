package LaboratoryWorks.lab4.behs;

import LaboratoryWorks.lab4.common.LW4Info;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

/**
 * @author Artur Dzhanaev
 * @created 27.12.2022
 */
@Slf4j
public class ASecond extends Behaviour {


    /** Общие данные */
    private final LW4Info lw4Info;

    /** Текущий поставщик */
    private final Agent myAgent;


    public ASecond(Agent myAgent, @NotNull LW4Info lw4Info) {
        super(myAgent);
        this.myAgent = myAgent;
        this.lw4Info = lw4Info;
    }

    /** Основная часть */
    @Override public void action() {
    }

    /** Условие останова */
    @Override public boolean done() { return false; }
}
