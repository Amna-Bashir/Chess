package game;

import board.Board;
import board.Spot;
import pieces.King;
import pieces.Piece;

public class FindKing {

    public Spot kingFind(
        Board board,
        boolean colour
    ) {

        return kingFindRecursion(
            board,
            colour,
            0,
            0
        );
    }

    private Spot kingFindRecursion(
        Board board,
        boolean colour,
        int row,
        int column
    ) {

        if (row >= 8) {
            return null;
        }

        Piece piece =
            board.getSquare()[row][column]
                .getPiece();

        if (
            piece != null
            &&
            piece instanceof King
            &&
            piece.getwhite() == colour
        ) {

            return board.getSquare()[row][column];
        }

        int nextRow = row;
        int nextColumn = column + 1;

        if (nextColumn >= 8) {

            nextRow = row + 1;
            nextColumn = 0;
        }

        return kingFindRecursion(
            board,
            colour,
            nextRow,
            nextColumn
        );
    }
}