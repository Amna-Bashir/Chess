import java.io.IOException;
import java.util.Scanner;

import account.AccountStats;
import account.FileManager;
import account.Leaderboard;
import account.MembershipSystem;

import board.Board;
import board.Spot;

import game.Check;
import game.Checkmate;
import game.Game;
import game.Stalemate;
import game.VariablesStatic;

import pieces.Piece;

import ui.Rulebook;

public class Chess {

    public static void main(String[] args)
        throws Exception {

        Scanner input =
            new Scanner(System.in);

        String username = "";
        String password = "";
        String key = "";
        String decision = "";

        MembershipSystem membership =
            new MembershipSystem();

        AccountStats account =
            new AccountStats();

        Rulebook rule =
            new Rulebook();

        Leaderboard leaderboard =
            new Leaderboard();

        // Load user data
        try {

            FileManager.conveyUserData();

        } catch (IOException e) {

            System.out.println(
                "Could not load user data: "
                + e.getMessage()
            );
        }

        // Welcome page
        System.out.println(
            "             ________________________________________________\r\n"
            + "            /                                                \\\r\n"
            + "           |    _________________________________________     |\r\n"
            + "           |   |                                         |    |\r\n"
            + "           |   |               CHESS.COM                 |    |\r\n"
            + "           |   |                                         |    |\r\n"
            + "           |   | PLAY CHESS ONLINE                       |    |\r\n"
            + "           |   |    "
            + account.gamestoday
            + " Games Today  "
            + account.playingnow
            + " Playing now     |    |\r\n"
            + "           |   |                                         |    |\r\n"
            + "           |   | LEADERBOARD:                            |    |\r\n"
            + "           |   | "
            + leaderboard.displayLeaderboard()
            + "      |    |\r\n"
            + "           |   |                                         |    |\r\n"
            + "           |   |                                         |    |\r\n"
            + "           |   |                                         |    |\r\n"
            + "           |   |  To begin: Sign up, login or continue   |    |\r\n"
            + "           |   |              as guest.                  |    |\r\n"
            + "           |   |                                         |    |\r\n"
            + "           |   |_________________________________________|    |\r\n"
            + "           |                                                  |\r\n"
            + "            \\_________________________________________________/\r\n"
            + "                   \\___________________________________/"
        );

        // Login / signup
        System.out.print(
            "\nSign up, Login, Guest: "
        );

        String accountType =
            input.nextLine();

        boolean flag = true;

        if (
            accountType.equalsIgnoreCase("sign up")
            ||
            accountType.equalsIgnoreCase("signup")
        ) {

            while (flag) {

                System.out.print(
                    "👤 Username: "
                );

                username =
                    input.next();

                System.out.print(
                    "🔒 Password: "
                );

                password =
                    input.next();

                if (
                    MembershipSystem.usernames
                        .contains(username)
                ) {

                    System.out.println(
                        "Username taken. Please enter a new one."
                    );

                } else {

                    flag = false;
                }
            }

            membership.addMembership(
                username,
                password
            );

            // Set username/password as current user
            membership.membershipStatus(
                username,
                password
            );

            System.out.println(
                "Your one-time security code is: "
                + membership.createOneTimePassword()
            );

        } else if (
            accountType.equalsIgnoreCase("log in")
            ||
            accountType.equalsIgnoreCase("login")
        ) {

            while (flag) {

                System.out.print(
                    "👤 Username: "
                );

                username =
                    input.next();

                System.out.print(
                    "🔒 Password: "
                );

                password =
                    input.next();

                System.out.print(
                    "🔑 One-time code: "
                );

                key =
                    input.next();

                if (
                    membership.membershipStatus(
                        username,
                        password
                    )
                ) {

                    if (
                        !membership.checkIdentity(key)
                    ) {

                        System.out.println(
                            "❌ Login failed. "
                            + "Invalid security code."
                        );

                        username = "";

                        return;

                    } else {

                        flag = false;
                    }

                } else {

                    System.out.println(
                        "\n\nWould you like to "
                        + "re-try or continue as guest?"
                        + "\nTry/guest: "
                    );

                    decision =
                        input.next();

                    if (
                        decision.equalsIgnoreCase("guest")
                    ) {

                        username = "";
                        password = "";
                        key = "";

                        flag = false;
                    }
                }
            }

        } else {

            System.out.println(
                "Continuing as guest..."
            );
        }

        System.out.println(
            "\n....Processing...."
        );

        try {

            Thread.sleep(2000);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }

        System.out.println(
            "Request processed!"
        );

        // Ensure account stats list has an entry
        if (
            !username.equals("")
            &&
            MembershipSystem.usernames.contains(username)
        ) {

            int userIndex =
                MembershipSystem.usernames
                    .indexOf(username);

            while (
                AccountStats.gameswon.size()
                <= userIndex
            ) {
                AccountStats.gameswon.add(0);
            }

            while (
                AccountStats.gameslost.size()
                <= userIndex
            ) {
                AccountStats.gameslost.add(0);
            }
        }

        System.out.println(
            "__________________________________________________________________________________"
        );

        System.out.println(
            "                     o\r\n"
            + "                   /\\^/\\\r\n"
            + "                  |  /  )\r\n"
            + "                  | /  /\r\n"
            + "                   Y  /\r\n"
            + "                   |  |\r\n"
            + "                   |  |\r\n"
            + "                  /    \\\r\n"
            + "      ___________|______|___________\r\n"
            + "     |                              |\r\n"
            + "     |     Welcome to game play     |\r\n"
            + "     |______________________________|\r\n"
        );

        input.nextLine();

        // Main menu
        System.out.println(
            "Would you like to:"
        );

        System.out.println(
            " ______________"
            + "\n|     PLAY     |"
            + "\n|______________|"
        );

        System.out.println(
            " ______________"
            + "\n| ACCOUNT STAT |"
            + "\n|______________|"
        );

        System.out.println(
            " ______________"
            + "\n|    RULES     |"
            + "\n|______________|"
        );

        System.out.println(
            " ______________"
            + "\n|     QUIT     |"
            + "\n|______________|"
        );

        boolean regulation = true;

        String menuOption = "";

        while (regulation) {

            System.out.print(
                "\nPlay/stats/rules/quit: "
            );

            menuOption =
                input.nextLine();

            int index = 0;

            // -------------------------
            // STATS
            // -------------------------

            if (
                menuOption.equalsIgnoreCase(
                    "stats"
                )
            ) {

                if (
                    username.equals("")
                    ||
                    !MembershipSystem.usernames
                        .contains(username)
                ) {

                    System.out.println(
                        "Guest users do not have stats."
                    );

                } else {

                    index =
                        MembershipSystem.usernames
                            .indexOf(username);

                    if (index != -1) {

                        System.out.println(
                            "Username: "
                            + username
                            + "\nGames won: "
                            + AccountStats.gameswon
                                .get(index)
                            + "\nGames lost: "
                            + AccountStats.gameslost
                                .get(index)
                        );
                    }
                }

            // -------------------------
            // PLAY
            // -------------------------

            } else if (
                menuOption.equalsIgnoreCase(
                    "play"
                )
            ) {

                // Reset castling variables
                VariablesStatic.whiteKingMoved =
                    false;

                VariablesStatic.whiteKSiderook =
                    false;

                VariablesStatic.whiteQSiderook =
                    false;

                VariablesStatic.blackKingMoved =
                    false;

                VariablesStatic.blackKSiderook =
                    false;

                VariablesStatic.blackQSiderook =
                    false;

                System.out.println(
                    "Player 1: WHITE SIDE"
                );

                System.out.println(
                    "Player 2: BLACK SIDE"
                );

                int play = 1;

                Board board =
                    new Board();

                Game game =
                    new Game();

                game.board = board;

                boolean gameplay = true;

                boolean move = true;

                boolean whiteTurn = true;

                Check checkLogic =
                    new Check();

                Checkmate checkmateLogic =
                    new Checkmate();

                Stalemate stalemateLogic =
                    new Stalemate();

                while (gameplay) {

                    board.printBoard();

                    // Check
                    if (
                        checkLogic.checkStatus(
                            board,
                            whiteTurn
                        )
                    ) {

                        System.out.println(
                            "CAUTION! CHECK DETECTED"
                        );
                    }

                    // Checkmate
                    if (
                        checkmateLogic.checkmateStatus(
                            board,
                            whiteTurn
                        )
                    ) {

                        System.out.println(
                            "CAUTION! CHECKMATE DETECTED"
                        );

                        if (!username.equals("")) {

                            index =
                                MembershipSystem.usernames
                                    .indexOf(username);

                            if (index != -1) {

                                if (whiteTurn) {

                                    AccountStats.gameslost
                                        .set(
                                            index,
                                            AccountStats.gameslost
                                                .get(index)
                                                + 1
                                        );

                                } else {

                                    AccountStats.gameswon
                                        .set(
                                            index,
                                            AccountStats.gameswon
                                                .get(index)
                                                + 1
                                        );
                                }
                            }
                        }

                        gameplay = false;

                        break;
                    }

                    // Stalemate
                    if (
                        stalemateLogic
                            .stalemateCondition(
                                board,
                                whiteTurn
                            )
                    ) {

                        System.out.println(
                            "CAUTION! STALEMATE DETECTED. "
                            + "Game drawn"
                        );

                        gameplay = false;

                        break;
                    }

                    String colour;

                    if (play == 1) {
                        colour = "white's turn";
                    } else {
                        colour = "black's turn";
                    }

                    System.out.println(
                        "Player "
                        + play
                        + ", "
                        + colour
                    );

                    move = true;

                    while (move) {

                        System.out.print(
                            "\nEnter x coordinate "
                            + "(row) of start block: "
                        );

                        int xstart =
                            input.nextInt();

                        System.out.print(
                            "Enter y coordinate "
                            + "(column) of start block: "
                        );

                        int ystart =
                            input.nextInt();

                        System.out.print(
                            "\nEnter x coordinate "
                            + "(row) of end block: "
                        );

                        int xend =
                            input.nextInt();

                        System.out.print(
                            "Enter y coordinate "
                            + "(column) of end block: "
                        );

                        int yend =
                            input.nextInt();

                        try {

                            board.getValidation(
                                xstart,
                                ystart
                            );

                            board.getValidation(
                                xend,
                                yend
                            );

                        } catch (Exception e) {

                            System.out.println(
                                "❌ Coordinates out of bounds. "
                                + "Try again."
                            );

                            continue;
                        }

                        Spot startSpot =
                            board.getSquare()
                                [xstart][ystart];

                        Spot endSpot =
                            board.getSquare()
                                [xend][yend];

                        Piece movingPiece =
                            startSpot.getPiece();

                        if (movingPiece == null) {

                            System.out.println(
                                "❌ No piece at the start position."
                            );

                            continue;
                        }

                        if (
                            movingPiece.getwhite()
                                != whiteTurn
                        ) {

                            System.out.println(
                                "❌ Not your turn. "
                                + "That's the opponent's piece."
                            );

                            continue;
                        }

                        if (
                            !movingPiece.movestatus(
                                board,
                                startSpot,
                                endSpot
                            )
                        ) {

                            System.out.println(
                                "❌ Illegal move for this piece."
                            );

                            continue;
                        }

                        if (
                            endSpot.getPiece() != null
                        ) {

                            String captured =
                                endSpot.getPiece()
                                    .getClass()
                                    .getSimpleName();

                            System.out.println(
                                "🎯 Captured: "
                                + captured
                            );
                        }

                        game.whiteTurn =
                            whiteTurn;

                        boolean successful =
                            game.gameplay(
                                xstart,
                                ystart,
                                xend,
                                yend
                            );

                        if (successful) {

                            move = false;

                        } else {

                            System.out.println(
                                "INVALID MOVE. "
                                + "RE-ENTER CHOICE."
                            );
                        }
                    }

                    // Switch players
                    whiteTurn =
                        !whiteTurn;

                    play =
                        (play % 2) + 1;
                }

                // Game summary
                System.out.println(
                    "Would you like to see the "
                    + "summary of all the moves made "
                    + "or the pieces captured?"
                    + "\nEnter moves for move history "
                    + "or captured for all captured pieces"
                );

                String report =
                    input.nextLine();

                if (
                    report.equalsIgnoreCase(
                        "moves"
                    )
                ) {

                    game.printMoveHistory();

                } else if (
                    report.equalsIgnoreCase(
                        "captured"
                    )
                ) {

                    game.displayCapturedPieces();

                } else {

                    System.out.println(
                        "APPLICATION CONTINUED."
                    );
                }

            // -------------------------
            // RULES
            // -------------------------

            } else if (
                menuOption.equalsIgnoreCase(
                    "rules"
                )
            ) {

                boolean running = true;

                System.out.println(
                    "\nHere are the rules: "
                    + "\n1. Setup, Turns, and Taking Pieces"
                    + "\n2. Pawn Movement"
                    + "\n3. Rook"
                    + "\n4. Knight"
                    + "\n5. Bishop"
                    + "\n6. Queen"
                    + "\n7. King"
                    + "\n8. Castling"
                    + "\n9. En Passant"
                    + "\n10. Check"
                    + "\n11. Checkmate"
                    + "\n12. Stalemate"
                );

                while (running) {

                    System.out.print(
                        "\nEnter a number 1 to 12 "
                        + "to see further information."
                        + "\nElse enter 0 to exit: "
                    );

                    int choice =
                        input.nextInt();

                    input.nextLine();

                    if (choice == 0) {

                        running = false;

                    } else if (choice == 1) {

                        rule.turnTakePieceSetup();

                    } else if (choice == 2) {

                        rule.pawnMovement();

                    } else if (choice == 3) {

                        rule.rook();

                    } else if (choice == 4) {

                        rule.knight();

                    } else if (choice == 5) {

                        rule.bishop();

                    } else if (choice == 6) {

                        rule.queen();

                    } else if (choice == 7) {

                        rule.king();

                    } else if (choice == 8) {

                        rule.castling();

                    } else if (choice == 9) {

                        rule.enPassant();

                    } else if (choice == 10) {

                        rule.check();

                    } else if (choice == 11) {

                        rule.checkmate();

                    } else if (choice == 12) {

                        rule.stalemate();
                    }
                }

            // -------------------------
            // QUIT
            // -------------------------

            } else if (
                menuOption.equalsIgnoreCase(
                    "quit"
                )
            ) {

                regulation = false;

            } else {

                System.out.println(
                    "\nInvalid option.\n"
                );
            }

            // Save data
            try {

                FileManager.saveUserData(
                    MembershipSystem.usernames,
                    AccountStats.gameswon,
                    AccountStats.gameslost
                );

                System.out.println(
                    "User data saved successfully."
                );

            } catch (IOException e) {

                System.out.println(
                    "User data not saved: "
                    + e.getMessage()
                );
            }
        }

        input.close();
    }
}