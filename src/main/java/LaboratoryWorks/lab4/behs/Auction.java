package LaboratoryWorks.lab4.behs;

import LaboratoryWorks.lab4.common.LW4Info;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 26.12.2022
 */
@Slf4j
public class Auction extends FSMBehaviour {


    /**
     * @description
     * 1) Принимаем сообщение от потребителя;
     * 2) Создаём чат (аукцион) со всеми потребителями и одним поставщиком;
     * 3) Полученные из сообщения данные закидываем в аукцион;
     * 4) Потребитель, получив сообщение в чате, смотрит, есть ли у него достаточное количество ЭЭ,
     * если есть, отправляет в чат свою цену (на сей процесс выделяется 2 (предположение) секунды);
     * 5) Если производители ответили, поставщик выбирает минимальную среди отправленных цен. В противном случае
     * поставщик должен поделить один раз (!!!) количество ЭЭ (на 3 (предположение) части) и пытаться получить ЭЭ
     * от нескольких поставщиков;
     * 6) После успешного проведения торгов и победы на аукционе производитель должен резервировать мощность,
     * запрошенную агентом-поставщиком и учитывать эту зарезервированную мощность при следующих торговых сессиях;
     * 7) Постфактум м поставщик отправляет своему потребителю отчёт с ценой и количеством приобретённой ЭЭ.
     */
    public Auction(Agent myAgent, LW4Info lw4Info) {
        super(myAgent);
        log.info("{} started!!!", getClass().getSimpleName());

        registerFirstState(new AZero(myAgent, lw4Info), "one");
        registerLastState(new AFirst(myAgent, lw4Info), "two");

        registerDefaultTransition("one", "two");
    }
}
