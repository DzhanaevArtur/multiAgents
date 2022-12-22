package LaboratoryWorks.third.common;

import Practices.AgentFounder;
import jade.core.Runtime;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Artur Dzhanaev
 * @created 22.12.2022
 */
@Slf4j
public class Main {

    public final static List<String> PARTICIPANTS = new ArrayList<>();

    public static void main(String[] args) {
        Runtime instance = Runtime.instance();
        instance.setCloseVM(false);
        instance.createMainContainer(AgentFounder.founder(Node.class));
    }

    public static Config parser() {
        Config cfgTimes;
        try {
            cfgTimes = (Config) JAXBContext.newInstance(Config.class)
                    .createUnmarshaller()
                    .unmarshal(new File("src/main/resources/dtdAndXml/LaboratoryWorks/3/names.xml"));
        } catch (JAXBException e) { throw new RuntimeException(e); }
        return cfgTimes;
    }

    public static List<Neighbor> choose(@NotNull String name) {
        return parser().getBros()
                .stream()
                .map(Bro::getNeighborList)
                .toList()
                .get(Integer.parseInt(name.split("_")[1]) - 1)
                .stream()
                .sorted(Comparator.comparingInt(Neighbor::getLength))
                .toList();
    }

    public static Neighbor res(@NotNull String name) {
        List<Neighbor> list = choose(name);
        Neighbor output;
        int n = 0;
        for (int i = 0; i < list.size(); i++) if (list.get(i).getLength() == list.get(n).getLength()) n = i;
        if (!PARTICIPANTS.contains(name)) {
            if (name.equals("Node_9")) output = list.get(n + 1);
            else output = list.get(n);
        } else output = list.get(n + 2);
        return output;
    }
}
