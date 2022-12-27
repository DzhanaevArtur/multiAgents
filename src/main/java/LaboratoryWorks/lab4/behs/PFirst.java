package LaboratoryWorks.lab4.behs;

import LaboratoryWorks.lab4.common.LW4Info;
import LaboratoryWorks.lab4.common.Main;
import LaboratoryWorks.lab4.common.PParser;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class PFirst extends Behaviour {


    /** Конфигурационные данные выработки ЭЭ */
    private final PParser p;

    /** Общие данные */
    private final LW4Info lw4Info;

    /** Номер текущего потребителя */
    private final Integer agentIndex;


    public PFirst(Agent myAgent, LW4Info lw4Info, PParser p) {
        super(myAgent);
        this.myAgent = myAgent;
        this.lw4Info = lw4Info;
        this.p = p;

        agentIndex = Integer.parseInt(myAgent.getLocalName().split("_")[1]);
    }

    /** Предварительное заполнение класса данными и регистрация производителей */
    @Override public void onStart() {
        addingToProductionList(lw4Info);
        Main.registration(myAgent);
    }

    /** Основная логика */
    @Override public void action() {

    }

    /** Обработка данных из конфигурационного файла */
    private void addingToProductionList(LW4Info l) {
        switch (agentIndex) {
            case 1 -> { for (int i = 0; i < 24; i++) l.getTPP().add(p.getA()); }
            case 2 -> { for (int i = 0; i < 24; i++) l.getWPS().add(new Random().nextGaussian(p.getB1(), p.getB2())); }
            case 3 -> { for (int i = 0; i < 24; i++) l.getTPP().add(generationOfSPS(i)); }
        }
    }

    /** Математическое описание выработки ЭЭ на СЭС */
    @Contract(pure = true) private @NotNull Double generationOfSPS(int i) {
        double output;
        if (i < 5 || i > 19) output = 0;
        else output = p.getC0() + i * p.getC1() + i * i * p.getC2() + i * i * i * i * p.getC3();
        return output;
    }

    @Override public boolean done() { return false; }
}
