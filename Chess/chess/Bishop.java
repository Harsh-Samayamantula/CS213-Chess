/**
 * Represents the Bishop piece in Chess
 * 
 * @author Harshith Samayamantula (hs1018)
 * @author Oways Jaffer (omj9)
 * 
 */
package chess25.chess;

import java.util.*;

public class Bishop extends ChessPiece {

    /**
     * Updates the validMoves variable of Bishop by identifying which squares the
     * bishop can potentially move to
     * 
     * Looks for moves diagonally
     * 
     */
    public void validMoves() {
        this.validMoveList.clear();

        // Top right
        movingPattern(this.validMoveList, this.position.getfile(), this.position.getrank(), 1, 1);
        // Top left
        movingPattern(this.validMoveList, this.position.getfile(), this.position.getrank(), -1, 1);
        // Bottom right
        movingPattern(this.validMoveList, this.position.getfile(), this.position.getrank(), 1, -1);
        // Bottom left
        movingPattern(this.validMoveList, this.position.getfile(), this.position.getrank(), -1, -1);
    }

    /**
     * Updates the attackingSquares variables of the Bishop by looking at which
     * pieces it is protecting and reaching.
     */
    public void attackingSquares() {
        this.attackingSquaresList.clear();
        // this.attackingSquaresList = new ArrayList<Position>();
        computeAttacking(this.attackingSquaresList, this.position.getfile(), this.position.getrank(), 1, 1);
        // Top left
        computeAttacking(this.attackingSquaresList, this.position.getfile(), this.position.getrank(), -1, 1);
        // Bottom right
        computeAttacking(this.attackingSquaresList, this.position.getfile(), this.position.getrank(), 1, -1);
        // Bottom left
        computeAttacking(this.attackingSquaresList, this.position.getfile(), this.position.getrank(), -1, -1);
    }

    /**
     * Traverses possible squares on the board to see if the Bishop can reach and
     * protect/attack square
     * 
     * @param attackList
     * @param initialFile
     * @param initialRank
     * @param fileDisplacement
     * @param rankDisplacement
     */
    public void computeAttacking(ArrayList<Position> attackList, int initialFile, int initialRank,
            int fileDisplacement, int rankDisplacement) {
        Position increment;
        int fileVal = initialFile;
        int rankVal = initialRank;
        while (true) {
            increment = new Position(fileVal + fileDisplacement, rankVal + rankDisplacement);

            if (chessLogic.inBounds(increment)) {
                if (chessLogic.isOccupied(increment)) {
                    attackList.add(increment);
                    break;
                } else if (chessLogic.proposedOccupied(increment)) {
                    attackList.add(increment);
                    break;
                } else {
                    attackList.add(increment);
                }
            } else {
                break;
            }
            fileVal += fileDisplacement;
            rankVal += rankDisplacement;
        }
    }

    /**
     * Bishop constructor with file, rank, color, and variable to keep track of King
     * 
     * @param file
     * @param rank
     * @param color
     * @param king
     */
    public Bishop(int file, int rank, String color, King king) {
        // this.file = file;
        // this.rank = rank;
        this.position = new Position(file, rank);
        this.color = color;
        this.validMoveList = new ArrayList<Position>();
        this.attackingSquaresList = new ArrayList<Position>();
        this.king = king;

    }

    /**
     * Bishop constructor with a Position object and the color.
     * 
     * @param pos
     * @param color
     */
    public Bishop(Position pos, String color) {
        this.position = pos;
        this.color = color;
        this.validMoveList = new ArrayList<Position>();
        this.attackingSquaresList = new ArrayList<Position>();

    }

    /**
     * Overriding object toString
     * 
     * @return String
     */
    public String toString() {
        return this.color.substring(0, 1).toLowerCase() + "B";
    }
}
