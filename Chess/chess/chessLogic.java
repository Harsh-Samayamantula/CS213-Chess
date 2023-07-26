/**
 * Implements chess board and the pieces on the board
 * 
 * @author Harshith Samayamantula
 */
package Chess.chess;

public class chessLogic {
    public static ChessPiece[][] ChessBoard;
    public static String[] letters = { "a", "b", "c", "d", "e", "f", "g", "h" };

    /**
     * Constructor that initializes the Chesspieces on the static variable
     * ChessBoard.
     */
    public chessLogic() {
        ChessBoard = new ChessPiece[8][8];
        // Initialization of board with pieces

        // The board is set up in the manner such that a1 corresponds to 00 and h8
        // corresponds to 77
        // Rank = rows on the chess board (1 through 8)
        // File = columns on the chess board (a through h)
        ChessBoard[0][4] = new King(4, 0, ChessPiece.COLOR_WHITE);
        ChessBoard[0][0] = new Rook(0, 0, ChessPiece.COLOR_WHITE, (King) ChessBoard[0][4]);
        ChessBoard[0][1] = new Knight(1, 0, ChessPiece.COLOR_WHITE, (King) ChessBoard[0][4]);
        ChessBoard[0][2] = new Bishop(2, 0, ChessPiece.COLOR_WHITE, (King) ChessBoard[0][4]);
        ChessBoard[0][3] = new Queen(3, 0, ChessPiece.COLOR_WHITE, (King) ChessBoard[0][4]);

        // testing
        // ChessBoard[4][4] = new Queen(3, 0, ChessPiece.COLOR_WHITE, (King)
        // ChessBoard[0][4]);

        ChessBoard[0][5] = new Bishop(5, 0, ChessPiece.COLOR_WHITE, (King) ChessBoard[0][4]);
        ChessBoard[0][6] = new Knight(6, 0, ChessPiece.COLOR_WHITE, (King) ChessBoard[0][4]);
        ChessBoard[0][7] = new Rook(7, 0, ChessPiece.COLOR_WHITE, (King) ChessBoard[0][4]);

        for (int i = 0; i < 8; i++) {
            ChessBoard[1][i] = new Pawn(i, 1, ChessPiece.COLOR_WHITE, (King) ChessBoard[0][4]);
        }

        ChessBoard[7][4] = new King(4, 7, ChessPiece.COLOR_BLACK);

        for (int i = 0; i < 8; i++) {
            ChessBoard[6][i] = new Pawn(i, 6, ChessPiece.COLOR_BLACK, (King) ChessBoard[7][4]);
        }

        // testing
        // ChessBoard[6][4] = null;

        ChessBoard[7][0] = new Rook(0, 7, ChessPiece.COLOR_BLACK, (King) ChessBoard[7][4]);
        ChessBoard[7][1] = new Knight(1, 7, ChessPiece.COLOR_BLACK, (King) ChessBoard[7][4]);
        ChessBoard[7][2] = new Bishop(2, 7, ChessPiece.COLOR_BLACK, (King) ChessBoard[7][4]);
        ChessBoard[7][3] = new Queen(3, 7, ChessPiece.COLOR_BLACK, (King) ChessBoard[7][4]);

        ChessBoard[7][5] = new Bishop(5, 7, ChessPiece.COLOR_BLACK, (King) ChessBoard[7][4]);
        ChessBoard[7][6] = new Knight(6, 7, ChessPiece.COLOR_BLACK, (King) ChessBoard[7][4]);
        ChessBoard[7][7] = new Rook(7, 7, ChessPiece.COLOR_BLACK, (King) ChessBoard[7][4]);

    }

    /**
     * Iterates through the static ChessBoard array to print out a representation of
     * the board.
     */
    public void printBoard() {
        System.out.println();
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                if (ChessBoard[i][j] == null) {
                    if ((i + j) % 2 == 0) {
                        System.out.print("## ");
                    } else {
                        System.out.print("   ");
                    }
                } else {
                    System.out.print(ChessBoard[i][j] + " ");
                }
            }
            System.out.println(i + 1);
        }
        System.out.print(" ");
        for (int k = 0; k < 8; k++) {
            System.out.print(letters[k] + "  ");
        }
        System.out.println("\n");
    }

    /**
     * Iterates through the ChessBoard and finds the King piece corresponding to the
     * given color.
     * 
     * @param color
     * @return King
     */
    public King getKing(String color) {
        for (ChessPiece[] ranks : ChessBoard) {
            for (ChessPiece piece : ranks) {
                if (piece != null && piece instanceof King && piece.color.equals(color))
                    return (King) piece;
                else if (piece != null && piece.color.equals(color)) {
                    return piece.king;
                }
            }
        }
        return null;
    }

    /**
     * Helper method to return the ChessPiece at a given position on the ChessBoard
     * array.
     * 
     * @param pos
     * @return ChessPiece
     */
    public ChessPiece getPiece(Position pos) {
        return ChessBoard[pos.rank][pos.file];
    }

    /**
     * Runs initial checks for moves to make sure they're in bounds and there is no
     * piece occupying the end.
     * 
     * @param playerColor
     * @param moveInput
     * @return boolean
     */
    public boolean isValidMove(String playerColor, String moveInput) {
        String[] move = moveInput.split(" ");
        Position start = new Position(readMove(move[0]));
        Position end = new Position(readMove(move[1]));
        if (!inBounds(start) || !inBounds(end))
            return false;
        // No piece at initial location
        if (getPiece(start) == null)
            return false;
        // There is a piece of the same color in the location you wish to move the piece
        // to.
        if (getPiece(end) != null && getPiece(end).getColor().equals(playerColor))
            return false;
        //
        return true;
        // we should check if the proposed move blocks check. player cant make a random
        // move while in check
    }

    /**
     * Returns the int arr of size 2 returning the numerical corresponding rank and
     * file to access on the ChessBoard array.
     * 
     * @param positionInput
     * @return int[]
     */
    public static int[] readMove(String positionInput) {
        int[] coordinates = new int[2];
        coordinates[0] = positionInput.charAt(0) - 97; // File
        coordinates[1] = Integer.valueOf(positionInput.substring(1)) - 1;
        // Rank

        return coordinates;
    }

    /**
     * Returns if a given coordinate array is in bounds on the board.
     * 
     * @param coordinates
     * @return boolean
     */
    public static boolean inBounds(int[] coordinates) {
        if (coordinates[0] < 0 || coordinates[0] > 7 || coordinates[1] < 0 || coordinates[1] > 7) {
            return false;
        }
        return true;
    }

    /**
     * Returns if a given Position object is in bounds on the board.
     * 
     * @param x
     * @return boolean
     */
    public static boolean inBounds(Position x) {
        return inBounds(x.getCoordinates());
    }

    /**
     * Method takes in the user move input and runs checks for validity and moves
     * the pieces on the ChessBoard array.
     * 
     * Returns 0 if successful
     * Returns 1 if unsuccessful
     * Returns 2 if checkmate
     * 
     * @param playerColor
     * @param moveInput
     * @param drawFlag
     * @return int
     */
    // return code values to edit loop in driver
    public int makeMove(String playerColor, String moveInput, boolean drawFlag) {
        // if (isValidMove(playerColor, moveInput)) {
        String oppositeColor;
        String promotionPiece = "Q";
        // boolean pawnPromotionMove = false;
        if (playerColor.equals(ChessPiece.COLOR_WHITE))
            oppositeColor = ChessPiece.COLOR_BLACK;
        else
            oppositeColor = ChessPiece.COLOR_WHITE;

        String[] move = moveInput.split(" ");
        if (move[0].length() > 2 || move[1].length() > 2) {
            System.out.println("Illegal move, try again");
            return 1;
        }

        if (move.length == 3 && move[2].equals("draw?")) {
            drawFlag = true;
        } else if (move.length == 3) {
            promotionPiece = move[2];
        }

        // for (String s : move) {
        // if (s.length() > 2) {
        // System.out.println("Illegal move, try again");
        // return 1;
        // }
        // }
        // System.out.println(move[0] + " " + move[1]);

        Position start = new Position(readMove(move[0]));
        // System.out.println(start.getrank() + " " + start.getfile());
        if (!inBounds(start) || getPiece(start) == null || !getPiece(start).color.equals(playerColor)) {
            System.out.println("Illegal move, try again");
            return 1;
        }

        Position end = new Position(readMove(move[1]));
        if (!inBounds(end) || getPiece(start) == null || !getPiece(start).color.equals(playerColor)) {
            System.out.println("Illegal move, try again");
            return 1;
        }

        computeLists();

        // CASTLING

        if (playerColor.equals(ChessPiece.COLOR_WHITE)) {
            Position night = new Position(1, 0);
            Position rookk = new Position(0, 0);
            Position quee = new Position(3, 0);
            // Queenside Castling for White
            if (getPiece(start) instanceof King && getPiece(end) == null && getPiece(night) == null
                    && getPiece(quee) == null && !getPiece(start).hasMoved && !getPiece(rookk).hasMoved
                    && !((King) getPiece(start)).inCheck()) {

                // getPiece(start).setPosition(end.getfile(), end.getrank());
                computeOpponentAttackList(oppositeColor);
                if (((King) getPiece(start)).threats(end).size() == 0
                        && ((King) getPiece(start)).threats(night).size() == 0
                        && ((King) getPiece(start)).threats(quee).size() == 0)
                    ((King) getPiece(start)).validMoveList.add(end);
                // castling = 1;
                // king.validMoveList.add(end);

            }
            rookk = new Position(7, 0);
            Position bish = new Position(5, 0);

            if (getPiece(start) instanceof King && getPiece(end) == null && getPiece(bish) == null
                    && !getPiece(start).hasMoved && !getPiece(rookk).hasMoved
                    && !((King) getPiece(start)).inCheck()) {

                computeOpponentAttackList(oppositeColor);
                if (((King) getPiece(start)).threats(end).size() == 0
                        && ((King) getPiece(start)).threats(bish).size() == 0)
                    ((King) getPiece(start)).validMoveList.add(end);
                // castling = 2;
            }

        } else {// black side castle

            Position night = new Position(1, 7);
            Position rookk = new Position(0, 7);
            Position quee = new Position(3, 7);
            // Queenside Castling for White
            if (getPiece(start) instanceof King && getPiece(end) == null && getPiece(night) == null
                    && getPiece(quee) == null && !getPiece(start).hasMoved && !getPiece(rookk).hasMoved
                    && !((King) getPiece(start)).inCheck()) {
                // castling = 3;
                // getPiece(start).setPosition(end.getfile(), end.getrank());
                computeOpponentAttackList(oppositeColor);
                if (((King) getPiece(start)).threats(end).size() == 0
                        && ((King) getPiece(start)).threats(night).size() == 0
                        && ((King) getPiece(start)).threats(quee).size() == 0)
                    ((King) getPiece(start)).validMoveList.add(end);

                // king.validMoveList.add(end);
            }
            rookk = new Position(7, 7);
            Position bish = new Position(5, 7);

            if (getPiece(start) instanceof King && getPiece(end) == null && getPiece(bish) == null
                    && !getPiece(start).hasMoved && !getPiece(rookk).hasMoved
                    && !((King) getPiece(start)).inCheck()) {

                computeOpponentAttackList(oppositeColor);
                if (((King) getPiece(start)).threats(end).size() == 0
                        && ((King) getPiece(start)).threats(bish).size() == 0)
                    ((King) getPiece(start)).validMoveList.add(end);
                // castling = 4;
            }

        }

        if (getPiece(start) instanceof Pawn) {
            Pawn testPawn = (Pawn) getPiece(start);
            if (testPawn.position.getrank() == 6 && playerColor.equals(ChessPiece.COLOR_WHITE)
                    || testPawn.position.getrank() == 1 && playerColor.equals(ChessPiece.COLOR_BLACK)) {
                testPawn.promotion = true;
            }
        }

        if (getPiece(start).validMoveList.contains(end)) {

            if (getPiece(start) instanceof Pawn && ((Pawn) getPiece(start)).promotion) {
                if (promotionPiece.equals("Q"))
                    ChessBoard[end.getrank()][end.getfile()] = new Queen(end.getfile(), end.getrank(), playerColor,
                            getPiece(start).king);
                if (promotionPiece.equals("N"))
                    ChessBoard[end.getrank()][end.getfile()] = new Knight(end.getfile(), end.getrank(), playerColor,
                            getPiece(start).king);
                if (promotionPiece.equals("B"))
                    ChessBoard[end.getrank()][end.getfile()] = new Bishop(end.getfile(), end.getrank(), playerColor,
                            getPiece(start).king);
                if (promotionPiece.equals("R"))
                    ChessBoard[end.getrank()][end.getfile()] = new Rook(end.getfile(), end.getrank(), playerColor,
                            getPiece(start).king);

                ChessBoard[start.getrank()][start.getfile()] = null;
            } else if (getPiece(start) instanceof King && Math.abs(end.getfile() - start.getfile()) > 1) {

                if (end.getfile() - start.getfile() > 0) {
                    // Kingside
                    getPiece(start).setPosition(end.getfile(), end.getrank());
                    ChessBoard[end.getrank()][end.getfile()] = getPiece(start);
                    ChessBoard[start.getrank()][start.getfile()] = null;
                    if (playerColor.equals(ChessPiece.COLOR_WHITE)) {
                        ChessBoard[0][7].setPosition(5, 0);
                        ChessBoard[0][5] = ChessBoard[0][7];
                        ChessBoard[0][7] = null;
                    } else {
                        ChessBoard[7][7].setPosition(5, 7);
                        ChessBoard[7][5] = ChessBoard[7][7];
                        ChessBoard[7][7] = null;
                    }
                } else {
                    // Queenside
                    getPiece(start).setPosition(end.getfile(), end.getrank());
                    ChessBoard[end.getrank()][end.getfile()] = getPiece(start);
                    ChessBoard[start.getrank()][start.getfile()] = null;
                    if (playerColor.equals(ChessPiece.COLOR_WHITE)) {
                        ChessBoard[0][0].setPosition(3, 0);
                        ChessBoard[0][3] = ChessBoard[0][0];
                        ChessBoard[0][0] = null;
                    } else {
                        ChessBoard[7][0].setPosition(3, 7);
                        ChessBoard[7][3] = ChessBoard[7][0];
                        ChessBoard[7][0] = null;
                    }
                }
                // Determining if it is a castling move.

            } else {
                getPiece(start).setPosition(end.getfile(), end.getrank());
                ChessBoard[end.getrank()][end.getfile()] = getPiece(start);
                ChessBoard[start.getrank()][start.getfile()] = null;
            }
            printBoard();
        } else {
            System.out.println("Illegal move, try again");
            return 1;
        }

        computeLists();
        if (isCheckMate(oppositeColor)) {
            System.out.println("Checkmate");
            System.out.println(playerColor.toUpperCase() + " wins");
            return 2;
        }
        if (getKing(oppositeColor).inCheck()) {
            System.out.println("Check!");
        }

        return 0;
        // for (int i = 0; i < 8; i++) {
        // for (int j = 0; j < 8; j++) {
        // ChessBoard[i][j].validMoves();
        // ChessBoard[i][j].attackingSquares();
        // }
        // }
        // }
    }

    /**
     * Checks to see if there is any ChessPiece object in the ChessBoard that has
     * its position set to the testPosition. This is in testing for legality of
     * moves.
     * 
     * @param testPosition
     * @return boolean
     */
    public static boolean proposedOccupied(Position testPosition) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (ChessBoard[i][j] == null)
                    continue;
                if (ChessBoard[i][j].position.equals(testPosition))
                    return true;
            }
        }
        return false;
    }

    /**
     * Checks to see if there is an object at the specified location on the
     * ChessBoard of the specified playerColor
     * 
     * @param testPosition
     * @param playerColor
     * @return boolean
     */
    public static boolean isOccupied(Position testPosition, String playerColor) {
        try {
            if (ChessBoard[testPosition.getrank()][testPosition.getfile()].getColor().equals(playerColor)
                    && ChessBoard[testPosition.getrank()][testPosition.getfile()].position.equals(testPosition)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks to see if there is a ChessPiece object in the ChessBoard at the
     * specified location.
     * 
     * @param testPosition
     * @return boolean
     */
    public static boolean isOccupied(Position testPosition) {
        try {
            ChessBoard[testPosition.getrank()][testPosition.getfile()].getColor();
            if (ChessBoard[testPosition.getrank()][testPosition.getfile()].position.equals(testPosition))
                return true;
            else
                return false;
            // return true;
        } catch (Exception e) {
            return false;

        }
    }

    /**
     * Checks all valid movelists to see if King is in checkmate.
     * 
     * @param playerColor
     * @return boolean
     */
    public boolean isCheckMate(String playerColor) {
        King king = getKing(playerColor);
        if (king.inCheck()) {
            for (ChessPiece[] ranks : ChessBoard) {
                for (ChessPiece piece : ranks) {
                    if (piece != null && piece.color.equals(playerColor) && piece.validMoveList.size() != 0) {
                        System.out.println("Piece: " + piece + " at " + piece.position + ".\nValidMoveList size: "
                                + piece.validMoveList.size());
                        return false;
                    }
                }
            }
            return true;
        } else
            return false;
    }

    /**
     * Calls validMoves and attackingSquares methods for each piece on the
     * ChessBoard
     */
    public static void computeLists() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (ChessBoard[i][j] == null)
                    continue;
                ChessBoard[i][j].validMoves();
                ChessBoard[i][j].attackingSquares();
            }
        }
    }

    /**
     * Calls attackingSquares method for all pieces of the specified color
     * 
     * @param opponentColor
     */
    public static void computeOpponentAttackList(String opponentColor) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (ChessBoard[i][j] == null)
                    continue;
                if (ChessBoard[i][j].getColor().equals(opponentColor)) {
                    ChessBoard[i][j].attackingSquares();

                    // System.out.println("INITIALLY: " + ChessBoard[i][j] + " " +
                    // ChessBoard[i][j].position);
                    // for (Position s : ChessBoard[i][j].attackingSquaresList) {
                    // System.out.println(s);
                    // }
                    // System.out.println();
                }
            }
        }
    }

    // FOR TESTING ONLY
    /**
     * Method only used for testing
     * 
     */
    public void printValidMoves() {

        Pawn pa = (Pawn) ChessBoard[6][1];
        pa.validMoves();
        System.out.println(pa.validMoveList.size());
        for (Position p : pa.validMoveList) {
            System.out.println(p);
        }

        King ls = pa.king;
        ls.validMoves();
        System.out.println(ls.validMoveList.size());
        for (Position p : ls.validMoveList) {
            System.out.println(p);
        }

        System.out.println();

        Queen attackQueen = (Queen) ChessBoard[4][4];
        attackQueen.attackingSquares();
        System.out.println(attackQueen.attackingSquaresList.size());
        for (Position p : attackQueen.attackingSquaresList) {
            System.out.println(p);
        }

        Queen q = (Queen) ChessBoard[7][3];
        q.validMoves();
        System.out.println(q.validMoveList.size());
        for (Position p : q.validMoveList) {
            System.out.println(p);
        }

    }

}