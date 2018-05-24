import java.awt.*;
import java.util.*;

import ecs100.*;

import javax.swing.*;


public class TicTacToe {
    private final JOptionPane JOptionPane = new JOptionPane();
    private String[][] board = new String[3][3];
    private String player = "x";
    private Object[] possibilities = {"Play Again", "Exit"};

    public TicTacToe() {
        UI.setWindowSize(320,310);
        this.drawBoard();
        UI.setFontSize(100);
        UI.setMouseListener(this::doMouse);
        UI.addButton("Restart", this::restartGame);
        UI.setDivider(0);


    }

    public boolean checkIfEmpty(int row, int col) {
        if (board[row][col] != null) {
            return false;
        }
        return true;
    }

    public void restartGame() {
        board = new String[3][3];
        UI.clearGraphics();
        drawBoard();
    }

    public void doMouse(String action, double x, double y) {
        int row = -1;
        int col = -1;
        if (action.equals("released")) {
            if (0 < x && x < 100) {
                if (0 < y && y < 100) {
                    row = 0;
                    col = 0;
                } else if (100 < y && y < 200) {
                    row = 0;
                    col = 1;
                } else if (200 < y && y < 300) {
                    row = 0;
                    col = 2;
                }
            } else if (100 < x && x < 200) {
                if (0 < y && y < 100) {
                    row = 1;
                    col = 0;
                } else if (100 < y && y < 200) {
                    row = 1;
                    col = 1;
                } else if (200 < y && y < 300) {
                    row = 1;
                    col = 2;
                }
            } else if (200 < x && x < 300) {
                if (0 < y && y < 100) {
                    row = 2;
                    col = 0;
                } else if (100 < y && y < 200) {
                    row = 2;
                    col = 1;
                } else if (200 < y && y < 300) {
                    row = 2;
                    col = 2;
                }
            }


            if (row != -1 && col != -1) {
                if (checkIfEmpty(row, col)) {
                    board[row][col] = player;
                    if (player.equals("x")) {
                        this.player = "o";
                    } else {
                        this.player = "x";
                    }
                } else {
                    System.out.println("error at " + row + "," + col);
                }
                drawBoard();
            }
            if (!checkIfTie()) {
                if (checkIfWinner()) {
                    if (player.equals("x")) {
                        this.player = "o";
                    } else {
                        this.player = "x";
                    }
                    int ans = javax.swing.JOptionPane.showOptionDialog(null, player + " JUST WON THE GAME!!!", "You win!!", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, possibilities, possibilities[0]);
                    if (ans == 0) restartGame();
                    else System.exit(0);
                }
            } else {
                int ans = javax.swing.JOptionPane.showOptionDialog(null, "This game is a tie!", "Game Over", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, possibilities, possibilities[0]);
                if (ans == 0) restartGame();
                else System.exit(0);

            }

        }
    }
    public boolean checkIfTie() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == null) {
                    return false;
                }

            }

        }
        return true;
    }

    public boolean checkIfWinner() {//just copy from the command line version TicTacToe
        if (board[0][0] == board[1][0] && board[1][0] == board[2][0] && (board[0][0] == "x" || board[0][0] == "o")) {
            return true;
        } else if (board[0][1] == board[1][1] && board[1][1] == board[2][1] && (board[0][1] == "x" || board[0][1] == "o")) {
            return true;
        } else if (board[0][2] == board[1][2] && board[1][2] == board[2][2] && (board[0][2] == "x" || board[0][2] == "o")) {
            return true;
        } else if (board[0][0] == board[0][1] && board[0][1] == board[0][2] && (board[0][0] == "x" || board[0][0] == "o")) {
            return true;
        } else if (board[1][0] == board[1][1] && board[1][1] == board[1][2] && (board[1][0] == "x" || board[1][0] == "o")) {
            return true;
        } else if (board[2][0] == board[2][1] && board[2][1] == board[2][2] && (board[2][0] == "x" || board[2][0] == "o")) {
            return true;
        } else if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && (board[0][0] == "x" || board[0][0] == "o")) {
            return true;
        } else if (board[2][0] == board[1][1] && board[1][1] == board[0][2] && (board[2][0] == "x" || board[2][0] == "o")) {
            return true;
        } else {
            return false;
        }
    }

    public void drawBoard() {
        UI.clearGraphics();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] != null) {
                    if (board[row][col].equals("o")) {
                        UI.setColor(Color.blue);
                        UI.drawOval(100 * row, 100 * col, 100, 100);
                    } else if (board[row][col].equals("x")) {
                        UI.setColor(Color.red);
                        UI.drawLine(row * 100, 100 * col, 100 * row + 100, 100 * col + 100);
                        UI.drawLine(100 * row + 100, 100 * col, 100 * row, 100 * col + 100);
                    }
                }
            }
        }
        UI.setColor(Color.black);
        UI.setLineWidth(3);
        UI.drawRect(0, 0, 300, 300);
        UI.drawLine(100, 0, 100, 300);
        UI.drawLine(200, 0, 200, 300);
        UI.drawLine(0, 100, 300, 100);
        UI.drawLine(0, 200, 300, 200);

    }


    public static void main(String[] args) {
        new TicTacToe();

    }
}
