package laboratoryWorks.lab3;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Scanner;

/**
 * @author Artur Dzhanaev
 * @created 08.12.2022
 */
@Slf4j
public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("config.xml");
        if (file.exists()) {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) log.info(scanner.nextLine());
            scanner.close();
        }
        else log.info("Hui");
    }
}
