package Chess;

public class Queen extends ChessPiece {
    public Queen(String color) {
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

        if (Math.abs(to_line - start_line) == Math.abs(to_column - start_column) ||
                start_line == to_line || start_column == to_column) {
            int step_line = (to_line > start_line) ? 1 : (to_line < start_line ? -1 : 0);
            int step_column = (to_column > start_column) ? 1 : (to_column < start_column ? -1 : 0);

            int current_line = start_line + step_line;
            int current_column = start_column + step_column;

            while (current_line != to_line || current_column != to_column) {
                if (board.board[current_line][current_column] != null) {
                    return false;
                }
                current_line += step_line;
                current_column += step_column;
            }

            ChessPiece target = board.board[to_line][to_column];
            return target == null || !target.get_color().equals(this.color);
        }

        return false;
    }

    @Override
    public String get_symbol() {
        return "Q";
    }
}
