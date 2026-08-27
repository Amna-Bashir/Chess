package pieces;

import board.Board;
import board.Spot;

public abstract class Piece {

    private boolean whitepiece = false;
    private boolean hasmoved = false;

    public Piece(
        boolean white,
        boolean has_moved
    ) {
        whitepiece = white;
        hasmoved = has_moved;
    }

    public boolean getmoved() {
        return hasmoved;
    }

    public void setmoved(boolean moved) {
        hasmoved = moved;
    }

    public void setwhite(boolean iswhite) {
        whitepiece = iswhite;
    }

    public boolean getwhite() {
        return whitepiece;
    }

    public abstract boolean movestatus(
        Board board,
        Spot starting,
        Spot ending
    );
}