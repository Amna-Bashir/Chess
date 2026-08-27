package game;

import board.Board;
import board.Spot;
import pieces.Piece;

public class Stalemate {

    public boolean stalemateCondition(
        Board board,
        boolean whiteTurn
    ) {

        Check check = new Check();

        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {

                Spot start =
                    board.getSquare()[i][j];

                Piece piece =
                    start.getPiece();

                if (
                    piece != null
                    &&
                    piece.getwhite()
                        == whiteTurn
                ) {

                    for (int x = 0; x < 8; x++) {

                        for (int y = 0; y < 8; y++) {

                            Spot to =
                                board.getSquare()[x][y];

                            if (
                                piece.movestatus(
                                    board,
                                    start,
                                    to
                                )
                            ) {

                                Piece captured =
                                    to.getPiece();

                                boolean wasMoved =
                                    piece.getmoved();

                                // Temporarily make move
                                to.setPiece(piece);
                                start.setPiece(null);

                                piece.setmoved(true);

                                boolean stillInCheck =
                                    check.checkStatus(
                                        board,
                                        whiteTurn
                                    );

                                // Undo move
                                start.setPiece(piece);
                                to.setPiece(captured);

                                piece.setmoved(wasMoved);

                                if (!stillInCheck) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}