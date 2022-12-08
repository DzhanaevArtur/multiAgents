package laboratoryWorks.Lab2.self;

import lombok.extern.slf4j.Slf4j;

import java.util.Locale;

import static laboratoryWorks.Lab2.self.agents.Agent1.X;
import static laboratoryWorks.Lab2.self.agents.Agent1.delta;

/**
 * @author Artur Dzhanaev
 * @created 07.12.2022
 */
@Slf4j
public class Help {

    public static final double EPSILON = 0.001;

    public static double agent1(double input) { return Math.pow(Math.E, (0.3 * input)); }
    public static double agent2(double input) { return Math.pow(input, 2); }
    public static double agent3(double input) { return Math.sin(Math.toRadians(input)); }
}
