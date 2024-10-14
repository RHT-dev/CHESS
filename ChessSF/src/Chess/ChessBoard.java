package Chess;

public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8];
    String now_player;

    public ChessBoard(String now_player) {
        this.now_player = now_player;
        initialize_board();
    }

    private void initialize_board() {
        board[0][0] = new Rook("Белый");
        board[0][1] = new Horse("Белый");
        board[0][2] = new Bishop("Белый");
        board[0][3] = new Queen("Белый");
        board[0][4] = new King("Белый");
        board[0][5] = new Bishop("Белый");
        board[0][6] = new Horse("Белый");
        board[0][7] = new Rook("Белый");
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn("Белый");
        }

        board[7][0] = new Rook("Чёрный");
        board[7][1] = new Horse("Чёрный");
        board[7][2] = new Bishop("Чёрный");
        board[7][3] = new Queen("Чёрный");
        board[7][4] = new King("Чёрный");
        board[7][5] = new Bishop("Чёрный");
        board[7][6] = new Horse("Чёрный");
        board[7][7] = new Rook("Чёрный");
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn("Чёрный");
        }
    }

    public String now_player_color() {
        return this.now_player;
    }

    public boolean move_to_position(int start_line, int start_column, int end_line, int end_column) {
        if (check_pos(start_line) && check_pos(start_column)) {
            if (board[start_line][start_column] == null) return false;
            if (!now_player.equals(board[start_line][start_column].get_color())) return false;

            if (board[start_line][start_column].can_move_to_position(this, start_line, start_column, end_line, end_column)) {
                ChessPiece target_piece = board[end_line][end_column];

                board[end_line][end_column] = board[start_line][start_column];
                board[start_line][start_column] = null;

                if (board[end_line][end_column].get_symbol().equals("K") ||
                        board[end_line][end_column].get_symbol().equals("R")) {
                    board[end_line][end_column].check = false;
                }

                this.now_player = this.now_player_color().equals("Белый") ? "Чёрный" : "Белый";
                return true;
            } else return false;
        } else return false;
    }

    public void print_board() {
        System.out.println("Ход: " + now_player);
        System.out.println();
        System.out.println("Игрок 2 (Чёрный)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");
        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].get_symbol() + board[i][j].get_color().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Игрок 1 (Белый)");
    }

    public boolean check_pos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public boolean castling_0() {
        if (now_player.equals("Белый")) {
            if (board[0][0] == null || board[0][4] == null) return false;
            if (board[0][0].get_symbol().equals("R") && board[0][4].get_symbol().equals("K") &&
                    board[0][1] == null && board[0][2] == null && board[0][3] == null) {
                if (board[0][0].get_color().equals("Белый") && board[0][4].get_color().equals("Белый") &&
                        board[0][0].check && board[0][4].check &&
                        !new King("Белый").is_under_attack(this, 0, 2)) {
                    board[0][4] = null;
                    board[0][2] = new King("Белый");
                    board[0][2].check = false;
                    board[0][0] = null;
                    board[0][3] = new Rook("Белый");
                    board[0][3].check = false;
                    now_player = "Чёрный";
                    return true;
                }
            }
        } else {
            if (board[7][0] == null || board[7][4] == null) return false;
            if (board[7][0].get_symbol().equals("R") && board[7][4].get_symbol().equals("K") &&
                    board[7][1] == null && board[7][2] == null && board[7][3] == null) {
                if (board[7][0].get_color().equals("Чёрный") && board[7][4].get_color().equals("Чёрный") &&
                        board[7][0].check && board[7][4].check &&
                        !new King("Чёрный").is_under_attack(this, 7, 2)) {
                    board[7][4] = null;
                    board[7][2] = new King("Чёрный");
                    board[7][2].check = false;
                    board[7][0] = null;
                    board[7][3] = new Rook("Чёрный");
                    board[7][3].check = false;
                    now_player = "Белый";
                    return true;
                }
            }
        }
        return false;
    }

    public boolean castling_7() {
        if (now_player.equals("Белый")) {
            if (board[0][7] == null || board[0][4] == null) return false;
            if (board[0][7].get_symbol().equals("R") && board[0][4].get_symbol().equals("K") &&
                    board[0][5] == null && board[0][6] == null) {
                if (board[0][7].get_color().equals("Белый") && board[0][4].get_color().equals("Белый") &&
                        board[0][7].check && board[0][4].check &&
                        !new King("Белый").is_under_attack(this, 0, 6)) {
                    board[0][4] = null;
                    board[0][6] = new King("Белый");
                    board[0][6].check = false;
                    board[0][7] = null;
                    board[0][5] = new Rook("Белый");
                    board[0][5].check = false;
                    now_player = "Чёрный";
                    return true;
                }
            }
        } else {
            if (board[7][7] == null || board[7][4] == null) return false;
            if (board[7][7].get_symbol().equals("R") && board[7][4].get_symbol().equals("K") &&
                    board[7][5] == null && board[7][6] == null) {
                if (board[7][7].get_color().equals("Чёрный") && board[7][4].get_color().equals("Чёрный") &&
                        board[7][7].check && board[7][4].check &&
                        !new King("Чёрный").is_under_attack(this, 7, 6)) {
                    board[7][4] = null;
                    board[7][6] = new King("Чёрный");
                    board[7][6].check = false;
                    board[7][7] = null;
                    board[7][5] = new Rook("Чёрный");
                    board[7][5].check = false;
                    now_player = "Белый";
                    return true;
                }
            }
        }
        return false;
    }
}
