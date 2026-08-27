package pieces;

import board.Board;
import board.Spot;

public class Knight extends Piece {

    public Knight(
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
            ) == 2
            &&
            Math.abs(
                starting.getY() - ending.getY()
            ) == 1
        ) {
            return true;
        }

        if (
            Math.abs(
                starting.getX() - ending.getX()
            ) == 1
            &&
            Math.abs(
                starting.getY() - ending.getY()
            ) == 2
        ) {
            return true;
        }

        return false;
    }
}