package LaboratoryWorks.lab4.agents;

import Practices.AutoRunnableAgent;
import LaboratoryWorks.lab4.behs.DChatName;
import LaboratoryWorks.lab4.common.Main;
import Practices.TopicHelper;
import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 * @description Агент - поставщик: принимает заявки от потребителя и производит заключение контрактов на поставку ЭЭ
 * на один час в виде открытых аукционах для получения наиболее выгодного предложения у текущих производителей ЭЭ
 */
@Slf4j
@AutoRunnableAgent(name = "Distributor", copy = 3)
public class Distributor extends Agent {

    @Override protected void setup() {
        Main.registration(this);
        TopicHelper.createTopic(this, Main.CHAT + this.getLocalName().split("_")[1]);
        addBehaviour(new DChatName(this));
    }
}
