package LaboratoryWorks.lab4.behs.consumption;

import LaboratoryWorks.lab4.common.CParser;
import LaboratoryWorks.lab4.common.LW4Info;
import LaboratoryWorks.lab4.common.Main;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Locale;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class CBuyRequest extends Behaviour {

    List<Double> one, two, thr;
    private Boolean trigger = false;
    private final CParser cParser;
    private final Agent myAgent;

    public CBuyRequest(Agent myAgent, @NotNull LW4Info lw4Info, CParser cParser) {
        super(myAgent);
        this.myAgent = myAgent;
        this.cParser = cParser;

        one = lw4Info.getMPEI();
        two = lw4Info.getFoodIndustryFactory();
        thr = lw4Info.getShoeFactory();
    }

    @Override public void onStart() {
        switch (myAgent.getLocalName()) {
            case "Consumer_1" -> addingToLoadList(one, cParser);
            case "Consumer_2" -> addingToLoadList(two, cParser);
            case "Consumer_3" -> addingToLoadList(thr, cParser);
        }
    }

    /** Отправка запроса поставщику на приобретение ЭЭ */
    @Override public void action() {
        switch (myAgent.getLocalName()) {
            case "Consumer_1" -> { while (true) aclMessageSending(one); }
            case "Consumer_2" -> { while (true) aclMessageSending(two); }
            case "Consumer_3" -> { while (true) aclMessageSending(thr); }
        }
        trigger = true;
    }

    /** Обработка данных из конфигурационного файла */
    private void addingToLoadList(@NotNull List<Double> list, @NotNull CParser cParser) {
        List<Double> coefficients = cParser.getPowerCoefficients();
        for (int i = 1; i < coefficients.size(); i++) list.add((coefficients.get(0) * coefficients.get(i)) / 100);
    }

    /** Отправка определённому поставщику запроса о покупке ЭЭ */
    private synchronized void aclMessageSending(@NotNull List<Double> l) {
        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
        aclMessage.addReceiver(new AID("Distributor_" + myAgent.getLocalName().split("_")[1], false));
        aclMessage.setProtocol("EnergyBuy");
        aclMessage.setContent(String.format(Locale.US, "%.3f", Main.value(l, Main.timer(Main.START, Main.F))));
        myAgent.send(aclMessage);
//        log.info("\"{}\" sent to {}", aclMessage.getContent(), ((AID) aclMessage.getAllReceiver().next()).getLocalName());
    }

    /** Завершение работы поведения, для исключения возникновения коллизий */
    @Override public boolean done() { return trigger; }
}
