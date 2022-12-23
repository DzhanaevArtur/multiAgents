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
        Config config;
        try {
            config = (Config) JAXBContext.newInstance(Config.class)
                    .createUnmarshaller()
                    .unmarshal(new File("src/main/resources/dtdAndXml/LaboratoryWorks/3/names.xml"));
        } catch (JAXBException e) { throw new RuntimeException(e); }
        return config;
    }

    public static Neighbor res(@NotNull String name) {
        Neighbor output = null;
        List<Neighbor> another = new ArrayList<>();
        for (Bro bro : parser().getBros()) {
            if (bro.getId().equals(name)) {
                List<Neighbor> list = bro.getNeighborList()
                        .stream()
                        .sorted(Comparator.comparingInt(Neighbor::getLength))
                        .toList();
                for (Neighbor neighbor : list) {
                    String id = neighbor.getId();
                    if (id.equals(bro.getDest())) output = neighbor;
                    else if (!PARTICIPANTS.contains(id)) output = neighbor;
                    else {
                        for (Neighbor n : list) if (!PARTICIPANTS.contains(n.getId())) another.add(n);
                        another = another.stream().filter(x -> x.getId().equals(bro.getDest()) ||
                            (x.getLength() == list.stream().mapToInt(Neighbor::getLength).min().getAsInt())).toList();
                        output = another.get(another.size() - 1);
                    }
                    PARTICIPANTS.add(name);
                    break;
                }
                break;
            }
        }
        return output;
    }
}
