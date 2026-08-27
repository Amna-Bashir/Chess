package ui;

public class Rulebook {

    public void turnTakePieceSetup() {

        System.out.println(
            "Setup:\r\n"
            + "The board is setup as shown. "
            + "There should always be a white square "
            + "at the closest right-hand side for both players. "
            + "Remember that the queen must be on a square "
            + "that matches her color.\r\n\r\n"

            + "Turns:\r\n"
            + "White always moves first, and players alternate turns. "
            + "Players can only move one piece at a time, "
            + "except when castling.\r\n\r\n"

            + "Taking Pieces:\r\n"
            + "Players take pieces when they encounter an opponent "
            + "in their movement path. Only pawns take differently "
            + "than they move. Players cannot take or move through "
            + "their own pieces."
        );
    }

    public void pawnMovement() {

        System.out.println(
            "Pawns only move forward. On the first move a pawn "
            + "can move one or two spaces, every subsequent move "
            + "can only be one space. Pawns move diagonally to "
            + "take opponents.\r\n\r\n"

            + "Pawn Promotion:\r\n"
            + "If a pawn reaches the opposite side of the board, "
            + "it is promoted to a higher piece (except king). "
            + "There is no limit to how many pawns can be promoted."
        );
    }

    public void rook() {

        System.out.println(
            "Rooks move in a continuous line forwards, backwards "
            + "and side-to-side.\r\n"
        );
    }

    public void knight() {

        System.out.println(
            "Knights are the only pieces that \"jump\" off the board. "
            + "Unlike other pieces they are not blocked if there are "
            + "pieces between them and their destination square.\r\n\r\n"

            + "To make it easier to remember how a knight moves think "
            + "of an L. Two spaces in a direction forward, backward or "
            + "side-to-side, and one space at a right turn."
        );
    }

    public void bishop() {

        System.out.println(
            "Bishops move in continuous diagonal lines in any direction.\r\n"
        );
    }

    public void queen() {

        System.out.println(
            "The queen moves in continuous diagonal and straight lines. "
            + "Forward, backward and side-to-side."
        );
    }

    public void king() {

        System.out.println(
            "The king can move in any direction, one square at a time.\r\n\r\n"

            + "A king cannot move to a square that is under attack "
            + "by the opponent."
        );
    }

    public void castling() {

        System.out.println(
            "Castling is the only move that allows two pieces to move "
            + "during the same turn.\r\n\r\n"

            + "During castling a king moves two spaces towards the rook "
            + "that it will castle with, and the rook jumps to the other "
            + "side.\r\n\r\n"

            + "The king can castle to either side as long as:\r\n\r\n"

            + "1. The king has not moved.\r\n"
            + "2. The king is not in check.\r\n"
            + "3. The king does not move through or into check.\r\n"
            + "4. There are no pieces between the king and castling-side rook.\r\n"
            + "5. The castling-side rook has not moved.\r\n\r\n"

            + "It does not matter:\r\n\r\n"

            + "A. If the king was in check, but is no longer.\r\n"
            + "B. If the rook can be attacked by an opponent's piece "
            + "before castling."
        );
    }

    public void enPassant() {

        System.out.println(
            "En passant is a special movement for pawns attacking pawns. "
            + "It only applies if your opponent moves a pawn two spaces, "
            + "and its destination space is next to your pawn. You can "
            + "take the opposing piece by moving forward-diagonal to "
            + "your pawn's attacked square."
        );
    }

    public void check() {

        System.out.println(
            "A king is in check when an opponent's piece is in a position "
            + "that can attack the king. A player must move their king out "
            + "of check, block the check or capture the attacking piece.\r\n\r\n"

            + "A player cannot move their king into check."
        );
    }

    public void checkmate() {

        System.out.println(
            "Putting an opponent's king in \"checkmate\" is the only way "
            + "to win the game.\r\n\r\n"

            + "A king is in checkmate if it is in check, the opponent's "
            + "piece that has the king in check cannot be captured, the "
            + "check cannot be blocked, and the king cannot move to a "
            + "square that is not under attack."
        );
    }

    public void stalemate() {

        System.out.println(
            "Simply put, a \"Stalemate\" is a tie. It is achieved if "
            + "there are no legal moves for a player to make.\r\n\r\n"

            + "If a player has no legal moves but their king is not "
            + "in check, the game is a stalemate."
        );
    }
}