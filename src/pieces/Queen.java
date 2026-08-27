package pieces;

import board.Board;
import board.Spot;

public class Queen extends Piece {

    public Queen(
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

        int direction = 0;
        int xdirection = 0;
        int ydirection = 0;

        // Cannot capture own piece
        if (
            ending.getPiece() != null
            &&
            ending.getPiece().getwhite() == getwhite()
        ) {
            return false;
        }

        // Vertical
        if (
            starting.getY() - ending.getY() == 0
            &&
            Math.abs(
                starting.getX() - ending.getX()
            ) != 0
        ) {

            if (ending.getX() - starting.getX() > 0) {
                direction = 1;
            } else {
                direction = -1;
            }

            int x = starting.getX() + direction;

            while (x != ending.getX()) {

                if (
                    board.getSquare()[x][starting.getY()]
                        .getPiece() != null
                ) {
                    return false;
                }

                x += direction;
            }

            return true;
        }

        // Horizontal
        if (
            Math.abs(
                starting.getX() - ending.getX()
            ) == 0
            &&
            Math.abs(
                starting.getY() - ending.getY()
            ) != 0
        ) {

            if (ending.getY() - starting.getY() > 0) {
                direction = 1;
            } else {
                direction = -1;
            }

            int y = starting.getY() + direction;

            while (y != ending.getY()) {

                if (
                    board.getSquare()[starting.getX()][y]
                        .getPiece() != null
                ) {
                    return false;
                }

                y += direction;
            }

            return true;
        }

        // Diagonal
        if (
            Math.abs(
                starting.getX() - ending.getX()
            )
            ==
            Math.abs(
                starting.getY() - ending.getY()
            )
        ) {

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