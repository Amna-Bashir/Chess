package game;

import pieces.Piece;

public class Move {

    private Piece captured;

    public Move(Piece capturedPiece) {
        captured = capturedPiece;
    }

    public Piece getCaptured() {
        return captured;
    }
}