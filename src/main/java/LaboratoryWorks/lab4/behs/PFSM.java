package LaboratoryWorks.lab4.behs;

import LaboratoryWorks.lab4.common.LW4Info;
import LaboratoryWorks.lab4.common.Main;
import LaboratoryWorks.lab4.common.PParser;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class PFSM extends FSMBehaviour {

    public PFSM(Agent myAgent, PParser pParser, LW4Info lw4Info) {
        super(myAgent);
        Main.registration(myAgent);
        addingToProductionList(lw4Info, pParser);

        registerFirstState(new PFirst(myAgent),  "one");
        registerLastState (new PSecond(myAgent), "two");

        registerDefaultTransition("one", "two");
    }

    private void addingToProductionList(LW4Info lw4Info, PParser pParser) {
        for (int i = 0; i < 24; i++) {
            lw4Info.getTPP().add(pParser.getA());
            lw4Info.getWPS().add(new Random().nextGaussian(pParser.getB1(), pParser.getB2()));
            lw4Info.getSPS().add(generationOfSPS(pParser, i));
        }
    }

    @Contract(pure = true) private @NotNull Double generationOfSPS(@NotNull PParser pParser, int i) {
        double output;
        if (i < 5 || i > 19) output = 0;
        else output = pParser.getC0() + i * pParser.getC1() + i * i * pParser.getC2() + i * i * i * i * pParser.getC3();
        return output;
    }
}
