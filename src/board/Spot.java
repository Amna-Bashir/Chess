package board;

import pieces.Piece;

public class Spot {

    private Piece piece;
    private int x;
    private int y;

    public Spot(int sentX, int sentY, Piece sentPiece) {
        x = sentX;
        y = sentY;
        piece = sentPiece;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }
}