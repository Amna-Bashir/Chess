package game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import board.Board;
import board.Spot;
import pieces.*;

public class Game {

    public ArrayList<Move> capturedPieces =
        new ArrayList<>();

    public Board board;

    public boolean whiteTurn;

    public String playersTurn;

    Scanner scanner =
        new Scanner(System.in);

    LinkedList<String> moveHistory =
        new LinkedList<>();

    public Game() {

        board = new Board();

        capturedPieces =
            new ArrayList<>();

        whiteTurn = true;
    }

    public void displayCapturedPieces() {

        for (
            int i = 0;
            i < capturedPieces.size();
            i++
        ) {

            Piece p =
                capturedPieces
                    .get(i)
                    .getCaptured();

            if (p instanceof Rook) {

                if (p.getwhite()) {
                    System.out.print("🏯");
                } else {
                    System.out.print("🏰");
                }

            } else if (p instanceof Knight) {

                if (p.getwhite()) {
                    System.out.print("🐴");
                } else {
                    System.out.print("🗡️");
                }

            } else if (p instanceof Bishop) {

                if (p.getwhite()) {
                    System.out.print("📿");
                } else {
                    System.out.print("✝️");
                }

            } else if (p instanceof Queen) {

                if (p.getwhite()) {
                    System.out.print("👸");
                } else {
                    System.out.print("💍");
                }

            } else if (p instanceof King) {

                if (p.getwhite()) {
                    System.out.print("🤴🏻");
                } else {
                    System.out.print("👑");
                }

            } else if (p instanceof Pawn) {

                if (p.getwhite()) {
                    System.out.print("👧");
                } else {
                    System.out.print("🧑");
                }
            }
        }

        System.out.println();
    }

    public boolean gameplay(
        int xS,
        int yS,
        int xE,
        int yE
    ) throws Exception {

        Spot starting =
            board.getSquare()[xS][yS];

        Spot ending =
            board.getSquare()[xE][yE];

        Piece movingPiece =
            starting.getPiece();

        if (whiteTurn) {
            playersTurn = "White";
        } else {
            playersTurn = "Black";
        }

        if (movingPiece == null) {

            System.out.println(
                "No piece at starting position."
            );

            return false;
        }

        if (
            movingPiece.getwhite()
                != whiteTurn
        ) {

            System.out.println(
                "INVALID. Cannot move opponents piece."
                + "\nIt is "
                + playersTurn
                + "'s turn."
            );

            return false;
        }

        if (
            !movingPiece.movestatus(
                board,
                starting,
                ending
            )
        ) {

            System.out.println(
                "Illegal move for this piece."
            );

            return false;
        }

        Piece captured =
            ending.getPiece();

        boolean wasMoved =
            movingPiece.getmoved();

        // Temporarily make move
        ending.setPiece(movingPiece);
        starting.setPiece(null);

        movingPiece.setmoved(true);

        Check check =
            new Check();

        // Make sure move doesn't leave king in check
        if (
            check.checkStatus(
                board,
                whiteTurn
            )
        ) {

            starting.setPiece(movingPiece);
            ending.setPiece(captured);

            movingPiece.setmoved(wasMoved);

            System.out.println(
                "Illegal move for this piece."
            );

            return false;
        }

        // Undo temporary move
        starting.setPiece(movingPiece);
        ending.setPiece(captured);

        movingPiece.setmoved(wasMoved);

        // Make actual move
        if (
            !board.movePiece(
                xS,
                yS,
                xE,
                yE
            )
        ) {
            return false;
        }

        String moveRecord =
            playersTurn
            + " "
            + movingPiece
                .getClass()
                .getSimpleName()
            + " from ("
            + xS
            + ", "
            + yS
            + ") to ("
            + xE
            + ", "
            + yE
            + ")";

        if (captured != null) {

            capturedPieces.add(
                new Move(captured)
            );

            moveRecord +=
                " capturing "
                + captured
                    .getClass()
                    .getSimpleName();
        }

        moveHistory.add(moveRecord);

        // Pawn promotion
        if (
            movingPiece instanceof Pawn
            &&
            ((Pawn) movingPiece)
                .promote(xE)
        ) {

            System.out.print(
                "PAWN PROMOTED. PICK EITHER "
                + "(Q, R, B, K) AS YOUR PIECE OF CHOICE: "
            );

            String choice =
                scanner.nextLine()
                    .toUpperCase();

            Piece newPiece;

            if (choice.equals("Q")) {

                newPiece =
                    new Queen(
                        whiteTurn,
                        true
                    );

                System.out.println(
                    "Promoted to Queen."
                );

            } else if (choice.equals("R")) {

                newPiece =
                    new Rook(
                        whiteTurn,
                        true
                    );

                System.out.println(
                    "Promoted to Rook."
                );

            } else if (choice.equals("B")) {

                newPiece =
                    new Bishop(
                        whiteTurn,
                        true
                    );

                System.out.println(
                    "Promoted to Bishop."
                );

            } else if (choice.equals("K")) {

                newPiece =
                    new Knight(
                        whiteTurn,
                        true
                    );

                System.out.println(
                    "Promoted to Knight."
                );

            } else {

                System.out.println(
                    "Invalid choice. Default to Queen."
                );

                newPiece =
                    new Queen(
                        whiteTurn,
                        true
                    );
            }

            ending.setPiece(newPiece);
        }

        displayCapturedPieces();

        return true;
    }

    public void printMoveHistory() {

        System.out.println(
            "\nMove History"
        );

        if (moveHistory.isEmpty()) {

            System.out.println(
                "No moves made yet"
            );

        } else {

            for (
                int i = 0;
                i < moveHistory.size();
                i++
            ) {

                System.out.println(
                    (i + 1)
                    + ". "
                    + moveHistory.get(i)
                );
            }
        }
    }
}