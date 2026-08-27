package game;

import board.Board;
import board.Spot;
import pieces.Piece;

public class Checkmate extends Check {

    public Checkmate() {
        super();
    }

    public boolean checkmateStatus(
        Board board,
        boolean turn
    ) {

        // Must already be in check
        if (!super.checkStatus(board, turn)) {
            return false;
        }

        // Search for any legal move
        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {

                Spot startingSpot =
                    board.getSquare()[i][j];

                Piece piece =
                    startingSpot.getPiece();

                if (
                    piece != null
                    &&
                    piece.getwhite() == turn
                ) {

                    for (int x = 0; x < 8; x++) {

                        for (int y = 0; y < 8; y++) {

                            Spot endingSpot =
                                board.getSquare()[x][y];

                            if (
                                !piece.movestatus(
                                    board,
                                    startingSpot,
                                    endingSpot
                                )
                            ) {
                                continue;
                            }

                            Piece captured =
                                endingSpot.getPiece();

                            boolean wasMoved =
                                piece.getmoved();

                            // Temporarily make move
                            endingSpot.setPiece(piece);
                            startingSpot.setPiece(null);

                            piece.setmoved(true);

                            boolean stillInCheck =
                                super.checkStatus(
                                    board,
                                    turn
                                );

                            // Undo move
                            endingSpot.setPiece(captured);
                            startingSpot.setPiece(piece);

                            piece.setmoved(wasMoved);

                            if (!stillInCheck) {
                                return false;
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}