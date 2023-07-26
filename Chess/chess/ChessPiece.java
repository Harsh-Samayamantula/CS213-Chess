/**
 * This is an abstract class for all the types of pieces on a Chess board.
 * 
  * @author Harshith Samayamantula
 */

package Chess.chess;

import java.util.*;

public abstract class ChessPiece {

    /**
     * Each child of ChessPiece must implement validMoves() to have a list of all
     * possible valid moves stored in the validMoveList variable.
     */
    public abstract void validMoves();

    /**
     * Each child of ChessPiece must implement attackingSquares() to have a list of
     * all
     * possible valid moves stored in the attackingSquaresList variable.
     */
    public abstract void attackingSquares();

    static final String COLOR_WHITE = "White";
    static final String COLOR_BLACK = "Black";
    public ArrayList<Position> validMoveList;
    public ArrayList<Position> attackingSquaresList;
    public boolean hasMoved = true;
    public String color;
    public King king;
    // public String position;
    // public int file;
    // public int rank;
    public Position position;

    /**
     * Sets position variable with file and rank inputs.
     * 
     * @param file
     * @param rank
     */
    public void setPosition(int file, int rank) {
        position.setFile(file);
        position.setRank(rank);
    }

    /**
     * Overrides Object toString method.
     * 
     * @return String
     */
    public String toString() {
        return "  ";
    }

    /**
     * Returns color variable of ChessPiece
     * 
     * @return String
     */
    public String getColor() {
        return color;
    }

    /**
     * Increments initial position with fileDisplacement and rankDisplacement and
     * Checks if a new position is in bounds, or occupied by a piece of the same
     * color, then calls proposedMove(...) to run more checks based on the King.
     * 
     * @param moveList
     * @param initialFileVal
     * @param initialRankVal
     * @param fileDisplacement
     * @param rankDisplacement
     */
    public void movingPattern(ArrayList<Position> moveList, int initialFileVal, int initialRankVal,
            int fileDisplacement, int rankDisplacement) {
        Position increment;
        int fileVal = initialFileVal;
        int rankVal = initialRankVal;

        String oppositeColor;
        if (this.color.equals(COLOR_WHITE))
            oppositeColor = COLOR_BLACK;
        else
            oppositeColor = COLOR_WHITE;

        while (true) {
            // System.out.println("loop4");

            increment = new Position(fileVal + fileDisplacement, rankVal + rankDisplacement);

            if (chessLogic.inBounds(increment)) {
                // System.out.print("Testing this value:");
                // System.out.println(increment);
                if (chessLogic.isOccupied(increment)) {
                    if (chessLogic.isOccupied(increment, color)) {
                        // Add protected
                        break;
                    } else if (chessLogic.ChessBoard[increment.getrank()][increment.getfile()] instanceof King) {
                        break;
                    } else {
                        // moveList.add(increment);
                        proposedMove(new Position(initialFileVal, initialRankVal), increment, moveList, oppositeColor);

                        // System.out.println("true " + this.validMoveList.size());
                        break;
                    }
                } else {
                    // moveList.add(increment);
                    proposedMove(new Position(initialFileVal, initialRankVal), increment, moveList, oppositeColor);

                    // System.out.println("true " + this.validMoveList.size());
                }
            } else {
                break;
            }
            fileVal += fileDisplacement;
            rankVal += rankDisplacement;
        }
    }

    /**
     * Checks to see if a proposed move will result in the king being in trouble. If
     * the king is safe after the proposed move, it is then added to validMoves.
     * 
     * 
     * @param initial
     * @param proposed
     * @param validMoves
     * @param opponentColorString
     */
    public void proposedMove(Position initial, Position proposed, ArrayList<Position> validMoves,
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

        chessLogic.ChessBoard[initialRank][initialFile].setPosition(proposedFile, proposedRank);
        // when its checking for attacklist its looking at location of the chessboard
        // And when we set the proposed position that location is still empty and thus
        // considered invalid as the threat can still get to the king

        chessLogic.computeOpponentAttackList(opponentColorString);

        if (this.king.threats(this.king.position).size() == 0)
            validMoves.add(proposed);

        if (temp != null)
            temp.setPosition(proposedFile, proposedRank);
        chessLogic.ChessBoard[initialRank][initialFile].setPosition(initialFile, initialRank);
        chessLogic.computeOpponentAttackList(opponentColorString);
    }

}
