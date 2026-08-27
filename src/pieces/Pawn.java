package pieces;

import board.Board;
import board.Spot;

public class Pawn extends Piece {

    public Pawn(
        boolean white,
        boolean pawnhasmoved
    ) {
        super(white, pawnhasmoved);
    }

    public boolean promote(int x) {

        return (
            getwhite() && x == 7
        )
        ||
        (
            !getwhite() && x == 0
        );
    }

    @Override
    public boolean movestatus(
        Board board,
        Spot starting,
        Spot ending
    ) {

        int direction;

        if (getwhite()) {
            direction = 1;
        } else {
            direction = -1;
        }

        if (
            ending.getPiece() != null
            &&
            ending.getPiece().getwhite() == getwhite()
        ) {
            return false;
        }

        int x =
            ending.getX() - starting.getX();

        int y =
            ending.getY() - starting.getY();

        // One square forward
        if (
            x == direction
            &&
            y == 0
            &&
            ending.getPiece() == null
        ) {
            return true;
        }

        // Two squares on first move
        if (
            x == 2 * direction
            &&
            y == 0
            &&
            !super.getmoved()
        ) {

            return (
                ending.getPiece() == null
                &&
                board.getSquare()[
                    starting.getX() + direction
                ][starting.getY()].getPiece() == null
            );
        }

        // Diagonal capture
        if (
            x == direction
            &&
            Math.abs(y) == 1
        ) {

            if (
                ending.getPiece() != null
                &&
                ending.getPiece().getwhite() != getwhite()
            ) {
                return true;
            }
        }

        // En passant
        Spot enPassant =
            board.getPassantTarget();

        if (
            enPassant != null
            &&
            ending.getX() == enPassant.getX()
            &&
            ending.getY() == enPassant.getY()
        ) {
            return true;
        }

        return false;
    }
}