package LaboratoryWorks.lab4.agents;

import LaboratoryWorks.lab4.behs.Auction;
import LaboratoryWorks.lab4.common.LW4Info;
import Practices.AutoRunnableAgent;
import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 * @description Агент - поставщик: принимает заявки от потребителя и производит заключение контрактов на поставку ЭЭ
 * на один час в виде открытых аукционах для получения наиболее выгодного предложения у текущих производителей ЭЭ
 */
@Slf4j
@AutoRunnableAgent(name = "Distributor", copy = 1)
public class Distributor extends Agent {


    /** Определение поведения поставщиков */
    @Override protected void setup() { addBehaviour(new Auction(this, LW4Info.getLW())); }
}
