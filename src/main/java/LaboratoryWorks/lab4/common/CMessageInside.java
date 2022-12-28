package LaboratoryWorks.lab4.common;

import jade.lang.acl.ACLMessage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

/**
 * @author Artur Dzhanaev
 * @created 28.12.2022
 */
@Slf4j
@Getter
public class CMessageInside {


    private final double energyValue;

    private final int maxPossiblePrice;

    private final int counter;

    public CMessageInside(@NotNull ACLMessage aclMessage) {
        String[] split = aclMessage.getContent().split(";");
        energyValue      = Double.parseDouble(split[0]);
        maxPossiblePrice = Integer.parseInt  (split[1]);
        counter          = Integer.parseInt  (split[2]);
    }
}
