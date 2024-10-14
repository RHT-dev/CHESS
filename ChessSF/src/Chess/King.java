package Chess;

public class King extends ChessPiece {

    public King(String color) {
        super(color);
    }

    @Override
    public String get_color() {
        return this.color;
    }

    @Override
    public String get_symbol() {
        return "К";
    }

    @Override
    public boolean can_move_to_position(ChessBoard board, int start_line, int start_column, int to_line, int to_column) {
        if (!board.check_pos(to_line) || !board.check_pos(to_column)) return false;
        if (start_line == to_line && start_column == to_column) return false;

        int line_diff = Math.abs(start_line - to_line);
        int column_diff = Math.abs(start_column - to_column);
        if (line_diff <= 1 && column_diff <= 1) {
            if (board.board[to_line][to_column] == null ||
                    !board.board[to_line][to_column].get_color().equals(this.color)) {
                return true;
            }
        }

        return false;
    }

    public boolean is_under_attack(ChessBoard board, int to_line, int to_column) {
        String opponent_color = this.color.equals("Белый") ? "Чёрный" : "Белый";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = board.board[i][j];
                if (piece != null && piece.get_color().equals(opponent_color)) {
                    if (piece.can_move_to_position(board, i, j, to_line, to_column)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
