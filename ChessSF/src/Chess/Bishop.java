package Chess;

public class Bishop extends ChessPiece {
    public Bishop(String color) {
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

        if (Math.abs(to_line - start_line) == Math.abs(to_column - start_column)) {
            int step_line = (to_line > start_line) ? 1 : -1;
            int step_column = (to_column > start_column) ? 1 : -1;

            for (int i = 1; i < Math.abs(to_line - start_line); i++) {
                if (board.board[start_line + i * step_line][start_column + i * step_column] != null) {
                    return false;
                }
            }

            ChessPiece target = board.board[to_line][to_column];
            return target == null || !target.get_color().equals(this.color);
        }

        return false;
    }

    @Override
    public String get_symbol() {
        return "С";
    }
}
