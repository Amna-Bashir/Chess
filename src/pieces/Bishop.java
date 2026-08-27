package pieces;

import board.Board;
import board.Spot;

public class Bishop extends Piece {

    public Bishop(
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

        if (
            Math.abs(
                starting.getX() - ending.getX()
            )
            ==
            Math.abs(
                starting.getY() - ending.getY()
            )
        ) {

            int xdirection = 0;
            int ydirection = 0;

            if (ending.getX() - starting.getX() > 0) {
                xdirection = 1;
            }

            if (ending.getX() - starting.getX() < 0) {
                xdirection = -1;
            }

            if (ending.getY() - starting.getY() > 0) {
                ydirection = 1;
            }

            if (ending.getY() - starting.getY() < 0) {
                ydirection = -1;
            }

            int x = starting.getX() + xdirection;
            int y = starting.getY() + ydirection;

            while (
                x != ending.getX()
                &&
                y != ending.getY()
            ) {

                if (
                    board.getSquare()[x][y].getPiece() != null
                ) {
                    return false;
                }

                x += xdirection;
                y += ydirection;
            }

            return true;
        }

        return false;
    }
}