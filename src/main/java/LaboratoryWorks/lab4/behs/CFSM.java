package LaboratoryWorks.lab4.behs;

import LaboratoryWorks.lab4.common.CParser;
import LaboratoryWorks.lab4.common.LW4Info;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class CFSM extends FSMBehaviour {

    public CFSM(Agent myAgent, CParser cParser, LW4Info lw4Info) {
        super(myAgent);
//        TopicHelper.createTopic(myAgent, Main.CHAT);
        switch (myAgent.getLocalName()) {
            case "Consumer_1" -> addingToLoadList(lw4Info.getMPEI(), cParser);
            case "Consumer_2" -> addingToLoadList(lw4Info.getFoodIndustryFactory(), cParser);
            case "Consumer_3" -> addingToLoadList(lw4Info.getShoeFactory(), cParser);
        }

        registerFirstState(new CChatName(myAgent, lw4Info), "one");
        registerLastState (new CEnergyBuy(myAgent, lw4Info), "two");

        registerDefaultTransition("one", "two");
    }

    private void addingToLoadList(@NotNull List<Double> list, @NotNull CParser cParser) {
        List<Double> coefficients = cParser.getPowerCoefficients();
        for (int i = 1; i < coefficients.size(); i++) list.add((coefficients.get(0) * coefficients.get(i)) / 100);
    }
}
