package chess.Draft_17_11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chess extends JFrame {
    private JButton[][] squares = new JButton[8][8];
    private String[][] board = new String[8][8];

    private JButton selectedSquare = null;

    public Chess() {
        setTitle("Chess Board");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 8));

        initializeBoard();
        setupGUI();
    }

    private void initializeBoard() {
        // Initialize board (you can customize this based on your chess piece
        // representations)
        

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 1) {
                    board[i][j] = "P";
                } else if (i == 6) {
                    board[i][j] = "p";
                }
                else
                {
                    board[i][j]="| |";
                }
            }
        }


        board[0][0] = "R";
        board[0][1] = "N";
        board[0][2] = "B";
        board[0][3] = "Q";
        board[0][4] = "K";
        board[0][5] = "B";
        board[0][6] = "N";
        board[0][7] = "R";

        board[7][0] = "r";
        board[7][1] = "n";
        board[7][2] = "b";
        board[7][3] = "q";
        board[7][4] = "k";
        board[7][5] = "b";
        board[7][6] = "n";
        board[7][7] = "r";

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                squares[row][col] = new JButton();
                squares[row][col].setPreferredSize(new Dimension(50, 50));

                // Set background color based on the position
                if ((row + col) % 2 == 0) {
                    squares[row][col].setBackground(Color.WHITE);
                } else {
                    squares[row][col].setBackground(Color.green);
                }

                // Set initial piece, if any
                setPieceOnButton(row, col);

                add(squares[row][col]);
            }
        }
    }



    private void printBoard()
    {
        for(int i=0; i<8; i++)
        {
            for(int j=0; j<8; j++)
            {
                if (board[i][j].equals("| |")) {
                    // System.out.print("  ");
                    System.out.print("  ");
                }

                else {
                    System.out.print(board[i][j] + " ");
                }

                // System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n\n");
    }
    private void setupGUI() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                final int currentRow = row;
                final int currentCol = col;

                squares[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleSquareClick(currentRow, currentCol);
                    }
                });
            }
        }
    }

    private void handleSquareClick(int row, int col) {
        System.out.println(row+"  "+col);
        JButton clickedSquare = squares[row][col];

        if (selectedSquare == null) {
            // No square is selected, select the clicked square
            selectedSquare = clickedSquare;
        } else {
            // Move the piece to the new location
            int fromRow = getRowOfButton(selectedSquare);
            int fromCol = getColOfButton(selectedSquare);

            // Move the piece on the backend
            board[row][col] = board[fromRow][fromCol];
            board[fromRow][fromCol] = "| |";

            // Update the GUI
            setPieceOnButton(row, col);
            clearPieceOnButton(fromRow, fromCol);

            // Reset the selected square
            selectedSquare = null;
        }
    }

    private int getRowOfButton(JButton button) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (squares[row][col] == button) {
                    return row;
                }
            }
        }
        return -1;
    }

    private int getColOfButton(JButton button) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (squares[row][col] == button) {
                    return col;
                }
            }
        }
        return -1;
    }

    private void setPieceOnButton(int row, int col) {
        if (board[row][col] != null) {
            squares[row][col].setIcon(getPieceIcon(board[row][col]));
        }
    }

    private void clearPieceOnButton(int row, int col) {
        squares[row][col].setIcon(null);
    }

    private ImageIcon getPieceIcon(String piece) {
        String path ="";
        printBoard();
        if ("P".equals(piece)) {
            path = "D:\\Downloads\\ChessOOP-master\\ChessOOP-master\\Chess\\src\\chess\\Black_Pawn.png";
        }

        if ("B".equals(piece)) {
            path = "D:\\Downloads\\ChessOOP-master\\ChessOOP-master\\Chess\\src\\chess\\Black_Bishop.png";
        }

        if ("K".equals(piece)) {
            path = "D:\\Downloads\\ChessOOP-master\\ChessOOP-master\\Chess\\src\\chess\\Black_King.png";
        }

        if ("Q".equals(piece)) {
            path = "D:\\Downloads\\ChessOOP-master\\ChessOOP-master\\Chess\\src\\chess\\Black_Queen.png";
        }

        if ("R".equals(piece)) {
            path = "D:\\Downloads\\ChessOOP-master\\ChessOOP-master\\Chess\\src\\chess\\Black_Rook.png";
        }

        if ("N".equals(piece)) {
            path = "D:\\Downloads\\ChessOOP-master\\ChessOOP-master\\Chess\\src\\chess\\Black_Knight.png";
        }






        if ("p".equals(piece)) {
            path = "D:\\Downloads\\ChessOOP-master\\ChessOOP-master\\Chess\\src\\chess\\White_Pawn.png";
        }

        if ("b".equals(piece)) {
            path = "D:\\Downloads\\ChessOOP-master\\ChessOOP-master\\Chess\\src\\chess\\White_Bishop.png";
        }

        if ("k".equals(piece)) {
            path = "D:\\Downloads\\ChessOOP-master\\ChessOOP-master\\Chess\\src\\chess\\White_King.png";
        }

        if ("q".equals(piece)) {
            path = "D:\\Downloads\\ChessOOP-master\\ChessOOP-master\\Chess\\src\\chess\\White_Queen.png";
        }

        if ("r".equals(piece)) {
            path = "D:\\Downloads\\ChessOOP-master\\ChessOOP-master\\Chess\\src\\chess\\White_Rook.png";
        }

        if ("n".equals(piece)) {
            path = "D:\\Downloads\\ChessOOP-master\\ChessOOP-master\\Chess\\src\\chess\\White_Knight.png";
        }





        // Add more conditions for other board

        // Load the image and scale it to fit the button
        ImageIcon icon = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        return icon;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Chess().setVisible(true);
            }
        });
    }
}
