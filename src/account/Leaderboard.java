package account;

import java.util.ArrayList;

public class Leaderboard {

    public int index;
    public int wins;
    public int losses;
    public String username;

    public Leaderboard(
        int index,
        int wins,
        int losses,
        String username
    ) {
        this.index = index;
        this.wins = wins;
        this.losses = losses;
        this.username = username;
    }

    public Leaderboard() {
    }

    public String displayLeaderboard() {

        if (MembershipSystem.usernames.isEmpty()) {
            return "No registered players found.";
        }

        ArrayList<Leaderboard> players =
            new ArrayList<>();

        for (
            int i = 0;
            i < MembershipSystem.usernames.size();
            i++
        ) {

            int playerWins = 0;
            int playerLosses = 0;

            if (i < AccountStats.gameswon.size()) {
                playerWins = AccountStats.gameswon.get(i);
            }

            if (i < AccountStats.gameslost.size()) {
                playerLosses = AccountStats.gameslost.get(i);
            }

            String playerUsername =
                MembershipSystem.usernames.get(i);

            players.add(
                new Leaderboard(
                    i,
                    playerWins,
                    playerLosses,
                    playerUsername
                )
            );
        }

        // Sort players by wins, highest first
        for (int k = 0; k < players.size() - 1; k++) {

            for (int j = k + 1; j < players.size(); j++) {

                if (players.get(j).wins > players.get(k).wins) {

                    Leaderboard temp = players.get(k);

                    players.set(k, players.get(j));
                    players.set(j, temp);
                }
            }
        }

        int maxDisplay =
            Math.min(3, players.size());

        StringBuilder result =
            new StringBuilder();

        for (int s = 0; s < maxDisplay; s++) {

            Leaderboard player = players.get(s);

            String medal;

            if (s == 0) {
                medal = "🥇";
            } else if (s == 1) {
                medal = "🥈";
            } else {
                medal = "🥉";
            }

            result.append(
                medal
                + " "
                + player.username
                + " ("
                + player.wins
                + " wins)\n"
            );
        }

        return result.toString();
    }
}