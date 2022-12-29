package LaboratoryWorks.lab4.behs;

import LaboratoryWorks.lab4.common.LW4Info;
import LaboratoryWorks.lab4.common.PParser;
import Practices.TopicHelper;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

import static jade.lang.acl.MessageTemplate.*;
import static jade.lang.acl.MessageTemplate.MatchProtocol;

/**
 * @author Artur Dzhanaev
 * @created 28.12.2022
 */
@Slf4j
public class PFirst extends Behaviour {


    /** Номер текущего потребителя */
    private final Integer agentIndex;

    /** Конфигурационные данные выработки ЭЭ */
    private final PParser p;

    /** Общие данные */
    private final LW4Info info;

    /** Текущий производитель */
    private final Agent myAgent;


    public PFirst(Agent myAgent, LW4Info info, PParser p) {
        super(myAgent);
        this.myAgent = myAgent;
        this.info = info;
        this.p = p;

        agentIndex = Integer.parseInt(myAgent.getLocalName().split("_")[1]);
    }

    /** Предварительное заполнение класса данными */
    @Override public void onStart() {
        switch (agentIndex) {
            case 1 -> { for (int i = 0; i < 24; i++) info.getT().add(p.getA()); }
            case 2 -> { for (int i = 0; i < 24; i++) info.getW().add(new Random().nextGaussian(p.getB1(), p.getB2())); }
            case 3 -> { for (int i = 0; i < 24; i++) info.getS().add(generationOfSPS(i)); }
        }
    }

    /** Отклик на поиск поставщиком и добавление в чат */
    @Override public void action() {
        ACLMessage aclMessage = myAgent.receive(and(MatchPerformative(ACLMessage.INFORM), MatchProtocol("XXX")));
        if (aclMessage != null) {
            TopicHelper.createTopic(myAgent, "Auction");
            myAgent.addBehaviour(new PSecond(myAgent, info));
        }
        else block();
    }

    /** Математическое описание выработки ЭЭ на СЭС */
    @Contract(pure = true) private @NotNull Double generationOfSPS(int i) {
        return (i < 5 || i > 19) ? 0 : p.getC0() + p.getC1() * i + p.getC2() * i * i + p.getC3() * i * i * i;
    }

    /** Останов поведения */
    @Override public boolean done() { return false; }
}
