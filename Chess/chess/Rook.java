/**
 * Represents Rook in Chess
 * @author Harshith Samayamantula
 */
package Chess.chess;

import java.util.*;

public class Rook extends ChessPiece {
    /**
     * Updates the validMoves variable of Rook by identifying which squares the
     * rook can potentially move to
     * 
     * Looks for moves diagonally
     */
    public void validMoves() {
        // reading current position
        this.validMoveList.clear();

        movingPattern(this.validMoveList, this.position.getfile(), this.position.getrank(), 1, 0);
        // Top left
        movingPattern(this.validMoveList, this.position.getfile(), this.position.getrank(), -1, 0);
        // Bottom right
        movingPattern(this.validMoveList, this.position.getfile(), this.position.getrank(), 0, -1);
        // Bottom left
        movingPattern(this.validMoveList, this.position.getfile(), this.position.getrank(), 0, 1);

    }

    /**
     * Updates the attackingSquares variables of the rook by looking at which
     * pieces it is protecting and reaching.
     */
    public void attackingSquares() {
        this.attackingSquaresList.clear();
        computeAttacking(this.attackingSquaresList, this.position.getfile(), this.position.getrank(), 1, 0);
        // Top left
        computeAttacking(this.attackingSquaresList, this.position.getfile(), this.position.getrank(), -1, 0);
        // Bottom right
        computeAttacking(this.attackingSquaresList, this.position.getfile(), this.position.getrank(), 0, -1);
        // Bottom left
        computeAttacking(this.attackingSquaresList, this.position.getfile(), this.position.getrank(), 0, 1);
    }

    /**
     * Traverses possible squares on the board to see if the rook can reach and
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
     * Constructor for Rook
     * 
     * @param file
     * @param rank
     * @param color
     * @param king
     */
    public Rook(int file, int rank, String color, King king) {
        // this.file = file;
        // this.rank = rank;
        this.position = new Position(file, rank);
        this.color = color;
        this.validMoveList = new ArrayList<Position>();
        this.attackingSquaresList = new ArrayList<Position>();
        this.king = king;
        this.hasMoved = false;

    }

    /**
     * Constructor for Rook
     * 
     * @param pos
     * @param color
     */
    public Rook(Position pos, String color) {
        this.position = pos;
        this.color = color;
        this.validMoveList = new ArrayList<Position>();
        this.attackingSquaresList = new ArrayList<Position>();
        this.hasMoved = false;
    }

    /**
     * Overrides toString
     * 
     * @return String
     */
    public String toString() {
        return this.color.substring(0, 1).toLowerCase() + "R";
    }
}
