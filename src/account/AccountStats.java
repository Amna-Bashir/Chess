package account;

import java.util.ArrayList;
import java.util.Random;

public class AccountStats {

    public static ArrayList<Integer> gameswon = new ArrayList<>();
    public static ArrayList<Integer> gameslost = new ArrayList<>();

    Random rand = new Random();

    public int gamestoday = rand.nextInt(5000);
    public int playingnow = rand.nextInt(1000);
}