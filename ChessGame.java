package chess;

import java.util.Scanner;

public class ChessGame {
    private static String[][] board = new String[8][8];
    private static String[][] temp = new String[8][8];

    public static void updateTemp() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                temp[i][j] = board[i][j];
            }
        }
    }

    private static int currentPlayer = 1; // 1 cho người chơi trắng, -1 cho người chơi đen

    public static void initializeBoard() {
        // Khởi tạo bàn cờ với các quân cờ mặc định
        // Ví dụ: "P" là tốt (pawn), "R" là xe (rook), "K" là mã (knight), "B" là tượng
        // (bishop), "Q" là hậu (queen), "K" là vua (king)

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 1)
                    board[i][j] = "P"; // Quân tốt đen
                else if (i == 6)
                    board[i][j] = "p"; // Quân tốt trắng
                else
                    board[i][j] = "| |";
            }
        }

        // Khởi tạo các quân cờ xe, mã, tượng, hậu, vua

        board[0][0] = "R"; // Xe đen
        board[0][1] = "N"; // Mã đen
        board[0][2] = "B"; // Tượng đen
        board[0][3] = "Q"; // Hậu đen
        board[0][4] = "K"; // Vua đen
        board[0][5] = "B"; // Tượng đen
        board[0][6] = "N"; // Mã đen
        board[0][7] = "R"; // Xe đen

        board[7][0] = "r"; // Xe trắng
        board[7][1] = "n"; // Mã trắng
        board[7][2] = "b"; // Tượng trắng
        board[7][3] = "q"; // Hậu trắng
        board[7][4] = "k"; // Vua trắng
        board[7][5] = "b"; // Tượng trắng
        board[7][6] = "n"; // Mã trắng
        board[7][7] = "r"; // Xe trắng
    }

    public static int checkWin() {
        int k = 0;
        int K = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].equals("K")) {
                    K++;
                }
                if (board[i][j].equals("k")) {
                    k++;
                }
            }
        }

        if (k == 0) {
            return 10;
        }
        if (K == 0) {
            return -10;
        }

        return 0;
    }

    public static void printBoard() {
        // In bàn cờ
        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {

                if (board[i][j].equals("| |")) {
                    // System.out.print("  ");
                    System.out.print("  ");
                }

                else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void printTemp() {
        // In bàn cờ
        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {

                if (temp[i][j].equals("| |")) {
                    System.out.print("  ");
                }

                else {
                    System.out.print(temp[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static boolean isValidMove(int startX, int startY, int endX, int endY) {
        String s = board[startX][startY];
        if (s.equals("R") || s.equals("r") || s.equals("Q") || s.equals("q")) {
            if (Xe_Hau(s, startX, startY, endX, endY)) {
                // System.out.println("Xe or Hau");
                return true;
            }
        }

        if (s.equals("B") || s.equals("b") || s.equals("Q") || s.equals("q")) {
            if (Cheo(s, startX, startY, endX, endY) && Tuong_Hau(s)) {
                // System.out.println("Tuong or Hau");
                return true;
            }
        }

        if (s.equals("n") || s.equals("N")) {
            if (Ma(s, startX, startY, endX, endY)) {
                // System.out.println("Ma");
                return true;
            }
        }

        if (s.equals("K") || s.equals("k")) {
            if (Vua(s, startX, startY, endX, endY)) {
                // System.out.println("King");
                return true;
            }
        }

        if (s.equals("p") || s.equals("P")) {
            if (Tot(s, startX, startY, endX, endY)) {
                // System.out.println("Tot");
                return true;
            }
        }

        return false;
    }

    public static void movePiece(int startX, int startY, int endX, int endY) {
        // Kiểm tra tính hợp lệ của nước đi và di chuyển quân cờ
        if (isValidMove(startX, startY, endX, endY)) {
            board[endX][endY] = board[startX][startY];
            board[startX][startY] = "| |";

            temp[endX][endY] = temp[startX][startY];
            temp[startX][startY] = "| |";
            // currentPlayer *= -1; // Chuyển lượt cho người chơi khác
        } else {
            System.out.println("Nước đi không hợp lệ. Vui lòng thử lại.");
        }
    }

    public static void main(String[] args) {
        initializeBoard();
        printBoard();
        updateTemp();

        int a = -10;
        int b = -10;
        int c = -10;
        int d = -10;

        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (checkWin() == 10) {
                System.out.println("BLACK WIN");
                break;
            }
            if (checkWin() == -10) {
                System.out.println("WHITE WIN");
                break;
            }

            System.out.println("Lượt của người chơi: " + (currentPlayer == 1 ? "Trắng" : "Đen"));

            if (currentPlayer == 1) {
                System.out.print("Nhập vị trí quân cờ (ví dụ: a2): ");
                String startMove = scanner.nextLine().toLowerCase();
                int startX = 8 - Character.getNumericValue(startMove.charAt(1));
                int startY = startMove.charAt(0) - 'a';

                System.out.println("This: " + board[startX][startY]);

                System.out.print("Nhập nước đi (ví dụ: a4): ");
                String endMove = scanner.nextLine().toLowerCase();
                int endX = 8 - Character.getNumericValue(endMove.charAt(1));
                int endY = endMove.charAt(0) - 'a';

                System.out.println("endX: " + endX + " endY:" + endY);

                movePiece(startX, startY, endX, endY);
                updateTemp();
                printBoard();
                System.out.println();
                currentPlayer *= -1;
            }

            if (currentPlayer == -1) {
                int score = 0;
                // String temp;
                int best = Integer.MIN_VALUE;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (board[i][j].equals("R") || board[i][j].equals("N") || board[i][j].equals("B")
                                || board[i][j].equals("Q") || board[i][j].equals("K") || board[i][j].equals("P")) {
                            for (int m = 0; m < 8; m++) {
                                for (int n = 0; n < 8; n++) {
                                    // temp = board[m][n];
                                    // System.out.println("temp: " + temp);
                                    if (isValidMove(i, j, m, n)) {
                                        // movePiece(i, j, m, n);
                                        board[m][n]=board[i][j];
                                        board[i][j]="| |";
                                        score = minimax(0, -1);
                                        board[i][j] = temp[i][j];
                                        board[m][n] = temp[m][n];

                                        if (score > best) {
                                            best = score;
                                            a = i;
                                            b = j;
                                            c = m;
                                            d = n;
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
                movePiece(a, b, c, d);
                System.out.println();
                updateTemp();
                printBoard();
                System.out.println(" ");
                currentPlayer *= -1;
            }
        }
    }

    public static int minimax(int depth, int player) {
        int score;
        if (checkWin() != 0 || depth == 0) {
            return evaluateBoard();
        }

        if (player == 1) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board[i][j].equals("R") || board[i][j].equals("N") || board[i][j].equals("B")
                            || board[i][j].equals("Q") || board[i][j].equals("K") || board[i][j].equals("P")) {
                        for (int m = 0; m < 8; m++) {
                            for (int n = 0; n < 8; n++) {
                                if (isValidMove(i, j, m, n)) {
                                    movePiece(i, j, m, n);
                                    score = minimax(0, -1);
                                    board[i][j] = temp[i][j];
                                    board[m][n] = temp[m][n];
                                    best = Math.max(score, best);

                                }

                            }
                        }
                    }
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board[i][j].equals("r") || board[i][j].equals("n") || board[i][j].equals("b")
                            || board[i][j].equals("q") || board[i][j].equals("k") || board[i][j].equals("p")) {
                        for (int m = 0; m < 8; m++) {
                            for (int n = 0; n < 8; n++) {
                                if (isValidMove(i, j, m, n)) {
                                    movePiece(i, j, m, n);
                                    score = minimax(0, 1);
                                    board[i][j] = temp[i][j];
                                    board[m][n] = temp[m][n];
                                    best = Math.min(score, best);

                                }
                                // best = Math.min(score, best);
                            }
                        }
                    }
                }
            }
            return best;
        }
    }

    public static int evaluateBoard() {
        int whiteScore = 0;
        int blackScore = 0;

        // Gán giá trị cho từng loại quân cờ
        int pawnValue = 1;
        int knightValue = 3;
        int bishopValue = 3;
        int rookValue = 5;
        int queenValue = 9;
        int kingValue = 10;
        // Duyệt qua bàn cờ và tính tổng giá trị cho cả hai bên
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String piece = board[i][j];
                if (!piece.equals("| |")) {
                    int pieceValue = 0;

                    // Gán giá trị cho từng loại quân cờ
                    if (piece.equals("P") || piece.equals("p")) {
                        pieceValue = pawnValue;
                    } else if (piece.equals("N") || piece.equals("n")) {
                        pieceValue = knightValue;
                    } else if (piece.equals("B") || piece.equals("b")) {
                        pieceValue = bishopValue;
                    } else if (piece.equals("R") || piece.equals("r")) {
                        pieceValue = rookValue;
                    } else if (piece.equals("Q") || piece.equals("q")) {
                        pieceValue = queenValue;
                    } else {
                        pieceValue = kingValue;
                    }
                    if (Character.isUpperCase(piece.charAt(0))) {
                        whiteScore += pieceValue;
                    } else {
                        blackScore += pieceValue;
                    }
                }
            }
        }

        // Trả về hiệu số điểm giữa hai bên

        return whiteScore - blackScore;
    }

    // public static int checkWin() {
    // // Kiểm tra điều kiện chiến thắng
    // // (Thêm logic kiểm tra)
    // return 0;
    // }

    public static boolean Cheo(String s, int startX, int startY, int endX, int endY) {
        if (Math.abs(startX - endX) == Math.abs(startY - endY)) {
            int x = startX;
            int y = startY;

            // if ((board[endX][endY].equals(" ") || KhacPhe(s, board[endX][endY]))) {
            if (KhacPhe(s, board[endX][endY])|| board[endX][endY].equals("| |")) {

                // System.out.println("Khong cung phe");
                if (startX > endX && startY > endY) {
                    while (x != endX + 1) {
                        x--;
                        y--;
                        if (board[x][y] != "| |") {
                            
                            return false;
                        }
                    }
                    return true;
                }

                if (startX < endX && startY > endY) {
                    while (x != endX - 1) {
                        x++;
                        y--;
                        if (board[x][y] != "| |") {
                            return false;
                        }
                    }
                    return true;
                }

                if (startX > endX && startY < endY) {
                    while (x != endX + 1) {
                        x--;
                        y++;
                        if (board[x][y] != "| |") {
                            return false;
                        }
                    }
                    return true;
                }

                if (startX < endX && startY < endY) {
                    while (x != endX - 1) {
                        x++;
                        y++;
                        if (board[x][y] != "| |") {
                            return false;
                        }
                    }
                    return true;
                }
            }
            // System.out.println("Kiem tra xem co khac phe voi la o trong ko");
        }
        // System.out.println("Kiem tra xem vi tri co nam tren duong cheo ko");
        return false;
    }

    public static boolean Tuong_Hau(String s) {
        if (s.equals("B") || s.equals("b") || s.equals("q") || s.equals("Q")) {
            return true;
        }

        return false;
    }

    public static boolean Ma(String s, int startX, int startY, int endX, int endY) {

        if (s.equals("N") || s.equals("n")) {
            if (KhacPhe(s, board[endX][endY])|| board[endX][endY].equals("| |")) {
                // System.out.println(1);
                if (Math.abs(startX - endX) == 1 && Math.abs(startY - endY) == 2
                        || Math.abs(startX - endX) == 2 && Math.abs(startY - endY) == 1) {
                    // System.out.println(2);
                    if (startX - endX == 1 && startY - endY == 2 || startX - endX == -1 && startY - endY == 2) {
                        // System.out.println(3);
                        if (board[startX][startY - 1] == "| |" && board[startX][startY - 2] == "| |") {
                            return true;
                        }
                    }

                    if (startX - endX == 2 && startY - endY == 1 || startX - endX == 2 && startY - endY == -1) {
                        // System.out.println(4);
                        if (board[startX - 1][startY] == "| |" && board[startX - 2][startY] == "| |") {
                            return true;
                        }
                    }

                    if (startX - endX == 1 && startY - endY == -2 || startX - endX == -1 && startY - endY == -2) {
                        // System.out.println(5);
                        if (board[startX][startY + 1] == "| |" && board[startX][startY + 2] == "| |") {
                            return true;
                        }
                    }

                    if (startX - endX == -2 && startY - endY == 1 || startX - endX == -2 && startY - endY == -1) {
                        // System.out.println(6);
                        // System.out.println("board[startX + 1][startY] =" + board[startX + 1][startY]);
                        // System.out.println("board[startX + 2][startY] =" + board[startX + 1][startY]);
                        if (board[startX + 1][startY] == "| |" && board[startX + 2][startY] == "| |") {
                            return true;
                        }
                    }
                }
            }
        }
        return false;

    }

    public static boolean Xe_Hau(String s, int startX, int startY, int endX, int endY) {

        if (s == "Q" || s == "q" || s == "r" || s == "R") {
            if (Math.abs(startX - endX) != 0 && Math.abs(startY - endY) == 0
                    || Math.abs(startX - endX) == 0 && Math.abs(startY - endY) != 0) {
                if (KhacPhe(s, board[endX][endY]) || board[endX][endY].equals("| |")) {
                    int x = startX;
                    int y = startY;

                    // System.out.println(KhacPhe(s, board[endX][endY]));

                    if (startX > endX) {
                        // System.out.println(0.1);
                        while (x > endX + 1) {
                            x--;
                            // System.out.println("ĐMM");
                            if (!board[x][y].equals("| |")) {
                                // System.out.println("board[x][y]: "+board[x][y]);
                                // System.out.println("1");
                                return false;
                            }

                        }
                        return true;

                    }

                    if (startX < endX) {
                        // System.out.println(0.2);
                        while (x < endX - 1) {
                            x++;
                            if (!board[x][y].equals("| |")) {
                                // System.out.println(2);
                                return false;
                            }
                        }
                        return true;
                    }

                    if (startY > endY) {
                        // System.out.println(0.3);
                        while (y > endY + 1) {
                            y--;
                            if (!board[x][y].equals("| |")) {
                                // System.out.println(3);
                                return false;
                            }
                        }
                        return true;
                    }

                    if (startY < endY) {
                        // System.out.println(0.4);
                        while (y < endY - 1) {
                            y++;
                            if (!board[x][y].equals("| |")) {
                                // System.out.println(4);
                                return false;
                            }
                        }
                        return true;
                    }
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public static boolean Tot(String s, int startX, int startY, int endX, int endY) {
        if (s.equals("p")) {
            // System.out.println(1);
            if (KhacPhe(s, board[endX][endY]) || board[endX][endY].equals("| |")) {
                // System.out.println(2);
                if (endX - startX == -2 && startX == 6 && startY == endY && board[endX][endY].equals("| |")) {
                    // System.out.println(3);
                    if (board[startX - 1][startY].equals("| |")) {
                        return true;
                    }
                }
                if (endX - startX == -1 && startY == endY && board[endX][endY].equals("| |")) {
                    return true;
                }
                if (endX - startX == -1 && endY - startY == 1 && !board[endX][endY].equals("| |")
                        && KhacPhe(s, board[endX][endY])) {
                    return true;
                }
                if (endX - startX == -1 && endY - startY == -1 && !board[endX][endY].equals("| |")
                        && KhacPhe(s, board[endX][endY])) {
                    return true;
                }
            }
        }

        if (s.equals("P")) {
            if (KhacPhe(s, board[endX][endY]) || board[endX][endY].equals("| |")) {
                if (endX - startX == 2 && startX == 1 && startY == endY && board[endX][endY].equals("| |")) {
                    if (board[startX + 1][startY].equals("| |")) {
                        return true;
                    }
                }
                if (endX - startX == 1 && startY == endY && board[endX][endY].equals("| |")) {
                    return true;
                }
                if (endX - startX == 1 && endY - startY == 1 && !board[endX][endY].equals("| |")
                        && KhacPhe(s, board[endX][endY])) {
                    return true;
                }
                if (endX - startX == 1 && endY - startY == -1 && !board[endX][endY].equals("| |")
                        && KhacPhe(s, board[endX][endY])) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean Vua(String x, int startX, int startY, int endX, int endY) {
        if (x.equals("K") || x.equals("k")) {
            if (KhacPhe(x, board[endX][endY]) || board[endX][endY].equals("| |")) {
                if (Math.abs(startX - endX) == 1 && Math.abs(startY - endY) == 1) {
                    return true;
                }

                if (Math.abs(startX - endX) == 1 && Math.abs(startY - endY) == 0) {
                    return true;
                }

                if (Math.abs(startX - endX) == 0 && Math.abs(startY - endY) == 1) {
                    return true;
                }

                // if (Math.abs(startX - endX) == 1 && Math.abs(startY - endY) == 1) {
                // return true;
                // }

            }
        }
        return false;
    }

    public static boolean KhacPhe(String a, String b) {
        char start = a.charAt(0);
        char end = b.charAt(0);
        return (Character.isUpperCase(start) && Character.isUpperCase(end) == false
                || Character.isUpperCase(end) && Character.isUpperCase(start) == false);
    }
}
