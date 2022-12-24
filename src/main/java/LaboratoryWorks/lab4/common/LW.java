package LaboratoryWorks.lab4.common;

import jade.core.AID;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Artur Dzhanaev
 * @created 24.12.2022
 */
@Slf4j
public class LW {

    @Getter @Setter private AID chat;
    @Getter @Setter private List<AID> pChatUsers = new ArrayList<>();
    private static LW lw;

    public static synchronized LW getLW() {
        if (lw == null) lw = new LW();
        return lw;
    }
}
