package game;

import board.Board;
import board.Spot;
import pieces.Piece;

public class Check {

    public boolean checkStatus(
        Board board,
        boolean colour
    ) {

        boolean opponentPlayer = !colour;

        FindKing find =
            new FindKing();

        Spot kingSpot =
            find.kingFind(
                board,
                colour
            );

        if (kingSpot == null) {
            return false;
        }

        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {

                Spot attackerSpot =
                    board.getSquare()[i][j];

                Piece attacking =
                    attackerSpot.getPiece();

                if (
                    attacking != null
                    &&
                    attacking.getwhite()
                        == opponentPlayer
                ) {

                    if (
                        attacking.movestatus(
                            board,
                            attackerSpot,
                            kingSpot
                        )
                    ) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean checkThreat(
        Board board,
        int x,
        int y,
        boolean colourAtt
    ) {

        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {

                Spot spotAtt =
                    board.getSquare()[i][j];

                Piece attacker =
                    spotAtt.getPiece();

                if (
                    attacker != null
                    &&
                    attacker.getwhite()
                        == colourAtt
                ) {

                    if (
                        attacker.movestatus(
                            board,
                            spotAtt,
                            board.getSquare()[x][y]
                        )
                    ) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}