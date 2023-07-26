/**
 * Position class to keep track of locations for ChessPieces in a game.
 *
 * @author Harshith Samayamantula (hs1018)
 * @author Oways Jaffer (omj9)
 * 
 */
package chess25.chess;

public class Position {
    public int file;
    public int rank;

    /**
     * Position constructor by taking in filenumber and rank number
     * 
     * @param file
     * @param rank
     */
    public Position(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    /**
     * Position constructor by taking in coordinates array which holds a file and
     * rank
     * 
     * @param coordinates
     */
    public Position(int[] coordinates) {
        this.file = coordinates[0];
        this.rank = coordinates[1];
    }

    /**
     * Position constructor by taking in a String.
     * 
     * @param positionInputString
     */
    public Position(String positionInputString) {
        int[] coor = chessLogic.readMove(positionInputString);
        this.file = coor[0];
        this.rank = coor[1];
    }

    /**
     * Returns Position coordinates in int array form
     * 
     * @return int[]
     */
    public int[] getCoordinates() {
        int[] coordinates = { file, rank };
        return coordinates;
    }

    /**
     * Returns file
     * 
     * @return int
     */
    public int getfile() {
        int file = this.file;
        return file;
    }

    /**
     * Returns rank
     * 
     * @return int
     */
    public int getrank() {
        int rank = this.rank;
        return rank;
    }

    /**
     * Sets rank
     * 
     * @param rank
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * Sets file
     * 
     * @param file
     */
    public void setFile(int file) {
        this.file = file;
    }

    /**
     * Overrides toString
     * 
     * @return String
     */
    public String toString() {
        return chessLogic.letters[file] + String.valueOf(rank + 1);
    }

    /**
     * Overrides equals method
     * 
     * @param x
     * @return boolean
     */
    public boolean equals(Object x) {
        if (x == this) {
            return true;
        }
        if (!(x instanceof Position)) {
            return false;
        }
        Position c = (Position) x;
        return this.getfile() == c.getfile() && this.getrank() == c.getrank();
    }
}
