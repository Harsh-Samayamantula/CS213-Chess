/**
 * Represents Pawn in Chess
 * @author Harshith Samayamantula (hs1018)
 * @author Oways Jaffer (omj9)
 * 
 */
package chess25.chess;

import java.util.*;

public class Pawn extends ChessPiece {
    // public int PawnMoveCount = 0;
    public boolean promotion = false;

    /**
     * Updates the validMoves variable of pawn by identifying which squares the
     * pawn can potentially move to
     * 
     * Performs all pawn move logic
     */
    public void validMoves() {
        // reading current position
        // check if same color king is in check
        // Check if this piece can block one of the attacks made my the (ChessPiece
        // objects)threats in the king.java
        this.validMoveList.clear();
        Position tempPosition;
        String oppositeColor;
        if (this.color.equals(COLOR_WHITE))
            oppositeColor = COLOR_BLACK;
        else
            oppositeColor = COLOR_WHITE;

        if (this.color.equals(COLOR_WHITE)) {
            tempPosition = new Position(position.getfile(), position.getrank() + 2);
            if (position.getrank() == 1 && chessLogic.inBounds(tempPosition)
                    && !chessLogic.isOccupied(tempPosition)) {

                proposedMove(this.position, tempPosition, this.validMoveList, oppositeColor);
                // this.validMoveList.add(tempPosition);
            }
            tempPosition = new Position(position.getfile(), position.getrank() + 1);
            if (chessLogic.inBounds(tempPosition) && (!chessLogic.isOccupied(tempPosition))) {
                proposedMove(this.position, tempPosition, this.validMoveList, oppositeColor);
                // this.validMoveList.add(tempPosition);
            }
            // Check diagonals
            tempPosition = new Position(position.getfile() - 1, position.getrank() + 1);
            if (chessLogic.inBounds(tempPosition)
                    && (chessLogic.isOccupied(tempPosition, oppositeColor)
                            && (!(chessLogic.ChessBoard[position.getrank() + 1][position.getfile()
                                    - 1] instanceof King)))) {

                proposedMove(this.position, tempPosition, this.validMoveList, oppositeColor);
                // this.validMoveList.add(tempPosition);
            }
            tempPosition = new Position(position.getfile() + 1, position.getrank() + 1);
            if (chessLogic.inBounds(tempPosition)
                    && (chessLogic.isOccupied(tempPosition, oppositeColor)
                            && (!(chessLogic.ChessBoard[position.getrank() + 1][position.getfile()
                                    + 1] instanceof King)))) {
                proposedMove(this.position, tempPosition, this.validMoveList, oppositeColor);
                // this.validMoveList.add(tempPosition);
            }

        } else {
            tempPosition = new Position(position.getfile(), position.getrank() - 2);
            if (position.getrank() == 6 && chessLogic.inBounds(tempPosition)
                    && (!chessLogic.isOccupied(tempPosition))) {
                proposedMove(this.position, tempPosition, this.validMoveList, oppositeColor);
                // this.validMoveList.add(tempPosition);
            }
            tempPosition = new Position(position.getfile(), position.getrank() - 1);
            if (chessLogic.inBounds(tempPosition) && (!chessLogic.isOccupied(tempPosition))) {
                proposedMove(this.position, tempPosition, this.validMoveList, oppositeColor);
                // this.validMoveList.add(tempPosition);
            }
            // Check diagonals

            tempPosition = new Position(position.getfile() - 1, position.getrank() - 1);
            if (chessLogic.inBounds(tempPosition)
                    && (chessLogic.isOccupied(tempPosition, oppositeColor)
                            && (!(chessLogic.ChessBoard[position.getrank() - 1][position.getfile()
                                    - 1] instanceof King)))) {
                proposedMove(this.position, tempPosition, this.validMoveList, oppositeColor);
                // this.validMoveList.add(tempPosition);
            }
            tempPosition = new Position(position.getfile() + 1, position.getrank() - 1);
            if (chessLogic.inBounds(tempPosition)
                    && (chessLogic.isOccupied(tempPosition, oppositeColor)
                            && (!(chessLogic.ChessBoard[position.getrank() - 1][position.getfile()
                                    + 1] instanceof King)))) {
                proposedMove(this.position, tempPosition, this.validMoveList, oppositeColor);
                // this.validMoveList.add(tempPosition);
            }
        }

        // no edge cases looking for check
        // compute attacking
        // compute same color king's check function
    }

    /**
     * Checks to see if moving the pawn to the proposed location will result in the
     * king being in danger.
     * 
     * @param initial
     * @param proposed
     * @param validMoves
     * @param opponentColorString
     */
    public void proposedMove(Position initial, Position proposed,
            ArrayList<Position> validMoves,
            String opponentColorString) {
        int initialFile = initial.getfile();
        int initialRank = initial.getrank();

        if (initialFile == -10 || initialRank == -10)
            return;

        int proposedFile = proposed.getfile();
        int proposedRank = proposed.getrank();

        ChessPiece temp;
        temp = chessLogic.ChessBoard[proposedRank][proposedFile];
        if (temp != null)
            temp.setPosition(-10, -10);
        chessLogic.ChessBoard[initialRank][initialFile].setPosition(proposedFile,
                proposedRank);

        chessLogic.computeOpponentAttackList(opponentColorString);

        if (this.king.threats(this.king.position).size() == 0)
            validMoves.add(proposed);

        if (temp != null)
            temp.setPosition(proposedFile, proposedRank);
        chessLogic.ChessBoard[initialRank][initialFile].setPosition(initialFile,
                initialRank);
        chessLogic.computeOpponentAttackList(opponentColorString);
    }

    /**
     * Adds the diagonal Squares from the Pawn's location into the
     * attackingSquareList of the Pawn
     */
    public void attackingSquares() {

        this.attackingSquaresList.clear();
        Position attackingSquare1 = new Position(this.position.getfile() - 1,
                this.position.getrank() + (this.color.equals(COLOR_WHITE) ? 1 : -1));
        Position attackingSquare2 = new Position(this.position.getfile() + 1,
                this.position.getrank() + (this.color.equals(COLOR_WHITE) ? 1 : -1));
        if (chessLogic.inBounds(attackingSquare1)) {
            this.attackingSquaresList.add(attackingSquare1);
        }
        if (chessLogic.inBounds(attackingSquare2)) {
            this.attackingSquaresList.add(attackingSquare2);
        }
        // Add enpassant variation
    }

    /**
     * Constructor for Pawn
     * 
     * @param file
     * @param rank
     * @param color
     * @param king
     */
    public Pawn(int file, int rank, String color, King king) {
        // this.file = file;
        // this.rank = rank;
        this.position = new Position(file, rank);
        this.color = color;
        this.validMoveList = new ArrayList<Position>();
        this.attackingSquaresList = new ArrayList<Position>();
        this.king = king;

    }

    /**
     * Constructor for Pawn
     * 
     * @param pos
     * @param color
     */
    public Pawn(Position pos, String color) {
        this.position = pos;
        this.color = color;
        this.validMoveList = new ArrayList<Position>();
        this.attackingSquaresList = new ArrayList<Position>();

    }

    /**
     * Overrides toString
     * 
     * @return String
     */
    public String toString() {
        return this.color.substring(0, 1).toLowerCase() + "p";
    }
}
