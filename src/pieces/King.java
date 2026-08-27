package pieces;

import board.Board;
import board.Spot;
import game.Check;
import game.VariablesStatic;

public class King extends Piece {

    public King(
        boolean iswhite,
        boolean pawnhasmoved
    ) {
        super(iswhite, pawnhasmoved);
    }

    @Override
    public boolean movestatus(
        Board board,
        Spot starting,
        Spot ending
    ) {

        Check check = new Check();

        int row = 0;

        // Normal one-square king movement
        if (
            Math.abs(starting.getX() - ending.getX()) <= 1
            &&
            Math.abs(starting.getY() - ending.getY()) <= 1
        ) {

            if (
                ending.getPiece() != null
                &&
                ending.getPiece().getwhite() == getwhite()
            ) {
                return false;
            }

            return true;
        }

        // Castling
        if (
            Math.abs(starting.getY() - ending.getY()) == 2
            &&
            Math.abs(starting.getX() - ending.getX()) == 0
        ) {

            // Cannot castle while in check
            if (check.checkStatus(board, getwhite())) {
                return false;
            }

            if (getwhite()) {
                row = 0;
            } else {
                row = 7;
            }

            // Kingside castling
            if (
                starting.getY() == 4
                &&
                ending.getY() == 6
            ) {

                if (
                    (
                        getwhite()
                        &&
                        !VariablesStatic.whiteKingMoved
                        &&
                        !VariablesStatic.whiteKSiderook
                    )
                    ||
                    (
                        !getwhite()
                        &&
                        !VariablesStatic.blackKingMoved
                        &&
                        !VariablesStatic.blackKSiderook
                    )
                ) {

                    if (
                        board.getSquare()[row][5].getPiece() == null
                        &&
                        board.getSquare()[row][6].getPiece() == null
                    ) {

                        Piece rook =
                            board.getSquare()[row][7].getPiece();

                        if (
                            rook == null
                            ||
                            !(rook instanceof Rook)
                            ||
                            rook.getwhite() != getwhite()
                        ) {
                            return false;
                        }

                        if (
                            check.checkThreat(
                                board,
                                row,
                                5,
                                !getwhite()
                            )
                            ||
                            check.checkThreat(
                                board,
                                row,
                                6,
                                !getwhite()
                            )
                        ) {
                            return false;
                        }

                        return true;
                    }
                }
            }

            // Queenside castling
            if (
                starting.getY() == 4
                &&
                ending.getY() == 2
            ) {

                if (
                    (
                        getwhite()
                        &&
                        !VariablesStatic.whiteKingMoved
                        &&
                        !VariablesStatic.whiteQSiderook
                    )
                    ||
                    (
                        !getwhite()
                        &&
                        !VariablesStatic.blackKingMoved
                        &&
                        !VariablesStatic.blackQSiderook
                    )
                ) {

                    if (
                        board.getSquare()[row][1].getPiece() == null
                        &&
                        board.getSquare()[row][2].getPiece() == null
                        &&
                        board.getSquare()[row][3].getPiece() == null
                    ) {

                        Piece rook =
                            board.getSquare()[row][0].getPiece();

                        if (
                            rook == null
                            ||
                            !(rook instanceof Rook)
                            ||
                            rook.getwhite() != getwhite()
                        ) {
                            return false;
                        }

                        if (
                            check.checkThreat(
                                board,
                                row,
                                2,
                                !getwhite()
                            )
                            ||
                            check.checkThreat(
                                board,
                                row,
                                3,
                                !getwhite()
                            )
                        ) {
                            return false;
                        }

                        return true;
                    }
                }
            }
        }

        return false;
    }
}