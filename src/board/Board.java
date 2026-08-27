package board;

import game.VariablesStatic;
import pieces.*;

public class Board {

    private Spot[][] square;

    private Spot targetEnPass = null;

    public Board() {

        square = new Spot[8][8];

        newBoard();
    }

    public Spot getPassantTarget() {
        return targetEnPass;
    }

    public void setPassantTarget(Spot t) {
        targetEnPass = t;
    }

    public Spot[][] getSquare() {
        return square;
    }

    public Spot getSquare(int x, int y) {
        return square[x][y];
    }

    public Spot getValidation(
        int x,
        int y
    ) throws Exception {

        if (
            x < 0
            ||
            x > 7
            ||
            y < 0
            ||
            y > 7
        ) {
            throw new Exception(
                "Index out of bound"
            );
        }

        return square[x][y];
    }

    public void newBoard() {

        square[0][0] =
            new Spot(0, 0, new Rook(true, false));

        square[0][1] =
            new Spot(0, 1, new Knight(true, false));

        square[0][2] =
            new Spot(0, 2, new Bishop(true, false));

        square[0][3] =
            new Spot(0, 3, new Queen(true, false));

        square[0][4] =
            new Spot(0, 4, new King(true, false));

        square[0][5] =
            new Spot(0, 5, new Bishop(true, false));

        square[0][6] =
            new Spot(0, 6, new Knight(true, false));

        square[0][7] =
            new Spot(0, 7, new Rook(true, false));


        square[7][0] =
            new Spot(7, 0, new Rook(false, false));

        square[7][1] =
            new Spot(7, 1, new Knight(false, false));

        square[7][2] =
            new Spot(7, 2, new Bishop(false, false));

        square[7][3] =
            new Spot(7, 3, new Queen(false, false));

        square[7][4] =
            new Spot(7, 4, new King(false, false));

        square[7][5] =
            new Spot(7, 5, new Bishop(false, false));

        square[7][6] =
            new Spot(7, 6, new Knight(false, false));

        square[7][7] =
            new Spot(7, 7, new Rook(false, false));


        for (int a = 0; a < 8; a++) {

            square[1][a] =
                new Spot(
                    1,
                    a,
                    new Pawn(true, false)
                );

            square[6][a] =
                new Spot(
                    6,
                    a,
                    new Pawn(false, false)
                );
        }


        for (int i = 2; i < 6; i++) {

            for (int j = 0; j < 8; j++) {

                square[i][j] =
                    new Spot(i, j, null);
            }
        }
    }

    public boolean movePiece(
        int xstart,
        int ystart,
        int xend,
        int yend
    ) throws Exception {

        Spot start =
            getValidation(xstart, ystart);

        Spot end =
            getValidation(xend, yend);

        Piece pieceMoving =
            start.getPiece();

        if (pieceMoving == null) {

            System.out.println(
                "No piece exists at this spot."
            );

            return false;
        }

        if (
            !pieceMoving.movestatus(
                this,
                start,
                end
            )
        ) {

            System.out.println(
                "Illegal move. Outside parameter."
            );

            return false;
        }

        // Castling
        if (
            pieceMoving instanceof King
            &&
            Math.abs(ystart - yend) == 2
        ) {

            if (yend > ystart) {

                // Kingside
                Spot rookStart =
                    getSquare(xstart, 7);

                Spot rookEnd =
                    getSquare(xstart, 5);

                rookEnd.setPiece(
                    rookStart.getPiece()
                );

                rookStart.setPiece(null);

                rookEnd.getPiece()
                    .setmoved(true);

                if (pieceMoving.getwhite()) {

                    VariablesStatic.whiteKSiderook =
                        true;

                } else {

                    VariablesStatic.blackKSiderook =
                        true;
                }

            } else {

                // Queenside
                Spot rookStart =
                    getSquare(xstart, 0);

                Spot rookEnd =
                    getSquare(xstart, 3);

                rookEnd.setPiece(
                    rookStart.getPiece()
                );

                rookStart.setPiece(null);

                rookEnd.getPiece()
                    .setmoved(true);

                if (pieceMoving.getwhite()) {

                    VariablesStatic.whiteQSiderook =
                        true;

                } else {

                    VariablesStatic.blackQSiderook =
                        true;
                }
            }
        }

        // King movement tracking
        if (
            pieceMoving instanceof King
            &&
            !pieceMoving.getmoved()
        ) {

            if (pieceMoving.getwhite()) {

                VariablesStatic.whiteKingMoved =
                    true;

            } else {

                VariablesStatic.blackKingMoved =
                    true;
            }
        }

        // En passant
        if (
            pieceMoving instanceof Pawn
            &&
            end == this.getPassantTarget()
        ) {

            int capturedPawnX;

            if (pieceMoving.getwhite()) {
                capturedPawnX = xend - 1;
            } else {
                capturedPawnX = xend + 1;
            }

            Spot captureSpot =
                getSquare(
                    capturedPawnX,
                    yend
                );

            captureSpot.setPiece(null);
        }

        // Update en passant target
        if (pieceMoving instanceof Pawn) {

            if (
                Math.abs(
                    xend - xstart
                ) == 2
            ) {

                setPassantTarget(
                    getSquare(
                        (xstart + xend) / 2,
                        ystart
                    )
                );

            } else {

                setPassantTarget(null);
            }

        } else {

            setPassantTarget(null);
        }

        // Cannot capture own piece
        if (
            end.getPiece() != null
            &&
            end.getPiece().getwhite()
                == pieceMoving.getwhite()
        ) {

            System.out.println(
                "Own piece present. Cannot capture."
            );

            return false;
        }

        end.setPiece(pieceMoving);
        start.setPiece(null);

        pieceMoving.setmoved(true);

        return true;
    }

    public void printBoard() {

        for (int i = 7; i >= 0; i--) {

            System.out.print(i + " ");

            for (int j = 0; j < 8; j++) {

                Piece piece =
                    square[i][j].getPiece();

                if (piece == null) {

                    System.out.print("[  ]");

                } else if (piece instanceof Rook) {

                    if (piece.getwhite()) {
                        System.out.print("[🏯]");
                    } else {
                        System.out.print("[🏰]");
                    }

                } else if (piece instanceof Knight) {

                    if (piece.getwhite()) {
                        System.out.print("[🐴]");
                    } else {
                        System.out.print("[🗡️]");
                    }

                } else if (piece instanceof Bishop) {

                    if (piece.getwhite()) {
                        System.out.print("[📿]");
                    } else {
                        System.out.print("[✝️]");
                    }

                } else if (piece instanceof Queen) {

                    if (piece.getwhite()) {
                        System.out.print("[👸]");
                    } else {
                        System.out.print("[💍]");
                    }

                } else if (piece instanceof King) {

                    if (piece.getwhite()) {
                        System.out.print("[🤴🏻]");
                    } else {
                        System.out.print("[👑]");
                    }

                } else if (piece instanceof Pawn) {

                    if (piece.getwhite()) {
                        System.out.print("[👧]");
                    } else {
                        System.out.print("[🧑]");
                    }
                }
            }

            System.out.println();
        }

        System.out.println(
            "   0  1  2  3  4  5  6  7"
        );
    }
}