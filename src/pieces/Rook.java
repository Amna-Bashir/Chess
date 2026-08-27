package pieces;

import board.Board;
import board.Spot;
import game.VariablesStatic;

public class Rook extends Piece {

    public Rook(
        boolean white,
        boolean pawnhasmoved
    ) {
        super(white, pawnhasmoved);
    }

    @Override
    public boolean movestatus(
        Board board,
        Spot starting,
        Spot ending
    ) {

        if (
            ending.getPiece() != null
            &&
            ending.getPiece().getwhite() == getwhite()
        ) {
            return false;
        }

        // Must move horizontally or vertically
        if (
            starting.getX() != ending.getX()
            &&
            starting.getY() != ending.getY()
        ) {
            return false;
        }

        // Vertical
        if (starting.getX() == ending.getX()) {

            int num;

            if (ending.getY() > starting.getY()) {
                num = 1;
            } else {
                num = -1;
            }

            for (
                int y = starting.getY() + num;
                y != ending.getY();
                y += num
            ) {

                if (
                    board.getSquare()[starting.getX()][y]
                        .getPiece() != null
                ) {
                    return false;
                }
            }
        }

        // Horizontal
        if (starting.getY() == ending.getY()) {

            int num;

            if (ending.getX() > starting.getX()) {
                num = 1;
            } else {
                num = -1;
            }

            for (
                int x = starting.getX() + num;
                x != ending.getX();
                x += num
            ) {

                if (
                    board.getSquare()[x][starting.getY()]
                        .getPiece() != null
                ) {
                    return false;
                }
            }
        }

        // Track rook movement for castling
        if (getwhite()) {

            if (
                starting.getX() == 0
                &&
                starting.getY() == 0
            ) {
                VariablesStatic.whiteQSiderook = true;

            } else if (
                starting.getX() == 0
                &&
                starting.getY() == 7
            ) {
                VariablesStatic.whiteKSiderook = true;
            }

        } else {

            if (
                starting.getX() == 7
                &&
                starting.getY() == 0
            ) {
                VariablesStatic.blackQSiderook = true;

            } else if (
                starting.getX() == 7
                &&
                starting.getY() == 7
            ) {
                VariablesStatic.blackKSiderook = true;
            }
        }

        return true;
    }
}