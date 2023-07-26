/**
 * @author Harshith Samayamantula (hs1018)
 * @author Oways Jaffer (omj9)
 * 
 */

package chess25.chess;

import java.util.*;

public class Chess {

    /**
     * Creates a visual representation of Chess. Users can input moves and play
     * Chess.
     * 
     * @param args
     */
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        chessLogic testGame = new chessLogic();
        testGame.printBoard();

        int moveCount = 0;

        String playerColor = ChessPiece.COLOR_WHITE;

        String input;
        int errcode;
        boolean drawFlag = false;
        while (true) {
            // if (testGame.isCheckMate(playerColor))
            // break;
            if ((moveCount % 2 == 0)) {
                playerColor = ChessPiece.COLOR_WHITE;
                System.out.print(playerColor + "'s move: ");
            } else {
                playerColor = ChessPiece.COLOR_BLACK;
                System.out.print(playerColor + "'s move: ");
            }
            input = scan.nextLine();
            if (input.equals("resign")) {
                if (playerColor.equals(ChessPiece.COLOR_WHITE))
                    System.out.println("Black wins");
                if (playerColor.equals(ChessPiece.COLOR_BLACK))
                    System.out.println("White wins");
                break;
            }
            // Looking for draw
            if (drawFlag) {
                if (input.equals("draw"))
                    break;
                else
                    drawFlag = false;
            }
            errcode = testGame.makeMove(playerColor, input, drawFlag);
            if (errcode == 0)
                moveCount++;
            if (errcode == 2) // Checkmate
                break;

        }

        scan.close();

    }
}
