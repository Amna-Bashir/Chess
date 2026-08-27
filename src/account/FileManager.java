package account;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileManager {

    public static void saveUserData(
        java.util.ArrayList<String> usernames,
        java.util.ArrayList<Integer> wins,
        java.util.ArrayList<Integer> losses
    ) throws IOException {

        try (FileWriter writer = new FileWriter("database.txt")) {

            for (int i = 0; i < usernames.size(); i++) {

                writer.write(
                    usernames.get(i)
                    + ","
                    + wins.get(i)
                    + ","
                    + losses.get(i)
                    + "\n"
                );
            }
        }
    }

    public static void conveyUserData() throws IOException {

        try (
            BufferedReader reader =
                new BufferedReader(
                    new FileReader("database.txt")
                )
        ) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                if (parts.length == 3) {

                    MembershipSystem.usernames.add(parts[0]);

                    AccountStats.gameswon.add(
                        Integer.parseInt(parts[1])
                    );

                    AccountStats.gameslost.add(
                        Integer.parseInt(parts[2])
                    );
                }
            }

        } catch (FileNotFoundException e) {

            System.out.println(
                "No existing profiles found."
            );
        }
    }
}