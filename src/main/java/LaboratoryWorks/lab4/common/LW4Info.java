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
public class LW4Info {

    /** OpenChat ID */
    @Getter @Setter private AID chat;

    /** Участники чата */
    @Getter @Setter private Set<AID> chatUsers = new HashSet<>();

    /** Нагрузка: МЭИ */
    @Getter @Setter private List<Double> MPEI = new ArrayList<>();

    /** Нагрузка: пищевое производство */
    @Getter @Setter private List<Double> foodIndustryFactory = new ArrayList<>();

    /** Нагрузка: обувная фабрика */
    @Getter @Setter private List<Double> shoeFactory = new ArrayList<>();

    /** Электростанция: тепловая */
    @Getter @Setter private List<Double> TPP = new ArrayList<>();

    /** Электростанция: ветряная */
    @Getter @Setter private List<Double> WPS = new ArrayList<>();

    /** Электростанция: солнечная */
    @Getter @Setter private List<Double> SPS = new ArrayList<>();

    /** Счётчик часов */
    @Getter @Setter private static Integer hours = 0;

    /** Алгоритм часов */
    public synchronized Integer getTime() { if (getHours() >= 23 ) setHours(0); return getHours(); }

    /** Class instance */
    @Getter @Setter private static LW4Info lw4Info;

    /** SingleTon realisation */
    public static synchronized LW4Info getLW() { if (getLw4Info() == null) setLw4Info(new LW4Info()); return getLw4Info(); }
}
