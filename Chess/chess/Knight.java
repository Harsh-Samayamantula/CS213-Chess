/**
 * Represents Knight in Chess
 * @author Harshith Samayamantula (hs1018)
 * @author Oways Jaffer (omj9)
 * 
 */
package chess25.chess;

import java.util.*;

public class Knight extends ChessPiece {

    /**
     * Updates the validMoves variable of Knight by identifying which squares the
     * knight can potentially move to
     * 
     * Looks for moves in the L pattern around position
     */
    public void validMoves() {
        // reading current position
        this.validMoveList.clear();
        movingPattern(this.validMoveList, this.position.getfile(), this.position.getrank(), 2, 1);

        movingPattern(this.validMoveList, this.position.getfile(), this.position.getrank(), 1, 2);

        movingPattern(this.validMoveList, this.position.getfile(), this.position.getrank(), -1, 2);

        movingPattern(this.validMoveList, this.position.getfile(), this.position.getrank(), -2, 1);

        movingPattern(this.validMoveList, this.position.getfile(), this.position.getrank(), 2, -1);

        movingPattern(this.validMoveList, this.position.getfile(), this.position.getrank(), 1, -2);

        movingPattern(this.validMoveList, this.position.getfile(), this.position.getrank(), -1, -2);

        movingPattern(this.validMoveList, this.position.getfile(), this.position.getrank(), -2, -1);
    }

    /**
     * Overrides ChessPiece movingPattern to only perform logic once.
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

        String oppositeColor;
        if (this.color.equals(COLOR_WHITE))
            oppositeColor = COLOR_BLACK;
        else
            oppositeColor = COLOR_WHITE;

        if (chessLogic.inBounds(increment)) {
            if (chessLogic.isOccupied(increment)) {
                if (!chessLogic.isOccupied(increment, this.color)
                        && !(chessLogic.ChessBoard[increment.getrank()][increment.getfile()] instanceof King)) {
                    // this.validMoveList.add(increment);
                    proposedMove(new Position(fileVal, rankVal), increment, moveList, oppositeColor);
                }
            } else {
                // this.validMoveList.add(increment);
                proposedMove(new Position(fileVal, rankVal), increment, moveList, oppositeColor);
            }
        }
    }

    /**
     * Checks squares in L pattern around the current position to add to
     * attackingSquaresList
     */
    public void attackingSquares() {
        this.attackingSquaresList.clear();
        Position attack = new Position(this.position.getfile() + 2, this.position.getrank() + 1);
        if (chessLogic.inBounds(attack)) {
            attackingSquaresList.add(attack);
        }
        attack = new Position(this.position.getfile() + 2, this.position.getrank() - 1);
        if (chessLogic.inBounds(attack)) {
            attackingSquaresList.add(attack);
        }
        attack = new Position(this.position.getfile() + 1, this.position.getrank() - 2);
        if (chessLogic.inBounds(attack)) {
            attackingSquaresList.add(attack);
        }
        attack = new Position(this.position.getfile() + 1, this.position.getrank() + 2);
        if (chessLogic.inBounds(attack)) {
            attackingSquaresList.add(attack);
        }
        attack = new Position(this.position.getfile() - 2, this.position.getrank() - 1);
        if (chessLogic.inBounds(attack)) {
            attackingSquaresList.add(attack);
        }
        attack = new Position(this.position.getfile() - 2, this.position.getrank() + 1);
        if (chessLogic.inBounds(attack)) {
            attackingSquaresList.add(attack);
        }
        attack = new Position(this.position.getfile() - 1, this.position.getrank() + 2);
        if (chessLogic.inBounds(attack)) {
            attackingSquaresList.add(attack);
        }
        attack = new Position(this.position.getfile() - 1, this.position.getrank() - 2);
        if (chessLogic.inBounds(attack)) {
            attackingSquaresList.add(attack);
        }

    }

    /**
     * Constructor for Knight
     * 
     * @param file
     * @param rank
     * @param color
     * @param king
     */
    public Knight(int file, int rank, String color, King king) {
        // this.file = file;
        // this.rank = rank;
        this.position = new Position(file, rank);
        this.color = color;
        this.validMoveList = new ArrayList<Position>();
        this.attackingSquaresList = new ArrayList<Position>();
        this.king = king;

    }

    /**
     * Constructor for Knight
     * 
     * @param pos
     * @param color
     */
    public Knight(Position pos, String color) {
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
        return this.color.substring(0, 1).toLowerCase() + "N";
    }
}
