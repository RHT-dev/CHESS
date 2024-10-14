package Chess;

public class Pawn extends ChessPiece {
    public Pawn(String color) {
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

        int direction = this.color.equals("Белый") ? 1 : -1;

        if (to_line == start_line + direction && to_column == start_column) {
            return board.board[to_line][to_column] == null;
        }

        if (start_line == (this.color.equals("Белый") ? 1 : 6) &&
                to_line == start_line + 2 * direction && to_column == start_column) {
            return board.board[to_line][to_column] == null && board.board[start_line + direction][start_column] == null;
        }

        if (to_line == start_line + direction && Math.abs(to_column - start_column) == 1) {
            ChessPiece target = board.board[to_line][to_column];
            return target != null && !target.get_color().equals(this.color);
        }

        return false;
    }

    @Override
    public String get_symbol() {
        return "П";
    }
}
