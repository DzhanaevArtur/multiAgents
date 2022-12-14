package LaboratoryWorks.lab4.common;

import jade.core.AID;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author Artur Dzhanaev
 * @created 24.12.2022
 * @description Модель данных для данной работы
 */
@Slf4j
@Getter
public class LW4Info {


    /** Идентификатор чата аукциона */
    @Setter private AID chat;

    /** Участники чата */
    private final Set<AID> users = new HashSet<>();


    /** Нагрузка: МЭИ */
    private final List<Double> MPEI = new ArrayList<>();

    /** Нагрузка: пищевое производство */
    private final List<Double> foodIndustryFactory = new ArrayList<>();

    /** Нагрузка: обувная фабрика */
    private final List<Double> shoeFactory = new ArrayList<>();


    /** Суточная совокупность генерируемой ЭЭ ТЭС */
    private final List<Double> t = new ArrayList<>();

    /** Суточная совокупность генерируемой ЭЭ ВЭС */
    private final List<Double> w = new ArrayList<>();

    /** Суточная совокупность генерируемой ЭЭ СЭС */
    private final List<Double> s = new ArrayList<>();


    /** Экземпляр класса для синглтон */
    private static LW4Info lw4;

    /** Синглтон */
    public static synchronized LW4Info getLW() { if (lw4 == null) lw4 = new LW4Info(); return lw4; }
}
