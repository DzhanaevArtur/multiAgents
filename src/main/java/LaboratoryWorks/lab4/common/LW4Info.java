package LaboratoryWorks.lab4.common;

import jade.core.AID;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Artur Dzhanaev
 * @created 24.12.2022
 */
@Slf4j
@Getter
@Setter
public class LW4Info {

    /** Идентификатор чата аукциона */
    private AID chat;

    /** Участники чата */
    private Set<AID> chatUsers = new HashSet<>();

    /** Нагрузка: МЭИ */
    private List<Double> MPEI = new ArrayList<>();

    /** Нагрузка: пищевое производство */
    private List<Double> foodIndustryFactory = new ArrayList<>();

    /** Нагрузка: обувная фабрика */
    private List<Double> shoeFactory = new ArrayList<>();

    /** Суточная совокупность генерируемой ЭЭ ТЭС */
    private List<Double> TPP = new ArrayList<>();

    /** Суточная совокупность генерируемой ЭЭ ВЭС */
    private List<Double> WPS = new ArrayList<>();

    /** Суточная совокупность генерируемой ЭЭ СЭС */
    private List<Double> SPS = new ArrayList<>();

    /** Экземпляр класса для синглтон */
    private static LW4Info lw4;

    /** Синглтон */
    public static synchronized LW4Info getLW() { if (lw4 == null) lw4 = new LW4Info(); return lw4; }
}
