package Chess;

public class Horse extends ChessPiece {
    public Horse(String color) {
        super(color);
    }

    @Override
    public String get_color() {
        return this.color;
    }

    @Override
    public boolean can_move_to_position(ChessBoard board, int start_line, int start_column, int to_line, int to_column) {
        if (!board.check_pos(to_line) || !board.check_pos(to_column)) return false;

        if (start_line == to_line && start_column == to_column) return false;

        int row_diff = Math.abs(to_line - start_line);
        int col_diff = Math.abs(to_column - start_column);
        boolean is_l_shape_move = (row_diff == 2 && col_diff == 1) || (row_diff == 1 && col_diff == 2);

        ChessPiece target = board.board[to_line][to_column];
        return is_l_shape_move && (target == null || !target.get_color().equals(this.color));
    }

    @Override
    public String get_symbol() {
        return "К";
    }
}
