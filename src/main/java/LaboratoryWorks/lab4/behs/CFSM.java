package LaboratoryWorks.lab4.behs;

import LaboratoryWorks.lab4.common.CParser;
import LaboratoryWorks.lab4.common.LW;
import LaboratoryWorks.lab4.common.Main;
import Practices.TopicHelper;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class CFSM extends FSMBehaviour {

    public CFSM(Agent myAgent, CParser cParser, LW lw) {
        super(myAgent);
        TopicHelper.createTopic(myAgent, Main.CHAT);
        switch (myAgent.getLocalName()) {
            case "Consumer_1" -> addingToList(lw.getCPowerPerHour1(), cParser);
            case "Consumer_2" -> addingToList(lw.getCPowerPerHour2(), cParser);
            case "Consumer_3" -> addingToList(lw.getCPowerPerHour3(), cParser);
        }

        registerFirstState(new CChatNameSent(myAgent, lw), "one");
        registerLastState (new CEnergyBuy(myAgent, lw), "two");

        registerDefaultTransition("one", "two");
    }

    private void addingToList(List<Double> list, CParser cParser) {
        list.add((cParser.getP100() * cParser.getP0())  / 100);
        list.add((cParser.getP100() * cParser.getP1())  / 100);
        list.add((cParser.getP100() * cParser.getP2())  / 100);
        list.add((cParser.getP100() * cParser.getP3())  / 100);
        list.add((cParser.getP100() * cParser.getP4())  / 100);
        list.add((cParser.getP100() * cParser.getP5())  / 100);
        list.add((cParser.getP100() * cParser.getP6())  / 100);
        list.add((cParser.getP100() * cParser.getP7())  / 100);
        list.add((cParser.getP100() * cParser.getP8())  / 100);
        list.add((cParser.getP100() * cParser.getP9())  / 100);
        list.add((cParser.getP100() * cParser.getP10()) / 100);
        list.add((cParser.getP100() * cParser.getP11()) / 100);
        list.add((cParser.getP100() * cParser.getP12()) / 100);
        list.add((cParser.getP100() * cParser.getP13()) / 100);
        list.add((cParser.getP100() * cParser.getP14()) / 100);
        list.add((cParser.getP100() * cParser.getP15()) / 100);
        list.add((cParser.getP100() * cParser.getP16()) / 100);
        list.add((cParser.getP100() * cParser.getP17()) / 100);
        list.add((cParser.getP100() * cParser.getP18()) / 100);
        list.add((cParser.getP100() * cParser.getP19()) / 100);
        list.add((cParser.getP100() * cParser.getP20()) / 100);
        list.add((cParser.getP100() * cParser.getP21()) / 100);
        list.add((cParser.getP100() * cParser.getP22()) / 100);
        list.add((cParser.getP100() * cParser.getP23()) / 100);
    }
}
