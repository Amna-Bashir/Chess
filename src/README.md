# Chess

A Java-based command-line chess application.

## Features

- User registration and login
- One-time security codes
- Player statistics
- Leaderboard
- Two-player chess
- Legal piece movement
- Capturing pieces
- Castling
- En passant
- Pawn promotion
- Check detection
- Checkmate detection
- Stalemate detection
- Move history
- Captured piece tracking

## Project Structure

```text
src/
├── Chess.java
│
├── account/
│   ├── MembershipSystem.java
│   ├── AccountStats.java
│   ├── Leaderboard.java
│   └── FileManager.java
│
├── board/
│   ├── Board.java
│   └── Spot.java
│
├── pieces/
│   ├── Piece.java
│   ├── King.java
│   ├── Queen.java
│   ├── Rook.java
│   ├── Bishop.java
│   ├── Knight.java
│   └── Pawn.java
│
├── game/
│   ├── Game.java
│   ├── Move.java
│   ├── Check.java
│   ├── Checkmate.java
│   ├── Stalemate.java
│   ├── FindKing.java
│   └── VariablesStatic.java
│
└── ui/
    └── Rulebook.java