/**
 * Represents King in Chess
 * @author Harshith Samayamantula (hs1018)
 * @author Oways Jaffer (omj9)
 * 
 */
package chess25.chess;

import java.util.*;

public class King extends ChessPiece {
    /**
     * Updates the validMoves variable of King by identifying which squares the
     * king can potentially move to
     * 
     * Looks for moves one space around position
     * 
     */
    public void validMoves() {

        this.validMoveList.clear();
        Position temp;
        if (king.inCheck()) {
            temp = validSquare(this.validMoveList, this.position.getfile(), this.position.getrank(), 1, 1);
            if (threats(temp).size() == 0 && temp != null)
                this.validMoveList.add(temp);
            temp = validSquare(this.validMoveList, this.position.getfile(), this.position.getrank(), -1, -1);
            if (threats(temp).size() == 0 && temp != null)
                this.validMoveList.add(temp);
            temp = validSquare(this.validMoveList, this.position.getfile(), this.position.getrank(), 1, -1);
            if (threats(temp).size() == 0 && temp != null)
                this.validMoveList.add(temp);
            temp = validSquare(this.validMoveList, this.position.getfile(), this.position.getrank(), -1, 1);
            if (threats(temp).size() == 0 && temp != null)
                this.validMoveList.add(temp);
            temp = validSquare(this.validMoveList, this.position.getfile(), this.position.getrank(), 1, 0);
            if (threats(temp).size() == 0 && temp != null)
                this.validMoveList.add(temp);
            temp = validSquare(this.validMoveList, this.position.getfile(), this.position.getrank(), 0, 1);
            if (threats(temp).size() == 0 && temp != null)
                this.validMoveList.add(temp);
            temp = validSquare(this.validMoveList, this.position.getfile(), this.position.getrank(), -1, 0);
            if (threats(temp).size() == 0 && temp != null)
                this.validMoveList.add(temp);
            temp = validSquare(this.validMoveList, this.position.getfile(), this.position.getrank(), 0, -1);
            if (threats(temp).size() == 0 && temp != null)
                this.validMoveList.add(temp);
        } else {
            movingPattern(this.validMoveList, this.position.getfile(), this.position.getrank(), 1, 1);
            movingPattern(this.validMoveList, this.position.getfile(), this.position.getrank(), -1, -1);
            movingPattern(this.validMoveList, this.position.getfile(), this.position.getrank(), 1, -1);
            movingPattern(this.validMoveList, this.position.getfile(), this.position.getrank(), -1, 1);
            movingPattern(this.validMoveList, this.position.getfile(), this.position.getrank(), 1, 0);
            movingPattern(this.validMoveList, this.position.getfile(), this.position.getrank(), 0, 1);
            movingPattern(this.validMoveList, this.position.getfile(), this.position.getrank(), -1, 0);
            movingPattern(this.validMoveList, this.position.getfile(), this.position.getrank(), 0, -1);
        }
    }

    /**
     * Checks to see if given location will be a validSquare based on locations of
     * opponent pieces.
     * 
     * @param moveList
     * @param fileVal
     * @param rankVal
     * @param fileDisplacement
     * @param rankDisplacement
     * @return Position
     */
    public Position validSquare(ArrayList<Position> moveList, int fileVal, int rankVal, int fileDisplacement,
            int rankDisplacement) {

        Position increment = new Position(fileVal + fileDisplacement, rankVal + rankDisplacement);

        if (chessLogic.inBounds(increment)) {
            if (chessLogic.isOccupied(increment)) {
                if (!chessLogic.isOccupied(increment, this.color)
                        && chessLogic.ChessBoard[increment.getrank()][increment.getfile()] instanceof King) {
                    if (!underAttack(increment))
                        return increment;
                }
            } else {
                if (!underAttack(increment))
                    return increment;
            }
        }
        return null;
    }

    /**
     * Overriding movingPattern array from ChessPiece
     * 
     * @param moveList
     * @param fileVal
     * @param rankVal
     * @param fileDisplacement
     * @param rankDisplacement
     */
    public void movingPattern(ArrayList<Position> moveList, int fileVal, int rankVal, int fileDisplacement,
            int rankDisplacement) {

        Position increment = new Position(fileVal + fileDisplacement, rankVal + rankDisplacement);

        if (chessLogic.inBounds(increment)) {
            if (chessLogic.isOccupied(increment)) {
                if (!chessLogic.isOccupied(increment, this.color)
                        && !(chessLogic.ChessBoard[increment.getrank()][increment.getfile()] instanceof King)) {
                    if (!underAttack(increment))
                        moveList.add(increment);
                }
            } else {
                if (!underAttack(increment))
                    moveList.add(increment);
            }
        }
    }

    /**
     * Checks to see if given test position will be underattack from any opponent
     * piece
     * 
     * @param test
     * @return boolean
     */
    public boolean underAttack(Position test) {
        for (ChessPiece[] rank : chessLogic.ChessBoard) {
            for (ChessPiece piece : rank) {
                if (piece == null)
                    continue;

                if (piece instanceof King && piece.color.equals(this.color)) {
                    continue;
                }
                if (!piece.color.equals(this.color) && piece.attackingSquaresList.contains(test))
                    return true;
            }
        }
        return false;
    }

    /**
     * Adds the squares around King to the attackingSquaresList variable
     */
    public void attackingSquares() {
        this.attackingSquaresList.clear();
        computeAttacking(this.attackingSquaresList, this.position.getfile(), this.position.getrank(), 1, 1);
        computeAttacking(this.attackingSquaresList, this.position.getfile(), this.position.getrank(), -1, -1);
        computeAttacking(this.attackingSquaresList, this.position.getfile(), this.position.getrank(), 1, -1);
        computeAttacking(this.attackingSquaresList, this.position.getfile(), this.position.getrank(), -1, 1);
        computeAttacking(this.attackingSquaresList, this.position.getfile(), this.position.getrank(), 1, 0);
        computeAttacking(this.attackingSquaresList, this.position.getfile(), this.position.getrank(), 0, 1);
        computeAttacking(this.attackingSquaresList, this.position.getfile(), this.position.getrank(), -1, 0);
        computeAttacking(this.attackingSquaresList, this.position.getfile(), this.position.getrank(), 0, -1);

    }

    /**
     * Checks to see if desired locations are in bounds.
     * 
     * @param attackList
     * @param fileVal
     * @param rankVal
     * @param fileDisplacement
     * @param rankDisplacement
     */
    public void computeAttacking(ArrayList<Position> attackList, int fileVal, int rankVal, int fileDisplacement,
            int rankDisplacement) {

        Position increment = new Position(fileVal + fileDisplacement, rankVal + rankDisplacement);

        if (chessLogic.inBounds(increment)) {
            attackList.add(increment);
        }
    }

    /**
     * Returns a list of ChessPiece objects of the opponent that have their line of
     * sights on the King.
     * 
     * @param testPosition
     * @return ArrayList<ChessPiece>
     */
    public ArrayList<ChessPiece> threats(Position testPosition) {
        ArrayList<ChessPiece> threatArray = new ArrayList<ChessPiece>();

        Position kingPosition = testPosition;

        for (ChessPiece[] rank : chessLogic.ChessBoard) {
            for (ChessPiece piece : rank) {
                if (piece == null)
                    continue;
                if (piece instanceof King && piece.color.equals(this.color)) {
                    continue;
                }
                if (!piece.color.equals(this.color) && piece.attackingSquaresList.contains(kingPosition))
                    threatArray.add(piece);
            }
        }
        return threatArray;
    }

    /**
     * Checks to see if the King is under check.
     * 
     * @return boolean
     */
    // unfinished
    public boolean inCheck() {
        Position kingPosition = this.position;

        for (ChessPiece[] rank : chessLogic.ChessBoard) {
            for (ChessPiece piece : rank) {
                if (piece == null)
                    continue;
                if (piece instanceof King && piece.color.equals(this.color)) {
                    continue;
                }
                if (!piece.color.equals(this.color) && piece.attackingSquaresList.contains(kingPosition))
                    return true;
            }
        }
        return false;
    }

    /**
     * Constructor for the King
     * 
     * @param file
     * @param rank
     * @param color
     */
    public King(int file, int rank, String color) {
        // this.file = file;
        // this.rank = rank;
        this.position = new Position(file, rank);
        this.color = color;
        this.validMoveList = new ArrayList<Position>();
        this.attackingSquaresList = new ArrayList<Position>();
        this.king = this;
        this.hasMoved = false;
    }

    /**
     * Constructor for the King
     * 
     * @param pos
     * @param color
     */
    public King(Position pos, String color) {
        this.position = pos;
        this.color = color;
        this.validMoveList = new ArrayList<Position>();
        this.attackingSquaresList = new ArrayList<Position>();
        this.hasMoved = false;
    }

    /**
     * Overrides the toString method.
     * 
     * @return String
     */
    public String toString() {
        return this.color.substring(0, 1).toLowerCase() + "K";
    }
}
